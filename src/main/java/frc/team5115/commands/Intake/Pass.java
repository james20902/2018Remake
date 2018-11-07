package frc.team5115.commands.Intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.team5115.robot.Robot;

public class Pass extends Command {

    public Pass(){
        requires(Robot.intake);
    }
    protected void execute() {
        Robot.intake.grip();
        Robot.intake.intake(0.2);
        Robot.intake.liftIntake();
    }
    protected boolean isFinished() {
        return false;
    }
}
