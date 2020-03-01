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
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

public class RobotContainer {
  // OI controllers
  private static final Joystick driverStick = new Joystick(Constants.driverStickPort);
  JoystickButton reverseDriveButton, intakeButton, shootButton;

  // Subsystems
  private Drivetrain m_drivetrain = new Drivetrain();
  private Intake m_intake = new Intake();
  private Turret m_turret = new Turret();


  //Shuffleboard
  private final SendableChooser<Command> autoChooser = new SendableChooser<>();

  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Configure Shuffleboard
    autoChooser.setDefaultOption("Score 3", new InstantCommand());
    autoChooser.addOption("Cross line", new InstantCommand());

    //drive based on joystick axis and customizable sensitivity constants
    m_drivetrain.setDefaultCommand(new DrivetrainDrive(
      () -> applyJoystickDeadBand(-driverStick.getY()) * Constants.joystickSpeedConstant, 
      () -> applyJoystickDeadBand(-driverStick.getZ()) * Constants.joystickTurnConstant,
      () -> reverseDriveButton.get(), 
      m_drivetrain));

    m_intake.setDefaultCommand(new IntakeDefault(
      m_intake,
      () -> intakeButton.get()
    ));

    m_turret.setDefaultCommand(new TurretDefault(
      m_turret,
      () -> shootButton.get()
    ));


  }

  private void configureButtonBindings() {
    reverseDriveButton = new JoystickButton(driverStick, 2);
    intakeButton = new JoystickButton(driverStick, 12);
    shootButton = new JoystickButton(driverStick, 11);


  }

  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
        return autoChooser.getSelected();
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
