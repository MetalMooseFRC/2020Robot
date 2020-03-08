/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  //initialize ports
    CANSparkMax intakeMotor = new CANSparkMax(Constants.intakeMotorCANID, MotorType.kBrushless);
    CANSparkMax storageMotor = new CANSparkMax(Constants.storageMotorCANID, MotorType.kBrushless);

   /** DigitalInput limitSwitch = new DigitalInput(Constants.intakeLimitSwitchPort);

     */
    DoubleSolenoid frontArm = new DoubleSolenoid(Constants.frontArmForwardPort, Constants.frontArmReversePort);
    //DoubleSolenoid backArm = new DoubleSolenoid(Constants.backArmForwardPort, Constants.backArmReversePort);

  public Intake() {
    intakeMotor.setIdleMode(IdleMode.kBrake);
    storageMotor.setIdleMode(IdleMode.kBrake);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**Motor methods */

  //intake balls at a default speed
  public void intake() {
      intakeMotor.set(Constants.intakeSpeed);
      storageMotor.set(Constants.storageSpeed);
  }

    //outtake balls at a default speed
    public void outtake() {
      intakeMotor.set(-Constants.intakeSpeed);
      storageMotor.set(-Constants.storageSpeed);
  }

  //set the speed of the intake
  public void setSpeed(double speed) {
      intakeMotor.set(speed);
      storageMotor.set(speed);
  }

  //get the amp draw
  public double getCurrentDraw() {
      return intakeMotor.getOutputCurrent();
  }

  /**Ball management methods */
  
  //is the 5th ball detector pressed
  public boolean isLimitPressed() {
      return false;//limitSwitch.get();
  }

  /**Arm actuators */

  //front arm down
  public void frontArmDown() {
    frontArm.set(Value.kForward);
  }
  //front arm up
  public void frontArmUp() {
    frontArm.set(Value.kReverse);
  }

  //back arm down
  public void backArmDown() {
    //backArm.set(Value.kForward);
  }
  //back arm up
  public void backArmUp() {
    //backArm.set(Value.kReverse);
  }


}
