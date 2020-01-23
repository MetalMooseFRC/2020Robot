package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;

public class DrivetrainTarget extends CommandBase {
private final Drivetrain m_drive;
private final Limelight m_limelight;

//arbitrary and will be moved to Constants in a more official command
private final double testP = -0.02;

    public DrivetrainTarget(Drivetrain m_drive, Limelight m_limelight) {
        this.m_drive = m_drive;
        this.m_limelight = m_limelight;
    }

    @Override
    public void execute() {
        //System.out.println("tv " + m_limelight.hasValidTarget());
        //System.out.println("tx " + m_limelight.getTx());

        //turn towards the target proportional to the error
        if (m_limelight.hasValidTarget()) m_drive.arcadeDrive(0, m_limelight.getTx() * testP);

        //System.out.println("Calculated distance: " + m_limelight.getDistance());
    }
}