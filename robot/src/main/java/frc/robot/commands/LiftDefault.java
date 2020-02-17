package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Lift;

public class LiftDefault extends CommandBase {
    private Lift m_lift;

    private DoubleSupplier joystickY;
    private DoubleSupplier joystickX;
    private BooleanSupplier buttonStatus;

    public LiftDefault(Lift m_lift, DoubleSupplier joystickY, DoubleSupplier joystickX, BooleanSupplier buttonStatus) {
        this.m_lift = m_lift;
        this.joystickX = joystickX;
        this.joystickY = joystickY;
        this.buttonStatus = buttonStatus;

        addRequirements(m_lift);
    }

    @Override
    public void execute() {
        //winch mode
        if (buttonStatus.getAsBoolean()) {
        
        //scissor lift mode
        } else {

        }
    }
}