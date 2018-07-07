package frc.team5115.statemachines;

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
    public static final int INTAKE = 7;

    public double armGoal = Robot.elevator.getAngle();

    protected void updateChildren(){
        Robot.EM.update();
        Robot.IM.update();
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

                if (InputManager.intake()){
                    setState(INTAKE);
                }
                break;
            case ARMUP:
                Robot.EM.setTarget(armGoal);
                Robot.EM.update();
                if(InputManager.moveUp()) {
                    armGoal = Robot.elevator.getAngle() + Constants.ELEVATOR_STEP;
                } else {
                    setState(INPUT);
                }

                break;
            case ARMDOWN:
                Robot.EM.setTarget(armGoal);
                Robot.EM.update();
                if(InputManager.moveUp()) {
                    armGoal = Robot.elevator.getAngle() + Constants.ELEVATOR_STEP;
                } else {
                    setState(INPUT);
                }
                break;
            case ARMSWITCH:
                armGoal = Constants.SWITCH_HEIGHT;
                Robot.EM.setTarget(armGoal);
                Robot.EM.update();

                setState(INPUT);
                break;
            case ARMSCALE:
                armGoal = Constants.SCALE_HEIGHT;
                Robot.EM.setTarget(armGoal);
                Robot.EM.update();
                setState(INPUT);
                break;
            case ARMHOME:
                armGoal = Constants.RETURN_HEIGHT;
                Robot.EM.setTarget(armGoal);
                Robot.EM.update();
                setState(INPUT);
                break;
            case INTAKE:
                Robot.IM.update();
                if (InputManager.intake()){
                    Robot.IM.setState(IntakeManager.INTAKE);
                } else if (!InputManager.intake() && Robot.intake.isCube()){
                    Robot.IM.setState(IntakeManager.PASS);
                    Robot.IM.update();
                    setState(INPUT);
                }
                break;
        }
    }

}
