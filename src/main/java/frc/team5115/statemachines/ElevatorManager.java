package frc.team5115.statemachines;

import frc.team5115.Constants;
import frc.team5115.PID;
import frc.team5115.robot.Robot;

public class ElevatorManager extends StateMachineBase {

    public static final int MOVING = 1;
    public static final int RESTRICTED = 2;

    double targetAngle = Constants.ELEVATOR_MIN;
    double dAngle;
    double angle;
    PID elevatorMover;
    PID elevatorMoverRestricted;

    public ElevatorManager(){
        elevatorMover = new PID( Constants.ARM_KP,	//readability!
                Constants.ARM_KI,	//because this line was too thicc
                Constants.ARM_KD,
                Constants.ELEVATOR_SPEED_SCALE);
        elevatorMoverRestricted = new PID( Constants.ARM_KP,	//readability!
                Constants.ARM_KI,	//because this line was too thicc
                Constants.ARM_KD,
                0.75);
    }


    //just some checks to make sure the arm doesnt go lower or higher than possible
    public void setTarget(double target) {
        if (target > Constants.ELEVATOR_MAX) {
            targetAngle = Constants.ELEVATOR_MAX;
        } else if (target < Constants.ELEVATOR_MIN) {
            targetAngle = Constants.ELEVATOR_MIN;
        } else {
            targetAngle = target;
        }
    }

    public void collisionAvoidance(){
        //if the elevator is at the lowest possible height, and isnt moving
        if (Robot.elevator.minHeight() && !Robot.elevator.movingArm) {
            //keep the intake up
            Robot.IM.setState(IntakeManager.PASS);
            //if the arm is at a height less than or equal to the intake height (so when the arm is about
            //to hit the intake
        } else if ((Robot.elevator.getAngle() <= Constants.INTAKE_HEIGHT)) {
            //open and let the arm through
            Robot.IM.setState(IntakeManager.OPEN);
            //otherwise
        } else {
            //assume the arm is past the point of collision and remain closed
            Robot.IM.setState(IntakeManager.CLOSE);
        }
    }


    public void setState(int s) {
        switch (s) {
            case MOVING:
                //set the speed to use our user set arm speed value somehow

        }
        super.setState(s);
    }

    public void update() {
        angle = Robot.elevator.getAngle();
        dAngle = Robot.elevator.getAngleSpeed();

        switch (state) {
            case STOP:
                Robot.elevator.move(0);
                break;

            case MOVING:
                collisionAvoidance();
                //then move the arm and calculate how far it needs to move
                Robot.elevator.move(elevatorMover.getPID(targetAngle, angle, dAngle));
                break;

            case RESTRICTED:
                collisionAvoidance();
                Robot.elevator.move(elevatorMoverRestricted.getPID(targetAngle, angle, dAngle));
                break;
        }
    }
}
