/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
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

  public Drivetrain() {
    //reset encoders on startup
    leftEncoder.setPosition(0);
    rightEncoder.setPosition(0);

    //set conversion factors
    leftEncoder.setPositionConversionFactor(Constants.encoderConversionFactor);
    leftEncoder.setVelocityConversionFactor(Constants.encoderConversionFactor);

    rightEncoder.setPositionConversionFactor(Constants.encoderConversionFactor);
    rightEncoder.setVelocityConversionFactor(Constants.encoderConversionFactor);
  }

  public void arcadeDrive(double speed, double turn) {
    drive.arcadeDrive(speed, turn);
  }

  public double getLeftSpeed() {
    return leftEncoder.getVelocity();
  }

  public double getRightSpeed() {
    return rightEncoder.getVelocity();
  }

  public void shiftHighGear() {
    leftShifter.set(Value.kReverse);
    rightShifter.set(Value.kReverse);
  }

  public void shiftLowGear() {
    leftShifter.set(Value.kForward);
    rightShifter.set(Value.kForward);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
