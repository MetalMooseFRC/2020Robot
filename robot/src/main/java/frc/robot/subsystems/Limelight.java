/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Limelight extends SubsystemBase {
  NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight");

  public Limelight() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
  }

  public double getTx() {
      return limelight.getEntry("tx").getDouble(0.0);
  }

  public double getTy() {
    return limelight.getEntry("ty").getDouble(0.0); 
  }

  public boolean hasValidTarget() {
    return limelight.getEntry("tv").getDouble(0.0) == 1;
  }

  public double getDistance() {
      return (Constants.targetHeight - Constants.limelightHeight)/Math.tan(getTy());
  }

}
