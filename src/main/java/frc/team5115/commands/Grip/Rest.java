package frc.team5115.commands.Grip;

import edu.wpi.first.wpilibj.command.Command;
import frc.team5115.robot.Robot;

public class Rest extends Command {
    public Rest(){
        requires(Robot.grip);
    }
    protected void initialize() {
        Robot.grip.stop();
    }
    protected boolean isFinished() {
        return true;
    }
}
