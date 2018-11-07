package frc.team5115.commands.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.team5115.robot.Robot;

public class ElevatorRest extends Command {
    public ElevatorRest(){
        requires(Robot.elevator);
    }

    protected void execute(){
        Robot.elevator.move(0);
    }

    protected boolean isFinished(){
        return false;
    }
}
