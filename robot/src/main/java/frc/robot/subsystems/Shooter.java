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

public class Shooter extends SubsystemBase {

  //private CANSparkMax shooterMotor = new CANSparkMax(Constants.shooterMotorCANID,MotorType.kBrushless);

  //private CANEncoder shooterEncoder = new CANEncoder(shooterMotor);


  public Shooter() {
    resetEncoder();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void resetEncoder() {
      //shooterEncoder.setPosition(0);
  }

  public void getSpeed() {
     // shooterEncoder.getVelocity();
  }

  public void setVoltage(double volts) {
     // shooterMotor.setVoltage(volts);
  }

  public void setSpeed(double speed) {
    //shooterMotor.set(speed);
  }


}
