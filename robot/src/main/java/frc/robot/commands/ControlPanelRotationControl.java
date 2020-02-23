package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ControlPanelSpinner;

public class ControlPanelRotationControl extends CommandBase {
    private final ControlPanelSpinner m_controlPanel;

    double rotationsToSpin;

    PIDController controlPanelPID = new PIDController(Constants.controlPanelP, Constants.controlPanelI, Constants.controlPanelD);

    public ControlPanelRotationControl(ControlPanelSpinner m_controlPanel) {
       this.m_controlPanel = m_controlPanel;
       addRequirements(m_controlPanel);
    }
    
    @Override
    public void initialize() {
       //reset motor encoder
       m_controlPanel.resetEncoder();

       //get the number of motor rotations
       rotationsToSpin =  Constants.rotationsPerColorPanel * 8 * 3.5;
    }

    @Override
    public void execute() {
        //use PID to get the output speed
       m_controlPanel.setSpeed(controlPanelPID.calculate(m_controlPanel.getPosition(), rotationsToSpin));
    }

    @Override
    public boolean isFinished() {
        //return true when the rotations are done
        return Math.abs(rotationsToSpin - m_controlPanel.getPosition()) < Constants.controlPanelPIDErrorMargin;
    }

    @Override
    public void end(boolean interrupted) {
        //stop motor
        m_controlPanel.setSpeed(0);
    }



}