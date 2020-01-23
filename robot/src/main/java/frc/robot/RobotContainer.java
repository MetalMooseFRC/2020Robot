/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.List;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private static final Joystick driverStick = new Joystick(Constants.driverStickPort);

  private Drivetrain m_drivetrain = new Drivetrain();
  private Limelight m_limelight = new Limelight();
  private ColorSensor m_colorsensor = new ColorSensor();
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    //drive based on joystick axis and customizable sensitivity constants
    m_drivetrain.setDefaultCommand(new DrivetrainDrive(
      () -> applyJoystickDeadBand(-driverStick.getY()) * Constants.joystickSpeedConstant, 
      () -> applyJoystickDeadBand(-driverStick.getZ()) * Constants.joystickTurnConstant, 
      m_drivetrain));
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //the following commands are only tests
    new JoystickButton(driverStick, 2)
    .whenHeld(new DrivetrainTarget(m_drivetrain, m_limelight));

    new JoystickButton(driverStick, 1)
    .whileHeld(new RunCommand(m_colorsensor::getCalibrationValues, m_colorsensor));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous

    //voltage constraints
    var autoVoltageConstraint =
      new DifferentialDriveVoltageConstraint(
        new SimpleMotorFeedforward(0.22, 1.98, 0.2),
        m_drivetrain.getKinematics(),
        10);

    //confige trajectory
    TrajectoryConfig config = new TrajectoryConfig(3, 3)
    .setKinematics(m_drivetrain.getKinematics())
    .addConstraint(autoVoltageConstraint);

    //generate trajectory of s curve
    Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
      new Pose2d(0,0, new Rotation2d(0)), 
      List.of(
        new Translation2d(1, 1),
        new Translation2d(2, -1)
      ), 
      new Pose2d(3,0, new Rotation2d(0)), 
      config);

      //Start ramsete calculations
      RamseteCommand ramseteCommand = new RamseteCommand(
        exampleTrajectory, 
        m_drivetrain::getPose, 
        new RamseteController(), 
        m_drivetrain.getKinematics(), 
        m_drivetrain::tankDriveMetersPerSecond, 
        m_drivetrain);

        //do trajectory
        return ramseteCommand.andThen(() -> m_drivetrain.arcadeDrive(0, 0));
  }

  //allow for dead areas on the joystick
  public double applyJoystickDeadBand(double originalValue) {
    //zero small inputs
    if (Math.abs(originalValue) < Constants.minimumJoystickInput) return 0;

    //scale larger inputs to maintain smoothness
    if (originalValue < 0) return originalValue + Constants.minimumJoystickInput;
    return originalValue - Constants.minimumJoystickInput;
  }
}
