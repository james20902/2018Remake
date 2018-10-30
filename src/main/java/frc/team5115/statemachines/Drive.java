package frc.team5115.statemachines;

import frc.team5115.Constants;
import frc.team5115.robot.InputManager;
import frc.team5115.robot.Robot;

public class Drive extends StateMachineBase {

    public static final int DRIVING = 1;
    public static final int AUTOFOLLOWER = 2;
    public static final int PARTYTIME = 3;

    double forwardSpeed;
    double turnSpeed;

    public void update() {
        switch (state) {
            case STOP:
                Robot.drivetrain.drive(0, 0);
                break;

            case DRIVING:
                if(Robot.CM.dashControl){
                    forwardSpeed = InputManager.getForward() * InputManager.getThrottle() * Robot.CM.driveSpeed;
                    turnSpeed = InputManager.getTurn() * InputManager.getThrottle() * Robot.CM.driveSpeed;
                } else {
                    forwardSpeed = InputManager.getForward() * InputManager.getThrottle() * Constants.TOP_SPEED;
                    turnSpeed = InputManager.getTurn() * InputManager.getThrottle() * Constants.TOP_TURN_SPEED;
                }
                Robot.drivetrain.drive(forwardSpeed, turnSpeed);
                break;

            case AUTOFOLLOWER:
                if(Robot.autocsv.isFinished()){
                    setState(STOP);
                } else {
                    Robot.drivetrain.drive(Robot.autocsv.getX(), Robot.autocsv.getY());
                }
                break;

            case PARTYTIME:
                Robot.drivetrain.drive(1, 1);
                break;

        }
    }

}
