package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class IntakeDefault extends CommandBase {
    private Intake m_intake;
    private BooleanSupplier inButtonStatus;
    private BooleanSupplier outButtonStatus;
    private DoubleSupplier trigger;


    public IntakeDefault(Intake m_intake, BooleanSupplier inButtonStatus, BooleanSupplier outButtonStatus) {
        this.m_intake = m_intake;
        this.inButtonStatus = inButtonStatus;
        this.outButtonStatus = outButtonStatus;

        addRequirements(m_intake);
    }

    @Override
    public void execute() {

        //if intake switch is on/forward
        if (inButtonStatus.getAsBoolean()) {
            //only intake if there are less than 5 balls and no jamming
            if (m_intake.isLimitPressed() /*|| m_intake.getCurrentDraw() > Constants.intakeMotorAmpLimit */) {
                m_intake.outtake();
            } else {
                m_intake.intake();
            }
        //if intake switch is neutral
        } else if (outButtonStatus.getAsBoolean()) {
            m_intake.outtake();
        //if intake switch is off/backwards
        } else {
            m_intake.setSpeed(0);
        } 

    }
}