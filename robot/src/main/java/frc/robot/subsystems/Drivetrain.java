/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {

  //Wheel motors and drive
  private CANSparkMax frontLeftMotor = new CANSparkMax(Constants.frontLeftMotorCANID, MotorType.kBrushless);
  private CANSparkMax topLeftMotor = new CANSparkMax(Constants.topLeftMotorCANID, MotorType.kBrushless);
  private CANSparkMax backLeftMotor = new CANSparkMax(Constants.backLeftMotorCANID, MotorType.kBrushless);
  private SpeedControllerGroup leftMotors = new SpeedControllerGroup(topLeftMotor, frontLeftMotor, backLeftMotor); 

  private CANSparkMax frontRightMotor = new CANSparkMax(Constants.frontRightMotorCANID, MotorType.kBrushless);
  private CANSparkMax topRightMotor = new CANSparkMax(Constants.topRightMotorCANID, MotorType.kBrushless);
  private CANSparkMax backRightMotor = new CANSparkMax(Constants.backRightMotorCANID, MotorType.kBrushless);
  private SpeedControllerGroup rightMotors = new SpeedControllerGroup(topRightMotor, frontRightMotor, backRightMotor); 

  private DifferentialDrive drive = new DifferentialDrive(leftMotors, rightMotors);

  //encoders
  private CANEncoder leftEncoder = new CANEncoder(frontLeftMotor);
  private CANEncoder rightEncoder = new CANEncoder(frontRightMotor);


  //Gearbox shifting solenoids
  private DoubleSolenoid leftShifter = new DoubleSolenoid(Constants.leftShifterForwardChannel, Constants.leftShifterReverseChannel);
  private DoubleSolenoid rightShifter = new DoubleSolenoid(Constants.rightShifterForwardChannel, Constants.rightShifterReverseChannel);

  //gyro
  private AHRS navx = new AHRS(Port.kMXP);

  //odometry for path generator
  private DifferentialDriveOdometry driveOdometry;

  public Drivetrain() {
    //reset encoders on startup
    resetEncoders();

    //set conversion factors
    leftEncoder.setPositionConversionFactor(Constants.encoderConversionFactor);
    leftEncoder.setVelocityConversionFactor(Constants.encoderConversionFactor);

    rightEncoder.setPositionConversionFactor(Constants.encoderConversionFactor);
    rightEncoder.setVelocityConversionFactor(Constants.encoderConversionFactor);

    driveOdometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    driveOdometry.update(Rotation2d.fromDegrees(getHeading()), getLeftSpeed(), getRightSpeed());
  }

 /* Drive methods*/

  public void arcadeDrive(double speed, double turn) {
    drive.arcadeDrive(speed, turn);
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    leftMotors.setVoltage(leftVolts);
    rightMotors.setVoltage(-rightVolts);
  }

  /** Odometry methods */

  public Pose2d getPose() {
    return driveOdometry.getPoseMeters();
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(getLeftSpeed(), getRightSpeed());
  }

  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    driveOdometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
  }

  /** Encoder methods */

  public double getLeftEncoder() {
    return leftEncoder.getPosition();
  }

  public double getRightEncoder() {
    return rightEncoder.getPosition();
  }

  public double getAverageEncoder() {
    return (leftEncoder.getPosition() + rightEncoder.getPosition())/ 2;
  }

  public double getLeftSpeed() {
    return leftEncoder.getVelocity();
  }

  public double getRightSpeed() {
    return rightEncoder.getVelocity();
  }

  public void resetEncoders() {
    leftEncoder.setPosition(0);
    rightEncoder.setPosition(0);
  }

  /** Solenoid methods */

  public void shiftLowGear() {
    leftShifter.set(Value.kReverse);
    rightShifter.set(Value.kReverse);
  }

  public void shiftHighGear() {
    leftShifter.set(Value.kForward);
    rightShifter.set(Value.kForward);
  }

  /** Gyro methods */

  public double getHeading() {
    return navx.getAngle() % 360;
  }

  public void resetHeading() {
    navx.reset();
  }
}
