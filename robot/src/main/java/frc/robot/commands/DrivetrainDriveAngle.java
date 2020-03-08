package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

public class DrivetrainDriveAngle extends CommandBase {
private final Drivetrain m_drive;
//in degrees
double angle;


PIDController drivePID = new PIDController(Constants.driveAngleP, Constants.driveAngleI, Constants.driveAngleD);

    public DrivetrainDriveAngle(double angle, Drivetrain m_subsystem) {
        m_drive = m_subsystem;
        addRequirements(m_subsystem);

        this.angle = angle;

    }
    
    @Override
    public void initialize() {
        //reset the drive encoders
        m_drive.resetHeading();
    }

    @Override
    public void execute() {
        //calculate PID control based on difference between encoders and distance to travel
        m_drive.arcadeDrive(0, -drivePID.calculate(m_drive.getHeading(), angle));
    }

    @Override
    public boolean isFinished() {
        //return true when the error is negligible
        return Math.abs(drivePID.getPositionError()) < Constants.driveAnglePIDErrorMargin;
    }

    @Override
    public void end(boolean interrupted) {
        //stop motors
        m_drive.arcadeDrive(0, 0);
    }



}