package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

public class DrivetrainDriveDistance extends CommandBase {
private final Drivetrain m_drive;
//in meters
double distance;
//on -1 to 1 scale
double speed;

PIDController drivePID = new PIDController(Constants.driveDistanceP, Constants.driveDistanceI, Constants.driveDistanceD);

    public DrivetrainDriveDistance(double distance, Drivetrain m_subsystem) {
        m_drive = m_subsystem;
        addRequirements(m_subsystem);

        this.distance = distance;
        this.speed = Constants.defaultAutoSpeed;

    }

    public DrivetrainDriveDistance(double distance, double speed, Drivetrain m_subsystem) {
        m_drive = m_subsystem;
        addRequirements(m_subsystem);

        this.distance = distance;
        this.speed = speed;
    }
    
    @Override
    public void initialize() {
        //reset the drive encoders
        m_drive.resetEncoders();
    }

    @Override
    public void execute() {
        //calculate PID control based on difference between encoders and distance to travel
        m_drive.arcadeDrive(drivePID.calculate(m_drive.getLeftEncoder(), distance), 0);

        System.out.println(drivePID.getPositionError());
    }

    @Override
    public boolean isFinished() {
        //return true when the error is negligible
        return Math.abs(drivePID.getPositionError()) < Constants.driveDistancePIDErrorMargin;
    }

    @Override
    public void end(boolean interrupted) {
        //stop motors
        m_drive.arcadeDrive(0, 0);
    }



}