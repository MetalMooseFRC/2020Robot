/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

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

    public static final double encoderConversionFactor = 6 * Math.PI;
    public static final double highGearThreshhold = 10;

}
