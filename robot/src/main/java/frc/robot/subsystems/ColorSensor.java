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
  private ColorSensorV3 colorSensor = new ColorSensorV3(Port.kOnboard);
  private ColorMatch colorMatcher = new ColorMatch();

  public ColorSensor() {
    colorMatcher.addColorMatch(Color.kAqua);
    colorMatcher.addColorMatch(Color.kLime);
    colorMatcher.addColorMatch(Color.kRed);
    colorMatcher.addColorMatch(Color.kYellow);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void getColorChar() {
    Color likelyColor = colorSensor.getColor();
    ColorMatchResult detectedColor = colorMatcher.matchClosestColor(likelyColor);

    if (detectedColor.color.equals(Color.kBlue)) {
        System.out.println("B");
    } else if (detectedColor.color.equals(Color.kGreen)) {
        System.out.println("G");
    } else if (detectedColor.color.equals(Color.kRed)) {
        System.out.println("R");
    } else if (detectedColor.color.equals(Color.kYellow)) {
        System.out.println("Y");
    } 
    


  }

}
