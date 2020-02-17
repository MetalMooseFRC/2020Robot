/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.List;

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
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private static final Joystick driverStick = new Joystick(Constants.driverStickPort);

  private Drivetrain m_drivetrain = new Drivetrain();


  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    //drive based on joystick axis and customizable sensitivity constants
    m_drivetrain.setDefaultCommand(new DrivetrainDrive(
      () -> applyJoystickDeadBand(-driverStick.getY()) * Constants.joystickSpeedConstant, 
      () -> applyJoystickDeadBand(-driverStick.getZ()) * Constants.joystickTurnConstant, 
      m_drivetrain));
  }

  private void configureButtonBindings() {
    

  }

  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
        return new InstantCommand();
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
