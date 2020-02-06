package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;

public class DrivetrainTarget extends CommandBase {
private final Drivetrain m_drive;
private final Limelight m_limelight;

boolean turningCounterClockwise = true;

//arbitrary and will be moved to Constants in a more official command
private final double testP = -0.018;

    public DrivetrainTarget(Drivetrain m_drive, Limelight m_limelight) {
        this.m_drive = m_drive;
        this.m_limelight = m_limelight;
    }

    @Override
    public void execute() {


        //turn towards the target proportional to the error
        if (m_limelight.hasValidTarget()) {

            if (180 - Math.abs(m_drive.getHeading()) < Math.abs(m_limelight.getTx())) System.out.println("Must turn around to shoot");
         m_drive.arcadeDrive(0, m_limelight.getTx() * testP);


        // System.out.println("tan PI " + Math.tan(m_limelight.getTy()*Math.PI/180));
         //System.out.println("Calculated distance: " + m_limelight.getDistance());
        } /**else {
            if (m_drive.getHeading() > 175) {
                turningCounterClockwise = true;
            } else if (m_drive.getHeading() < -175) {
                turningCounterClockwise = false;
            }

            if (turningCounterClockwise) {
                m_drive.arcadeDrive(0, 0.3);
            } else {
                m_drive.arcadeDrive(0, -0.3);
            } 
        } */
    }
}