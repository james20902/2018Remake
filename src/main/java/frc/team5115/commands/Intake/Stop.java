package frc.team5115.commands.Intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.team5115.robot.Robot;

public class Stop extends Command {
    protected void execute() {
        Robot.intake.relax();
        Robot.intake.bump();
        Robot.intake.lowerIntake();
    }
    protected boolean isFinished() {
        return true;
    }
}
