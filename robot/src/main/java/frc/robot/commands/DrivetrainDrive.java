package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DrivetrainDrive extends CommandBase {
private final Drivetrain m_drive;
private DoubleSupplier speed;
private DoubleSupplier turn;

private BooleanSupplier buttonStatus; 

private double speedMultiplier;

    public DrivetrainDrive(DoubleSupplier speed, DoubleSupplier turn, BooleanSupplier buttonStatus, Drivetrain m_subsystem) {
        m_drive = m_subsystem;
        addRequirements(m_subsystem);

        this.speed = speed;
        this.turn = turn;
        this.buttonStatus = buttonStatus;
    }

    @Override
    public void execute() {

        //reverse direction if button pressed
        if (buttonStatus.getAsBoolean()) speedMultiplier = -1;
        else speedMultiplier = 1;

        m_drive.arcadeDrive(speed.getAsDouble() *speedMultiplier, turn.getAsDouble());

    }
}