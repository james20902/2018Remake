package frc.team5115.commands.Intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.team5115.robot.Robot;

public class Close extends Command {

    protected void execute(){
        Robot.intake.close();
    }
    protected boolean isFinished(){
        return true;
    }
}
