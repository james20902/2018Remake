package frc.team5115.commands.Grip;

import edu.wpi.first.wpilibj.command.Command;
import frc.team5115.robot.Robot;

public class Grip extends Command {
    public Grip(){
        requires(Robot.grip);
    }
    protected void initialize() {
        Robot.grip.grip();
    }
    protected boolean isFinished() {
        return true;
    }
}