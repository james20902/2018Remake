package frc.team5115.commands.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.team5115.Constants;
import frc.team5115.PID;
import frc.team5115.robot.Robot;

public class ElevatorMove extends Command {

    PID elevatorMover;
    double target;
    double dAngle;
    double angle;

    public ElevatorMove(double angle){
        requires(Robot.elevator);
        elevatorMover = new PID( Constants.ARM_KP,	//readability!
                Constants.ARM_KI,	//because this line was too thicc
                Constants.ARM_KD,
                Constants.ELEVATOR_SPEED_SCALE);
        target = angle;
    }

    protected void execute(){
        angle = Robot.elevator.getAngle();
        dAngle = Robot.elevator.getAngleSpeed();
        Robot.elevator.move(elevatorMover.getPID(target, angle, dAngle));
    }

    protected boolean isFinished(){
        return !Robot.elevator.movingArm;
    }
}
