package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Turret;

public class TurretShootForTime extends CommandBase {
    Turret m_turret;

    //time translated from s to code counts
    double time;

    double count;

    public TurretShootForTime(double time, Turret m_subsystem) {
        this.m_turret = m_subsystem;
        this.time = time * 50;

        addRequirements(m_subsystem);
    }

    
    @Override
    public void initialize() {
        count = 0;

        m_turret.setShooterSpeed(1);
        
    }

    @Override
    public void execute() {
        System.out.println(m_turret.getSpeed());

        m_turret.setShooterSpeed(1);

        if (m_turret.getSpeed() > Constants.highGoalShooterSpeed) {
            m_turret.engage();
            count++;
        }
    }

    @Override
    public boolean isFinished() {
        //return true when the error is negligible
        return time == count;
    }

    @Override
    public void end(boolean interrupted) {
        m_turret.disengage();
        System.out.println("shooting done");
    }



}