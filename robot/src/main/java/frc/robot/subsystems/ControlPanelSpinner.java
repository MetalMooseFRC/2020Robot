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

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ControlPanelSpinner extends SubsystemBase {
  
    /** 
    //initialize ports
    CANSparkMax controlPanelSpinnerMotor = new CANSparkMax(Constants.controlPanelSpinnerCANID, MotorType.kBrushless);
    CANEncoder controlPanelSpinnerEncoder = new CANEncoder(controlPanelSpinnerMotor);

  public ControlPanelSpinner() {

  }

  @Override
  public void periodic() {
      resetEncoder();
    // This method will be called once per scheduler run
  }

  /**Motor methods 

  //set the speed of the motor
  public void setSpeed(double speed) {
    controlPanelSpinnerMotor.set(speed);
  }

  //reset encoders
  public void resetEncoder() {
      controlPanelSpinnerEncoder.setPosition(0);
  }

  //get position in rotations
  public double getPosition() {
      return controlPanelSpinnerEncoder.getPosition();
  }
  */


}
