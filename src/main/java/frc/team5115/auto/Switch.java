package frc.team5115.auto;

import edu.wpi.first.wpilibj.Timer;
import frc.team5115.Constants;
import frc.team5115.robot.Robot;
import frc.team5115.statemachines.ElevatorManager;
import frc.team5115.statemachines.GripManager;
import frc.team5115.statemachines.IntakeManager;
import frc.team5115.statemachines.StateMachineBase;


public class Switch extends StateMachineBase {


    public static final int INIT = 0;
    public static final int DRIVING = 1;
    public static final int TURNING = 2;
    public static final int DRIVEAGAIN = 3;
    public static final int CENTERISNTDONEYET = 4;
    public static final int STILLISNTDONEYET = 5;
    public static final int FINISHED = 6;

    double time;
    boolean center = Robot.OP.isCenter();
    boolean side = Robot.OP.switchOurs();
    boolean scale = Robot.OP.scaleOurs();

    AutoDrive drive;

    public Switch(){
        drive = new AutoDrive();
    }

    protected void updateChildren() {
        drive.update();
        Robot.EM.update();
        Robot.IM.update();
        Robot.GM.update();
    }

    public void update(){
        switch(state) {
            case INIT:
                Robot.EM.setState(ElevatorManager.STOP);
                Robot.IM.setState(IntakeManager.PASSNOWHEELS);
                Robot.GM.setState(GripManager.GRIP);
                //determine which path to take
                if(center){
                    drive.startLine(1.5, 0.45, false);
                } else if(side) {
                    drive.startLine(12, 0.75, false);
                } else if(scale){
                    Robot.EM.setState(ElevatorManager.MOVING);
                    Robot.EM.setTarget(Constants.SCALE_HEIGHT);
                    drive.startLine(19, 0.75, false);
                } else {
                    drive.startLine(11.6, 0.75, false);
                }
                setState(DRIVING);
                break;
            case DRIVING:
                updateChildren();
                if(drive.state == AutoDrive.FINISHED){
                    if(center){
                        if(Robot.OP.switchpos == 'R'){
                            drive.startTurn(45, 0.5, false);
                        } else {
                            drive.startTurn(-45, 0.5, false);
                        }
                    } else if(side) {
                        if(Robot.OP.switchpos == 'R'){
                            drive.startTurn(-90, 0.5, false);
                        } else {
                            drive.startTurn(90, 0.5, false);
                        }
                    } else if(scale){
                        if (Robot.OP.scalepos == 'R'){
                            drive.startTurn(-30, .5, false);
                        }
                        else {
                            drive.startTurn(30, .5, false);
                        }
                    } else {
                        drive.setState(drive.STOP);
                        Robot.EM.setState(ElevatorManager.STOP);
                        setState(FINISHED);
                    }
                    setState(TURNING);
                }
                break;
            case TURNING:
                updateChildren();
                if(drive.state == AutoDrive.FINISHED){
                    Robot.EM.setState(ElevatorManager.MOVING);
                    Robot.EM.setTarget(Constants.SWITCH_HEIGHT);
                    if(center){
                        if(Robot.OP.switchpos == 'R') {
                            drive.startLine(5.5, 0.5, false);
                        }
                        else {
                            drive.startLine(7, 0.5, false);
                        }
                    } else if(side){
                        drive.startLine(2.5, 0.25, false);
                    } else { //assume we're going to the scale
                        Robot.EM.setTarget(Constants.SCALE_HEIGHT);
                        drive.startLine(4, 0.25, false);
                    }
                    time = Timer.getFPGATimestamp();
                    setState(DRIVEAGAIN);
                }
                break;
            case DRIVEAGAIN:
                updateChildren();
                if(drive.state == AutoDrive.FINISHED){
                    if(center){
                        if(Robot.OP.switchpos == 'R') {
                            drive.startTurn(45, 0.5, false);
                        }
                        else {
                            drive.startTurn(-45, 0.5, false);
                        }
                        setState(CENTERISNTDONEYET);
                    } else { //if we did anything from the side, we're done.
                        drive.setState(drive.STOP);
                        Robot.EM.setState(ElevatorManager.STOP);
                        Robot.grip.release();
                        setState(FINISHED);
                    }
                }
                break;
            case CENTERISNTDONEYET:
                updateChildren();
                if(drive.state == AutoDrive.FINISHED) {
                    drive.startLine(4, 0.25, false);
                }
                break;
            case STILLISNTDONEYET:
                updateChildren();
                if(drive.state == AutoDrive.FINISHED){
                    drive.setState(drive.STOP);
                    Robot.EM.setState(ElevatorManager.STOP);
                    Robot.GM.setState(GripManager.RELEASE);
                    setState(FINISHED);
                }
                break;
            case FINISHED:
                updateChildren();
                break;

        }
    }

}
