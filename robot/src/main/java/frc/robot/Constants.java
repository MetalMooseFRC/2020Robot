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

    //drivetrain measurements and conversions
    public static final double drivetrainWidth = Units.inchesToMeters(29);

    //distance the wheels travel in 1 rotation of neo in meters
    public static final double drivetrainEncoderConversionFactor = Units.inchesToMeters(6 * Math.PI) / 13;

    //distance PID constants
    public static final double driveDistanceP = 0.01;
    public static final double driveDistanceI = 0;
    public static final double driveDistanceD = 0;

    //allowed PID error in meters
    public static final double driveDistancePIDErrorMargin = 0.05;

    //distance PID constants
    public static final double driveAngleP = 0.05;
    public static final double driveAngleI = 0;
    public static final double driveAngleD = 0;

    //allowed PID error in degrees
    public static final double driveAnglePIDErrorMargin = 2;

    //default speed of drivetrain for auto
    public static final double defaultAutoSpeed = 0.6;


    /** Intake */
    //motor, pneumatics, and sensor ports
    public static final int intakeMotorCANID = 7;

    public static final int frontArmForwardPort = 0;
    public static final int frontArmReversePort = 1;
    public static final int backArmForwardPort = 2;
    public static final int backArmReversePort = 3;

    public static final int intakeLimitSwitchPort = 0;
    
    //amp limit to determine stall or jam
    public static final double intakeMotorAmpLimit = 35;

    //default speed to intake balls
    public static final double intakeSpeed = -0.5;


    /** Roulette Storage */
    public static final int rouletteStorageMotorCANID = 8;
    
    //public static final int preloaderMotorCANID = 9;

    /** Turret */
    //motor and pneumatic ports
    public static final int shooterMotorCANID = 10;
    public static final int turretMotorCANID = 11;
    public static final int elevateMotorCANID = 12;

    public static final int shooterEngagerForwardPort = 4;
    public static final int shooterEngagerReversePort = 5;

    //soft max limit
    public static final double maxTurretMotorRotations = 10;
    //soft min limit
    public static final double minTurretMotorRotations = -10;

    //shooter speeds in rpm
    public static final double highGoalShooterSpeed = 200;
    public static final double lowGoalShooterSpeed = 100;

    //shooter speed margin
    public static final double shooterSpeedErrorMargin = 1;

    /** Lift */

    //lifter motors
    public static final int leftLifterMotorCANID = 13;
    public static final int rightLifterMotorCANID = 14;


    /** Contol Panel */
    //motor and pneumatic ports
    public static final int controlPanelSpinnerCANID = 15;
 
    public static final int spinnerEngagerForwardPort = 6;
    public static final int spinnerEngagerReversePort = 7;

    //number of rotations of the neo 550 with 12:1 gearing and 2.25 in wheel for one panel
    public static final double rotationsPerColorPanel = 200/3;

    //control panel PID constants
    public static final double controlPanelP = 0.3;
    public static final double controlPanelI = 0;
    public static final double controlPanelD = 0;

    public static final double controlPanelPIDErrorMargin = 1;

    /** Limelight */

    //distance calculation factors should be in inches
    public static final double limelightHeight = 24.5;
    public static final double targetHeight = 81;

    public static final double limelightErrorMargin = 1;
    public static final double limelightP = 0.2;

    /**Color Sensor */
    //offset of panels between our color detection and the control panel's.
    //positive indicates that it is x panels moving clockwise from our sensor and the control panel's
    public static final int colorDetectionOffset = 2;
    
      //example code values for control panel swatches
    public static Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    public static Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    public static Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    public static Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
}
