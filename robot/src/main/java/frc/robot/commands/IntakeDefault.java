package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class IntakeDefault extends CommandBase {
    Intake m_intake;

    public IntakeDefault(Intake m_intake) {
        this.m_intake = m_intake;
    }

    @Override
    public void execute() {

        //only intake if there are less than 5 balls and no jamming
        if (m_intake.isLimitPressed()) {
            m_intake.setSpeed(0);
        } else if (m_intake.getCurrentDraw() > Constants.intakeMotorAmpLimit) {
            m_intake.setSpeed(-Constants.intakeSpeed);
        } else {
         m_intake.intake();
        }

    }
}