/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ColorSensor extends SubsystemBase {
  //Initialize color sensor in roborio port
  private ColorSensorV3 colorSensor = new ColorSensorV3(Port.kOnboard);
  private ColorMatch colorMatcher = new ColorMatch();



  public ColorSensor() {
    //add the target colors to the matching pool
    colorMatcher.addColorMatch(Constants.kBlueTarget);
    colorMatcher.addColorMatch(Constants.kGreenTarget);
    colorMatcher.addColorMatch(Constants.kRedTarget);
    colorMatcher.addColorMatch(Constants.kYellowTarget);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  //return the char of the detected color to compare to FMS data
  public char getColorChar() {
    //get detected color from sensor
    Color likelyColor = colorSensor.getColor();
    //match it to the closest color given the target colors
    ColorMatchResult result = colorMatcher.matchClosestColor(likelyColor);

    //return the appropriate character
    if (result.color == Constants.kBlueTarget) {
        System.out.println("B");
        return 'B';
    } else if (result.color == Constants.kGreenTarget) {
        System.out.println("G");
        return 'G';
    } else if (result.color == Constants.kRedTarget) {
        System.out.println("R");
    } else if (result.color == Constants.kYellowTarget) {
        System.out.println("Y");
    } 

    return ' ';
    
  }

  //print the raw rgb values of detected color
  public void getCalibrationValues() {
    //detected color
    Color likelyColor = colorSensor.getColor();

    //print Color class rgb values
    System.out.println("R: " + likelyColor.red);
    System.out.println("G: " + likelyColor.green);
    System.out.println("B: " + likelyColor.blue);
  }

}
