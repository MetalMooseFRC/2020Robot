package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Turret;

public class TurretHoodToDistance extends CommandBase {
    Turret m_turret;

    double distance;

    PIDController turretPID = new PIDController(Constants.hoodPositionP, Constants.hoodPositionI, Constants.hoodPositionD);

    public TurretHoodToDistance(double distance, Turret m_subsystem) {
        this.m_turret = m_subsystem;
        this.distance = distance;

        addRequirements(this.m_turret);
    }

    
    @Override
    public void initialize() {
        
    }

    @Override
    public void execute() {
        //calculate PID control based on difference between encoders and distance to travel
        m_turret.setHoodSpeed(turretPID.calculate(m_turret.getHoodPosition(), distance));
        System.out.println("Error " + turretPID.getPositionError());
    }

    @Override
    public boolean isFinished() {
        //return true when the error is negligible
        return Math.abs(turretPID.getPositionError()) < Constants.hoodPositionErrorMargin;
    }

    @Override
    public void end(boolean interrupted) {
        //stop motors
        m_turret.setHoodSpeed(0);
    }



}