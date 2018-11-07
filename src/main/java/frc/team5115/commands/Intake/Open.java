package frc.team5115.commands.Intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.team5115.robot.Robot;

public class Open extends Command {

    protected void execute(){ Robot.intake.open(); }
    protected boolean isFinished(){ return true; }
}
