/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Limelight extends SubsystemBase {
  //initialize the limeligh network table
  NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight");

  public Limelight() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
  }

  //get the x error between the crosshair and target
  public double getTx() {
      return limelight.getEntry("tx").getDouble(0.0);
  }

  //get the y error between the crosshair and target
  public double getTy() {
    return limelight.getEntry("ty").getDouble(0.0); 
  }

  //does the limelight see a viable target
  public boolean hasValidTarget() {
    return limelight.getEntry("tv").getDouble(0.0) == 1;
  }

  //calculate the distance based on trig
  public double getDistance() {
      return (Constants.targetHeight - Constants.limelightHeight)/Math.tan(getTy()*Math.PI/180);
  }

}
