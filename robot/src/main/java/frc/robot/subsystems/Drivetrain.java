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
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {

  //Wheel motors and drive
  private CANSparkMax frontLeftMotor = new CANSparkMax(Constants.frontLeftMotorCANID, MotorType.kBrushless);
  private CANSparkMax topLeftMotor = new CANSparkMax(Constants.topLeftMotorCANID, MotorType.kBrushless);
  private CANSparkMax backLeftMotor = new CANSparkMax(Constants.backLeftMotorCANID, MotorType.kBrushless);

  private CANSparkMax frontRightMotor = new CANSparkMax(Constants.frontRightMotorCANID, MotorType.kBrushless);
  private CANSparkMax topRightMotor = new CANSparkMax(Constants.topRightMotorCANID, MotorType.kBrushless);
  private CANSparkMax backRightMotor = new CANSparkMax(Constants.backRightMotorCANID, MotorType.kBrushless);

  //kinematics to convert chassis speeds to wheel speeds
  private DifferentialDriveKinematics driveKinematics = new DifferentialDriveKinematics(Constants.drivetrainWidth);

  //encoders
  private CANEncoder leftEncoder = new CANEncoder(frontLeftMotor);
  private CANEncoder rightEncoder = new CANEncoder(frontRightMotor);

  //gyro
  private AHRS navx = new AHRS(Port.kMXP);

  //odometry for path generator
  private DifferentialDriveOdometry driveOdometry;

  public Drivetrain() {
    resetHeading();
    //reset encoders on startup
    resetEncoders();

    //set conversion factors
    leftEncoder.setPositionConversionFactor(Constants.drivetrainEncoderConversionFactor);
    leftEncoder.setVelocityConversionFactor(Constants.drivetrainEncoderConversionFactor);

    rightEncoder.setPositionConversionFactor(Constants.drivetrainEncoderConversionFactor);
    rightEncoder.setVelocityConversionFactor(Constants.drivetrainEncoderConversionFactor);

    //group motor controllers
    topRightMotor.follow(frontRightMotor);
    backRightMotor.follow(frontRightMotor);

    topLeftMotor.follow(frontLeftMotor);
    backLeftMotor.follow(frontLeftMotor);

    //set idle mode
    topLeftMotor.setIdleMode(IdleMode.kBrake);
    topRightMotor.setIdleMode(IdleMode.kBrake);
    frontLeftMotor.setIdleMode(IdleMode.kBrake);
    frontRightMotor.setIdleMode(IdleMode.kBrake);
    backLeftMotor.setIdleMode(IdleMode.kBrake);
    backRightMotor.setIdleMode(IdleMode.kBrake);

    //initialize odometry
    driveOdometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    driveOdometry.update(Rotation2d.fromDegrees(getHeading()), getLeftSpeed(), getRightSpeed());

  }

 /* Drive methods*/

 //use kinematics to calculate wheel speeds
  public void arcadeDrive(double speed, double turn) {
    ChassisSpeeds chassisSpeeds = new ChassisSpeeds(speed, 0, turn);

    //calculate wheel speeds
    DifferentialDriveWheelSpeeds wheelSpeeds = driveKinematics.toWheelSpeeds(chassisSpeeds);
    //keep output between -1 and 1
    wheelSpeeds.normalize(1);

    //output speeds
    frontLeftMotor.set(wheelSpeeds.leftMetersPerSecond);
    frontRightMotor.set(-wheelSpeeds.rightMetersPerSecond);    
  }

  //control wheels directly through voltage supply
  public void tankDriveVolts(double leftVolts, double rightVolts) {
    frontLeftMotor.setVoltage(leftVolts);
    frontRightMotor.setVoltage(-rightVolts);
  }

  //control wheels through meters per second
  public void tankDriveMetersPerSecond(double leftSpeed, double rightSpeed) {
    DifferentialDriveWheelSpeeds wheelSpeeds = new DifferentialDriveWheelSpeeds(leftSpeed, rightSpeed);
    wheelSpeeds.normalize(1);

    frontLeftMotor.set(wheelSpeeds.leftMetersPerSecond);
    frontRightMotor.set(-wheelSpeeds.rightMetersPerSecond); 
  }

  public DifferentialDriveKinematics getKinematics() {
    return driveKinematics;
  }

  /** Odometry methods */

  //get position coordinates of robot
  public Pose2d getPose() {
    return driveOdometry.getPoseMeters();
  }

  //Get the individual wheel speeds for the robot
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(getLeftSpeed(), getRightSpeed());
  }

  //reset the position and heading for odometry
  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    driveOdometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
  }

  /** Encoder methods */

  //get left encoder in meters
  public double getLeftEncoder() {
    return leftEncoder.getPosition();
  }

  //get right encoder in meters
  public double getRightEncoder() {
    return rightEncoder.getPosition();
  }

  
  //get average distance between left and right encoders in meters
  public double getAverageDistance() {
    return (leftEncoder.getPosition() + rightEncoder.getPosition())/ 2;
  }

  //get average speed between left and right encoders in meters per second
  public double getAverageSpeed() {
    return (leftEncoder.getVelocity() + rightEncoder.getVelocity())/ 2;
  }

  //get left speed in meters per second
  public double getLeftSpeed() {
    return leftEncoder.getVelocity();
  }

  //get right speed in meters per second
  public double getRightSpeed() {
    return rightEncoder.getVelocity();
  }

  //zero the encoders
  public void resetEncoders() {
    leftEncoder.setPosition(0);
    rightEncoder.setPosition(0);
  }

  /** Gyro methods */

  //get angle ranging from 0 to 360
  public double getHeading() {
    return navx.getYaw();
  }

  //zero heading
  public void resetHeading() {
    navx.reset();
  }
}
