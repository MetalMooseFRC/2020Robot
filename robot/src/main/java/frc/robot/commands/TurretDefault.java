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
    BooleanSupplier buttonStatus;


    public TurretDefault(Turret m_turret, Limelight limelight, DoubleSupplier joystickX, DoubleSupplier joystickY, BooleanSupplier buttonStatus) {
        this.m_turret = m_turret;
        this.limelight = limelight;
    
        this.joystickX = joystickX;
        this.joystickY = joystickY;
        this.buttonStatus = buttonStatus;

        addRequirements(m_turret, limelight);
    }

    @Override
    public void execute() {

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
           
        }

    }
}