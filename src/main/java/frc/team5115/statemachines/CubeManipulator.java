package frc.team5115.statemachines;

import edu.wpi.first.wpilibj.Timer;
import frc.team5115.Constants;
import frc.team5115.robot.InputManager;
import frc.team5115.robot.Robot;

public class CubeManipulator extends StateMachineBase {

    public static final int INPUT = 1;
    public static final int ARMUP = 2;
    public static final int ARMDOWN = 3;
    public static final int ARMSWITCH = 4;
    public static final int ARMSCALE = 5;
    public static final int ARMHOME = 6;

    public double armGoal = Robot.elevator.getAngle();

    protected void updateChildren(){
        Robot.EM.update();
    }

    public void update(){
        switch (state){
            case STOP:
                Robot.elevator.move(0);
                break;
            case INPUT:
                // user input
                if ((InputManager.moveUp()) && !Robot.elevator.maxHeight()){
                    setState(ARMUP);
                }

                if (InputManager.moveDown() && !Robot.elevator.minHeight()){
                    setState(ARMDOWN);
                }

                if (InputManager.switchHeight()) {
                    setState(ARMSWITCH);
                }

                if (InputManager.scaleHeight()) {
                    setState(ARMSCALE);
                }

                if (InputManager.returnHeight()) {
                    setState(ARMHOME);
                }
                break;
            case ARMUP:
                Robot.EM.setTarget(armGoal);
                updateChildren();
                if(InputManager.moveUp()) {
                    armGoal = Robot.elevator.getAngle() + Constants.ELEVATOR_STEP;
                } else {
                    setState(INPUT);
                }

                break;
            case ARMDOWN:
                Robot.EM.setTarget(armGoal);
                updateChildren();
                if(InputManager.moveUp()) {
                    armGoal = Robot.elevator.getAngle() + Constants.ELEVATOR_STEP;
                } else {
                    setState(INPUT);
                }
                break;
            case ARMSWITCH:
                armGoal = Constants.SWITCH_HEIGHT;
                Robot.EM.setTarget(armGoal);
                updateChildren();
                setState(INPUT);
                break;
            case ARMSCALE:
                armGoal = Constants.SCALE_HEIGHT;
                Robot.EM.setTarget(armGoal);
                updateChildren();
                setState(INPUT);
                break;
            case ARMHOME:
                armGoal = Constants.RETURN_HEIGHT;
                Robot.EM.setTarget(armGoal);
                updateChildren();
                setState(INPUT);
                break;
        }
    }

}
