package frc.team5115.commands.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.team5115.Constants;
import frc.team5115.PID;
import frc.team5115.robot.Robot;

public class ElevatorMove extends Command {

    PID elevatorMover;
    double targetAngle;
    double dAngle;
    double angle;

    boolean stepping = false;
    boolean up;

    public ElevatorMove(double angle){
        requires(Robot.elevator);
        elevatorMover = new PID( Constants.ARM_KP,	//readability!
                Constants.ARM_KI,	//because this line was too thicc
                Constants.ARM_KD,
                Constants.ELEVATOR_SPEED_SCALE);
        targetAngle = angle;

    }

    public ElevatorMove(boolean direction){
        requires(Robot.elevator);
        elevatorMover = new PID( Constants.ARM_KP,	//readability!
                Constants.ARM_KI,	//because this line was too thicc
                Constants.ARM_KD,
                Constants.ELEVATOR_SPEED_SCALE);
        stepping = true;
        up = direction;
    }

    protected double stepTarget(double currentAngle) {
        targetAngle = currentAngle + Constants.ELEVATOR_STEP;
        if(up){
            if (targetAngle > Constants.ELEVATOR_MAX) {
                return Constants.ELEVATOR_MAX;
            }
        } else {
            if (targetAngle < Constants.ELEVATOR_MIN) {
                return Constants.ELEVATOR_MIN;
            }
        }
        return targetAngle;
    }

    protected void execute(){
        angle = Robot.elevator.getAngle();
        dAngle = Robot.elevator.getAngleSpeed();

        if(stepping){
            targetAngle = stepTarget(angle);
        }
        Robot.elevator.move(elevatorMover.getPID(targetAngle, angle, dAngle));
    }

    protected boolean isFinished(){
        return !Robot.elevator.movingArm;
    }
}
