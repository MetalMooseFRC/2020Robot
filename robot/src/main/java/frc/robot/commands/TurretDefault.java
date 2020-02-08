package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;

public class TurretDefault extends CommandBase {
    //initialize subsystems
    Turret m_turret;
    Drivetrain m_drive;
    Limelight limelight;

    boolean isTurningClockwise;
    boolean hasStatedSearching;

    public TurretDefault(Turret m_turret, Limelight limelight, Drivetrain m_drive) {
        this.m_turret = m_turret;
        this.m_drive = m_drive;
        this.limelight = limelight;

        //don't require the drive, just use it's gyro
        addRequirements(m_turret, limelight);
    }

    @Override
    public void execute() {

        if (limelight.hasValidTarget()) {
            hasStatedSearching = false;

            //if the target cannot be approached because of the hard limit
            if ((m_turret.getTurretPosition() > Constants.maxTurretMotorRotations || m_turret.getTurretPosition() < Constants.minTurretMotorRotations) && Math.abs(limelight.getTx()) < Constants.limelightErrorMargin) {
                //move turret but with opposite to last searching
                if (!isTurningClockwise) {
                    m_turret.setTurretSpeed(0.1);
                } else {
                    m_turret.setTurretSpeed(-0.1);
                }
                
            }

            m_turret.setTurretSpeed(limelight.getTx() * Constants.limelightP);

        } else {
            
            if (!hasStatedSearching) {
                //start searching in the correct direction depending on gyro
                if (m_drive.getHeading() < 0) {
                    isTurningClockwise = true;
                } else {
                    isTurningClockwise = false;
                }
                hasStatedSearching = true;
            }

            //switch the searching direction after it's hit the hard limit
            if (m_turret.getTurretPosition() > Constants.maxTurretMotorRotations) isTurningClockwise = false;
            if (m_turret.getTurretPosition() < Constants.minTurretMotorRotations) isTurningClockwise = true;

            //move turret
            if (isTurningClockwise) {
                m_turret.setTurretSpeed(0.1);
            } else {
                m_turret.setTurretSpeed(-0.1);
            }

        }

    }
}