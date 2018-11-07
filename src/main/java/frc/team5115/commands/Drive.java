package frc.team5115.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team5115.Constants;
import frc.team5115.robot.InputManager;
import frc.team5115.robot.Robot;

public class Drive extends Command {

    double x;
    double y;

    protected void execute() {
        x = InputManager.getForward() * InputManager.getThrottle() * Constants.TOP_SPEED;
        y = InputManager.getTurn() * InputManager.getThrottle() * Constants.TOP_TURN_SPEED;
        Robot.drivetrain.drive(x, y);
    }
    protected boolean isFinished() {
        return false;
    }
    protected void end() {
        Robot.drivetrain.drive(0, 0);
    }
}
