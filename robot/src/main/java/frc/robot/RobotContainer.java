/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.List;

import edu.wpi.cscore.HttpCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

public class RobotContainer {
  // OI controller
  private static final Joystick driverStick = new Joystick(Constants.driverStickPort);
  private static final XboxController operatorStick = new XboxController(Constants.operatorPort);

  JoystickButton intakeButton, outtakeButton, hoodSetButton, armButton;

  // Subsystems
  private Drivetrain m_drivetrain = new Drivetrain();
  private Intake m_intake = new Intake();
  private Turret m_turret = new Turret();
  private Limelight m_limelight = new Limelight();


  //Shuffleboard
  private final ShuffleboardTab shuffleBoard =Shuffleboard.getTab("SmartDashboard");
  private final SendableChooser<Command> autoChooser = new SendableChooser<>();
  private final HttpCamera limelightStream = new HttpCamera("limelight", "http://10.13.91.42:5800");

  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    CameraServer.getInstance().startAutomaticCapture(limelightStream);

    // Configure Shuffleboard
    autoChooser.setDefaultOption("Score 3", new InstantCommand());
    autoChooser.addOption("Cross line", new TurretShootForTime(3, m_turret));

    shuffleBoard.add(autoChooser);

    //drive based on joystick axis and customizable sensitivity constants
    m_drivetrain.setDefaultCommand(new DrivetrainDrive(
      () -> applyJoystickDeadBand(-driverStick.getY()) * Constants.joystickSpeedConstant, 
      () -> applyJoystickDeadBand(-driverStick.getZ()) * Constants.joystickTurnConstant, 
      m_drivetrain));

    m_intake.setDefaultCommand(new IntakeDefault(
      m_intake,
      () -> intakeButton.get(),
      () -> outtakeButton.get()
    ));

    m_turret.setDefaultCommand(new TurretDefault(
      m_turret,
      m_limelight,
      () -> operatorStick.getRawAxis(0),
      () -> operatorStick.getRawAxis(1),
      () -> driverStick.getRawAxis(3),
      () -> operatorStick.getRawAxis(2)
    ));


  }

  private void configureButtonBindings() {
    intakeButton = new JoystickButton(operatorStick, 5);
    outtakeButton = new JoystickButton(operatorStick, 6);
    hoodSetButton = new JoystickButton(operatorStick, 4);
    armButton = new JoystickButton(operatorStick, 3);

    armButton.whenPressed(m_intake::frontArmUp)
    .whenReleased(m_intake::frontArmDown);
    hoodSetButton.whenPressed(new TurretHoodToDistance(Constants.trenchEndHoodCount, m_turret));


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

  //reset every encoder
  public void resetAll() {
    m_drivetrain.resetEncoders();
    m_drivetrain.resetHeading();

    m_turret.resetHoodEncoder();
    m_turret.resetTurretEncoder();
  }
}
