package frc.team5115.commands.Grip;

import edu.wpi.first.wpilibj.command.Command;
import frc.team5115.robot.Robot;

public class Close extends Command {
    protected void initialize() {
        Robot.grip.grip();
    }
    protected boolean isFinished() {
        return true;
    }
}