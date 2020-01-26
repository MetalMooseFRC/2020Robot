/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.util.Units;

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

    public static final double drivetrainEncoderConversionFactor = Units.inchesToMeters(6 * Math.PI) / 17;
    public static final double highGearThreshhold = 10;

    /** Intake */
    //motor and sensor ports
    public static final int intakeMotorCANID = 7;
    public static final int intakeLimitSwitchPort = 0;
    
    public static final double intakeMotorAmpLimit = 35;

    public static final double intakeSpeed = -0.4;

    //solenoid
    public static final int intakeEngagerChannel = 4;

    /** Shooter */

    //shooter motor
    public static final int shooterMotorCANID = 8;

    /** Limelight */

    //distance calculation factors should be in inches
    public static final double limelightHeight = 24.5;
    public static final double targetHeight = 81;
}
