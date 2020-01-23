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

  //example code values for control panel swatches
  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  public ColorSensor() {
    //add the target colors to the matching pool
    colorMatcher.addColorMatch(kBlueTarget);
    colorMatcher.addColorMatch(kGreenTarget);
    colorMatcher.addColorMatch(kRedTarget);
    colorMatcher.addColorMatch(kYellowTarget);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public char getColorChar() {
    //get detected color from sensor
    Color likelyColor = colorSensor.getColor();
    //match it to the closest color given the target colors
    ColorMatchResult result = colorMatcher.matchClosestColor(likelyColor);

    //return the appropriate character
    if (result.color == kBlueTarget) {
        System.out.println("B");
        return 'B';
    } else if (result.color == kGreenTarget) {
        System.out.println("G");
        return 'G';
    } else if (result.color == kRedTarget) {
        System.out.println("R");
    } else if (result.color == kYellowTarget) {
        System.out.println("Y");
    } 

    return ' ';
    
  }

  public void getCalibrationValues() {
    //detected color
    Color likelyColor = colorSensor.getColor();

    System.out.println("R: " + likelyColor.red);
    System.out.println("G: " + likelyColor.green);
    System.out.println("B: " + likelyColor.blue);
  }

}
