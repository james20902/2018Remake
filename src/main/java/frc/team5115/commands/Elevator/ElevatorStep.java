package frc.team5115.commands.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.team5115.Constants;
import frc.team5115.PID;
import frc.team5115.robot.InputManager;
import frc.team5115.robot.Robot;

public class ElevatorStep extends Command {

    PID elevatorMover;

    double targetAngle = Constants.ELEVATOR_MIN;
    double armGoal;
    double angle;
    double dAngle;
    boolean up;

    public ElevatorStep(boolean direction){
        requires(Robot.elevator);
        elevatorMover = new PID( Constants.ARM_KP,	//readability!
                Constants.ARM_KI,	//because this line was too thicc
                Constants.ARM_KD,
                Constants.ELEVATOR_SPEED_SCALE);
        up = direction;
    }

    public void setTarget(double target) {
        if (target > Constants.ELEVATOR_MAX) {
            targetAngle = Constants.ELEVATOR_MAX;
        } else if (target < Constants.ELEVATOR_MIN) {
            targetAngle = Constants.ELEVATOR_MIN;
        } else {
            targetAngle = target;
        }
    }

    protected void execute(){
        angle = Robot.elevator.getAngle();
        dAngle = Robot.elevator.getAngleSpeed();
        if(up){
            armGoal = Robot.elevator.getAngle() + Constants.ELEVATOR_STEP;
        } else {
            armGoal = Robot.elevator.getAngle() - Constants.ELEVATOR_STEP;
        }
        Robot.elevator.move(elevatorMover.getPID(armGoal, angle, dAngle));
    }

    protected boolean isFinished(){
        return true;
    }

}
