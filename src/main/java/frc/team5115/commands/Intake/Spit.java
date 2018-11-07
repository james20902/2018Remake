package frc.team5115.commands.Intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.team5115.Constants;
import frc.team5115.robot.Robot;

public class Spit extends Command {
    protected void execute() {
        Robot.intake.relax();
        Robot.intake.intake(-Constants.INTAKE_SPEED);
        Robot.intake.lowerIntake();
    }
    protected boolean isFinished() {
        return true;
    }
}
