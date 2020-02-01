/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorMatch;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    /**Control System */
    public static final int driverStickPort = 0;
    
    //control and sensitivity constants
    public static final double joystickSpeedConstant = 1.2;
    public static final double joystickTurnConstant = 1.4;
    public static final double minimumJoystickInput = 0.1;
    
    /**Drivetrain */

    //drive motors
    public static final int frontLeftMotorCANID = 1;
    public static final int topLeftMotorCANID = 2;
    public static final int backLeftMotorCANID = 3;

    public static final int frontRightMotorCANID = 4;
    public static final int topRightMotorCANID = 5;
    public static final int backRightMotorCANID = 6;

    //solenoid shifter
    public static final int leftShifterForwardChannel = 0;
    public static final int leftShifterReverseChannel = 1;
    public static final int rightShifterForwardChannel = 2;
    public static final int rightShifterReverseChannel = 3;

    //drivetrain measurements and conversions
    public static final double drivetrainWidth = Units.inchesToMeters(29);

    //distance the wheels travel in 1 rotation of neo in meters
    public static final double drivetrainEncoderConversionFactor = Units.inchesToMeters(6 * Math.PI) / 13;
    //speed at which to switch between gears?
    public static final double highGearThreshhold = 10;

    //distance PID constants
    public static final double driveDistanceP = 0.01;
    public static final double driveDistanceI = 0;
    public static final double driveDistanceD = 0;

    //allowed PID error in meters
    public static final double driveDistancePIDErrorMargin = 0.05;

    //allowed PID error in degrees
    public static final double driveAnglePIDErrorMargin = 2;

    //default speed of drivetrain for auto
    public static final double defaultAutoSpeed = 0.6;


    /** Intake */
    //motor and sensor ports
    public static final int intakeMotorCANID = 7;
    public static final int intakeLimitSwitchPort = 0;
    
    //amp limit to determine stall or jam
    public static final double intakeMotorAmpLimit = 35;

    //default speed to intake balls
    public static final double intakeSpeed = -0.4;

    //solenoid channel
    public static final int intakeEngagerChannel = 4;

    /** Shooter */

    //shooter motor
    public static final int shooterMotorCANID = 8;

    /** Limelight */

    //distance calculation factors should be in inches
    public static final double limelightHeight = 24.5;
    public static final double targetHeight = 81;

    /**Color Sensor */
      //example code values for control panel swatches
    public static Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    public static Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    public static Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    public static Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
}
