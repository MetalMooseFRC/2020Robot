/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Turret extends SubsystemBase {

   
  //shooter wheel motor
  private CANSparkMax shooterMotor = new CANSparkMax(Constants.shooterMotorCANID,MotorType.kBrushless);
  private CANEncoder shooterEncoder = new CANEncoder(shooterMotor);

   
  //turret motor (shooter yaw)
  private CANSparkMax turretMotor = new CANSparkMax(Constants.turretMotorCANID, MotorType.kBrushless);
  private CANEncoder turretEncoder = new CANEncoder(turretMotor);

  //hood motor (shooter pitch)
  private CANSparkMax hoodMotor = new CANSparkMax(Constants.hoodMotorCANID, MotorType.kBrushless);
  private CANEncoder hoodEncoder = new CANEncoder(hoodMotor);
  

  //ball into shooter actuator
private CANSparkMax preloaderMotor = new CANSparkMax(Constants.preloaderMotorCANID, MotorType.kBrushless);
  public Turret() {
    preloaderMotor.setIdleMode(IdleMode.kBrake);
    resetShooterEncoder();
    resetTurretEncoder();
    resetHoodEncoder(); 
    
    shooterMotor.setIdleMode(IdleMode.kCoast);
    turretMotor.setIdleMode(IdleMode.kBrake);
    hoodMotor.setIdleMode(IdleMode.kBrake);

    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /** Shooter motor methods  */

  //reset encoders to 0
  public void resetShooterEncoder() {
      shooterEncoder.setPosition(0);
  }

  //get the speed in rpm
  public double getSpeed() {
     return shooterEncoder.getVelocity();
  }

  //set the voltage of the motor
  public void setVoltage(double volts) {
     shooterMotor.setVoltage(volts);
  }

  //set the speed of the motor (-1 to 1)
  public void setShooterSpeed(double speed) {
    shooterMotor.set(speed);
  }

  /* Turret motor methods */

  //reset turret encoder
  public void resetTurretEncoder() {
    turretEncoder.setPosition(0);
  }

  //set speed
  public void setTurretSpeed(double speed) {
    turretMotor.set(speed);
  }

  //get position in rotation
  public double getTurretPosition() {
    return turretEncoder.getPosition();
  }

    /* hood motor methods */
    
  //reset hood encoder
  public void resetHoodEncoder() {
    hoodEncoder.setPosition(0);
  }

  //set speed
  public void setHoodSpeed(double speed) {
    hoodMotor.set(speed);
  }

  //get position in rotation
  public double getHoodPosition() {
    return hoodEncoder.getPosition();
  }

  /** Engager actuator methods */

  //activate
  public void engage() {
    preloaderMotor.set(1);
  }

  //deactivate
  public void disengage() {
    preloaderMotor.set(0);
  }
  
}
