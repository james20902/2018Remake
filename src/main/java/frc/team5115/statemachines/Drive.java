package frc.team5115.statemachines;

import frc.team5115.Constants;
import frc.team5115.robot.InputManager;
import frc.team5115.robot.Robot;

public class Drive extends StateMachineBase {

    public static final int DRIVING = 1;
    public static final int PARTYTIME = 2;
    public static final int RESTRICTED = 3;
    public static final int EBREAK = 4;

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

            case PARTYTIME:
                Robot.drivetrain.drive(1, 1);
                break;

            case RESTRICTED:
                forwardSpeed = InputManager.getForward() * 0.75;
                turnSpeed = InputManager.getTurn() * 0.75;
                Robot.drivetrain.drive(forwardSpeed, turnSpeed);
                break;
            case EBREAK:
                Robot.ebreak.update();
                break;
        }
    }

}
