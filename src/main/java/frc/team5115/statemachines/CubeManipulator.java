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
    public static final int INTAKE = 7;
    public static final int DROP = 8;
    public static final int RELEASE = 9;
    public static final int PARTYTIME = 10;

    public double armGoal = Robot.elevator.getAngle();

    public boolean dashControl = false;
    public double driveSpeed = 0;
    public double armSpeed = 0;

    protected void updateChildren(){
        Robot.EM.update();
        Robot.IM.update();
    }

    public void update(){
        System.out.println(driveSpeed);
        if(driveSpeed > 1){
            driveSpeed = 1;
        } else if(driveSpeed < 0){
            driveSpeed = 0;
        }
        if(armSpeed > 1){
            armSpeed = 1;
        } else if(armSpeed < 0){
            armSpeed = 0;
        }
        switch (state){
            case STOP:
                Robot.elevator.move(0);
                break;
            case INPUT:
                updateChildren();
                System.out.println("accepting input");
                if (Robot.elevator.minHeight()) {
                    Robot.IM.setState(IntakeManager.PASS);
                } else {
                    Robot.IM.setState(IntakeManager.PASSNOWHEELS);
                }
                //always return back to this state, check if any button is being pressed. if it is, act accordingly
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

                if (InputManager.eject()){
                    setState(DROP);
                }
                if (InputManager.toggleDash()){
                    dashControl = !dashControl;
                }
                if(InputManager.partyTime() && Robot.DS.getMatchTime() > 135) {
                    setState(PARTYTIME);
                }
                break;
            case ARMUP:
                //set current target, to be reassigned later if button is being held
                Robot.EM.setTarget(armGoal);

                //move the arm (execute move case)
                updateChildren();
                //every loop, check if the button is being held down
                if(InputManager.moveUp()) {
                    //if it is, add to the target
                    armGoal = Robot.elevator.getAngle() + Constants.ELEVATOR_STEP;
                } else {
                    //if its not, give back input options
                    setState(INPUT);
                }
                break;
            case ARMDOWN:
                Robot.EM.setTarget(armGoal);
                updateChildren();
                if(InputManager.moveDown()) {
                    armGoal = Robot.elevator.getAngle() - Constants.ELEVATOR_STEP;
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
            case INTAKE:
                Robot.IM.update();
                if (InputManager.intake()){
                    Robot.IM.setState(IntakeManager.INTAKE);
                } else if (!InputManager.intake() && Robot.elevator.minHeight()){
                    Robot.IM.setState(IntakeManager.PASS);
                    Robot.IM.update();
                    Timer.delay(1);
                    Robot.grip.grip();
                    setState(INPUT);
                } else {
                    Robot.IM.setState(IntakeManager.PASS);
                    Robot.IM.update();
                    setState(INPUT);
                }
                break;
            case DROP:
                Robot.IM.setState(IntakeManager.DROP);
                Robot.IM.update();
                setState(INPUT);
                break;
            case RELEASE:
                Robot.grip.release();
                break;
            case PARTYTIME:
                if(InputManager.partyTime() && Robot.elevator.minHeight()){
                    Robot.drive.setState(Drive.PARTYTIME);
                } else {
                    Robot.drive.setState(Drive.DRIVING);
                    setState(INPUT);
                }
        }
    }

}
