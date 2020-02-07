/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class RouletteStorage extends SubsystemBase {

  /**  
    //initialize ports
    CANSparkMax rouletteStorageMotor = new CANSparkMax(Constants.rouletteStorageMotorCANID, MotorType.kBrushless);
    CANSparkMax preloaderMotor = new CANSparkMax(Constants.preloaderMotorCANID, MotorType.kBrushless);

  public RouletteStorage() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**Motor methods 

  //set the speed of the intake
  public void setSpeed(double speed) {
      rouletteStorageMotor.set(speed);
  }

  //get the amp draw
  public double getCurrentDraw() {
      return rouletteStorageMotor.getOutputCurrent();
  }

  //preload ball into shooter
  public void preload() {
    preloaderMotor.set(Constants.preloadSpeed);
  }

  */


}