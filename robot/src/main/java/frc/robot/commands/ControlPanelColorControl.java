package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.ControlPanelSpinner;

public class ControlPanelColorControl extends CommandBase {
    private final ControlPanelSpinner m_controlPanel;
    private final ColorSensor m_colorSensor;

    private double rotationsToSpin; 

    PIDController controlPanelPID = new PIDController(Constants.controlPanelP, Constants.controlPanelI, Constants.controlPanelD);

    public ControlPanelColorControl(ControlPanelSpinner m_controlPanel, ColorSensor m_colorSensor) {
       this.m_controlPanel = m_controlPanel;
       this.m_colorSensor = m_colorSensor;
       addRequirements(m_controlPanel, m_colorSensor);
    }
    
    @Override
    public void initialize() {
       //reset motor encoder
       m_controlPanel.resetEncoder();

       //get the number of panels needed to shift
       rotationsToSpin = m_colorSensor.getDistanceToCorrectPanel() * Constants.rotationsPerColorPanel;
    }

    @Override
    public void execute() {
        //use PID to get the output speed
       m_controlPanel.setSpeed(controlPanelPID.calculate(m_controlPanel.getPosition(), rotationsToSpin));
    }

    @Override
    public boolean isFinished() {
        //return true when the color is correct
        return m_colorSensor.FMSColorCharToNum() == m_colorSensor.getColorNum() ;
    }

    @Override
    public void end(boolean interrupted) {
        //stop motor
        m_controlPanel.setSpeed(0);
    }



}