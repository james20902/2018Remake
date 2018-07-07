package frc.team5115.statemachines;

import frc.team5115.Constants;
import frc.team5115.robot.Robot;

public class ElevatorManager extends StateMachineBase {

    public static final int MOVING = 1;

    double error;
    double errorAccum = 0;
    double dError;
    double output;

    double targetAngle;
    double dAngle;
    double angle;

    public void setTarget(double target) {
        if (target > Constants.ELEVATOR_MAX) {
            targetAngle = Constants.ELEVATOR_MAX;
        } else if (target < Constants.ELEVATOR_MIN) {
            targetAngle = Constants.ELEVATOR_MIN;
        } else {
            targetAngle = target;
        }
    }

    public double calculate(double setpoint, double reading, double dReading) {
        error = setpoint - reading;
        dError = -dReading;        // result of taking the derivative of the equation above with respect to time

        output = Constants.ARM_KP * error + Constants.ARM_KI * errorAccum + Constants.ARM_KD * dError;

        // Do not integrate if the output exceeds max to avoid intergral windup. See youtu.be/fusr9eTceEo
        if (Math.abs(output) <= Constants.ELEVATOR_SPEED_SCALE) {
            errorAccum += error * Constants.DELAY;
        }

        // Do not return a value greater than the maximum, but make sure it's the right sign
        if (Math.abs(output) > Constants.ELEVATOR_SPEED_SCALE) {
            output = Constants.ELEVATOR_SPEED_SCALE * Math.signum(output);
        }

        return output;
    }

    public void update() {
        angle = Robot.elevator.getAngle();
        dAngle = Robot.elevator.getAngleSpeed();

        switch (state) {
            case STOP:
                Robot.elevator.move(0);
                break;

            case MOVING:
                Robot.elevator.move(calculate(targetAngle, angle, dAngle));
                break;
        }
    }
}
