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
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ControlPanelSpinner extends SubsystemBase {
  /** 
    //initialize ports
    CANSparkMax controlPanelSpinnerMotor = new CANSparkMax(Constants.controlPanelSpinnerCANID, MotorType.kBrushless);
    CANEncoder controlPanelSpinnerEncoder = new CANEncoder(controlPanelSpinnerMotor);

    DoubleSolenoid spinnerEngager = new DoubleSolenoid(Constants.spinnerEngagerForwardPort, Constants.spinnerEngagerReversePort);
  */
  public ControlPanelSpinner() {
    resetEncoder();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**Motor methods */

  //set the speed of the motor
  public void setSpeed(double speed) {
    //controlPanelSpinnerMotor.set(speed);
  }

  //reset encoders
  public void resetEncoder() {
      //controlPanelSpinnerEncoder.setPosition(0);
  }

  //get position in rotations
  public double getPosition() {
      return 0;//controlPanelSpinnerEncoder.getPosition();
  }

  /**Actuator methods */
  
  //engage spinner
  public void engage() {
    //spinnerEngager.set(Value.kForward);
  }

  //disengage spinner
  public void disengage() {
    //spinnerEngager.set(Value.kReverse);
  }

}
