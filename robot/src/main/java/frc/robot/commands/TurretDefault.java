package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.Limelight;

public class TurretDefault extends CommandBase {
    //initialize subsystems
    Turret m_turret;
    Limelight limelight;

    DoubleSupplier joystickX;
    DoubleSupplier joystickY;
    DoubleSupplier joystickSlider;
    DoubleSupplier trigger;

    double hoodP = 0.08;


    public TurretDefault(Turret m_turret, Limelight limelight, DoubleSupplier joystickX, DoubleSupplier joystickY , DoubleSupplier joystickSlider, DoubleSupplier trigger) {
        this.m_turret = m_turret;
        this.limelight = limelight;
    
        this.joystickX = joystickX;
        this.joystickY = joystickY;
        this.joystickSlider = joystickSlider;
        this.trigger = trigger;

        addRequirements(m_turret, limelight);
    }

    @Override
    public void execute() {

        m_turret.setShooterSpeed((-joystickSlider.getAsDouble() + 1)/2);

        double hoodSpeed = joystickY.getAsDouble();
        double turretSpeed = joystickX.getAsDouble();

        System.out.println("Hood count: " + m_turret.getHoodPosition());
        System.out.println("Dist " + limelight.getDistance());

        //if no target use joystick
        if (!limelight.hasValidTarget()) {
            if (Math.abs(hoodSpeed) < Constants.minimumJoystickInput) hoodSpeed = 0;
            if (Math.abs(turretSpeed) < Constants.minimumJoystickInput) turretSpeed = 0;
    
            if (m_turret.getHoodPosition() > -Constants.hoodPositionErrorMargin && hoodSpeed > 0) hoodSpeed = 0;
            if (Constants.hoodSoftLimit - m_turret.getHoodPosition() > -Constants.hoodPositionErrorMargin && hoodSpeed < 0) hoodSpeed = 0;
            m_turret.setHoodSpeed(hoodSpeed * Constants.turretSensitivity);
            m_turret.setTurretSpeed(turretSpeed * Constants.turretSensitivity);
        } else {
            double targetHoodPos =limelight.getDistance() * -0.0364553 - 13.7447;
            m_turret.setHoodSpeed((targetHoodPos - m_turret.getHoodPosition()) * hoodP + 0.01);
        }


        if (trigger.getAsDouble() > Constants.minimumJoystickInput) {
            m_turret.engage();
        } else {
            m_turret.disengage();
        }
/** 
        //auto targetting
        if (limelight.hasValidTarget()) {
            //start spinning shooter to full power
           m_turret.setShooterSpeed(1);

            //if the target cannot be approached because of the hard limit
            if ((m_turret.getTurretPosition() > Constants.maxTurretMotorRotations || m_turret.getTurretPosition() < Constants.minTurretMotorRotations) && Math.abs(limelight.getTx()) > Constants.limelightErrorMargin) {
                
                if (limelight.getTx() > 0) {
                    //move counterclockwise
                    m_turret.setTurretSpeed(0.1);
                } else {
                    //move clockwise
                    m_turret.setTurretSpeed(-0.1);
                }
            
            //else proportional point to the target
            } else {
                m_turret.setTurretSpeed(limelight.getTx() * Constants.limelightP);
                //add turret elevate PID algorithm
            }

            if (buttonStatus.getAsBoolean() && Math.abs(m_turret.getSpeed() - Constants.highGoalShooterSpeed) < Constants.shooterSpeedErrorMargin) {
                m_turret.engage();
            } else {
                m_turret.disengage();
            }

        } else {
            //continue spinning
            m_turret.setShooterSpeed(1);

            m_turret.setElevateSpeed(joystickY.getAsDouble());
            m_turret.setTurretSpeed(joystickX.getAsDouble());

            if (buttonStatus.getAsBoolean()) {
                m_turret.engage();
            } else {
                m_turret.disengage();
            }
        
           
        } */

    }
}