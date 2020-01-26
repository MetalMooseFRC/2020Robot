/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    //initialize ports
    //CANSparkMax intakeMotor = new CANSparkMax(Constants.intakeMotorCANID, MotorType.kBrushless);
    DigitalInput limitSwitch = new DigitalInput(Constants.intakeLimitSwitchPort);

   // Solenoid engagerSolenoid = new Solenoid(Constants.intakeEngagerChannel);


  public Intake() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**Motor methods */

  public void intake() {
      //intakeMotor.set(Constants.intakeSpeed);
  }

  public void setSpeed(double speed) {
      //intakeMotor.set(speed);
  }

  public double getCurrentDraw() {
      //return intakeMotor.getOutputCurrent();
      return 0;
  }

  /**Ball management methods */
  public boolean isLimitPressed() {
      return limitSwitch.get();
  }

  /** 
  public void engageIntake() {
    engagerSolenoid.set(true);
  }

  public void disengageIntake() {
    engagerSolenoid.set(false);
  } */


}
