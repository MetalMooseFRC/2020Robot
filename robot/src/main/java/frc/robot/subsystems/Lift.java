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

public class Lift extends SubsystemBase {
/** 
    //initialize motor ports
    private CANSparkMax rightLifterMotor = new CANSparkMax(Constants.rightLifterMotorCANID, MotorType.kBrushless);
    private CANSparkMax leftLifterMotor = new CANSparkMax(Constants.leftLifterMotorCANID, MotorType.kBrushless);

    //motor encoders
    private CANEncoder rightLifterEncoder = new CANEncoder(rightLifterMotor);
    private CANEncoder leftLifterEncoder = new CANEncoder(leftLifterMotor);


  public Lift() {
    //reset encoders
    resetEncoders();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**Motor methods 

  //set the speed of the lifter
  public void setSpeed(double speed) {
      rightLifterMotor.set(-speed);
      leftLifterMotor.set(speed);
  }

  //get left encoder
  public double getLeftEncoder() {
    return leftLifterEncoder.getPosition();
  }

    //get right encoder
    public double getRightEncoder() {
      return rightLifterEncoder.getPosition();
    }

    //reset encoders
    public void resetEncoders() {
      rightLifterEncoder.setPosition(0);
      leftLifterEncoder.setPosition(0);
    }
 */
  

}

