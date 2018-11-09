package frc.team5115.commands.Grip;

import edu.wpi.first.wpilibj.command.Command;
import frc.team5115.robot.Robot;

public class Release extends Command {
    public Release(){
        requires(Robot.grip);
    }
    protected void initialize() {
        Robot.grip.release();
    }
    protected boolean isFinished() {
        return true;
    }
}
