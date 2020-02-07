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

import edu.wpi.first.wpilibj.DriverStation;
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

  //return the associated integer of the 
  public int FMSColorCharToNum() {
    //get FMS data
    String gameData = DriverStation.getInstance().getGameSpecificMessage();

    //only if the gamedata has been sent
    if (gameData.length() > 0) {
    //return the appropriate integer
    if (gameData == "R") {
      return 0;
    } else if (gameData == "Y") {
      return 1;
    } else if (gameData  == "B") {
      return 2;
    } else if (gameData == "G") {
      return 3;
    }
    }

    //no color gotten
    return -1;
    
  }

  //return the number of the detected color. Enumeration is in order going clockwise
  public int getColorNum() {
    //get detected color from sensor
    Color likelyColor = colorSensor.getColor();
    //match it to the closest color given the target colors
    ColorMatchResult result = colorMatcher.matchClosestColor(likelyColor);

    //return the appropriate character
    if (result.color == Constants.kBlueTarget) {
        return 2;
    } else if (result.color == Constants.kGreenTarget) {
        return 3;
    } else if (result.color == Constants.kRedTarget) {
        return 0;
    } else if (result.color == Constants.kYellowTarget) {
        return 1;
    } 

    //no color gotten
    return -1;
    
  }

  public int getDistanceToCorrectPanel() {
    int neededColor = FMSColorCharToNum();
    int currentColor = getColorNum();

    //this is in panels. Positive is clockwise
    int difference = neededColor - (currentColor + Constants.colorDetectionOffset) % 4;

    return difference;

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
