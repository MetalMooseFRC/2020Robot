package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DrivetrainDrive extends CommandBase {
private final Drivetrain m_drive;
private double speed;
private double turn;

    DrivetrainDrive(double speed, double turn, Drivetrain m_subsystem) {
        m_drive = m_subsystem;
        addRequirements(m_subsystem);

        this.speed = speed;
        this.turn = turn;
    }

    @Override
    public void execute() {
        m_drive.arcadeDrive(speed, turn);

    }
}