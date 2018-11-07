package frc.team5115.commands.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.team5115.PID;
import frc.team5115.robot.Robot;

public class ElevatorMove extends Command {
    double target;

    public ElevatorMove(double angle){
        requires(Robot.elevator);
        PID elevatorMover = new
        target = angle;
    }


    protected boolean isFinished(){
        //return pid loop is finished
        return ;
    }
}
