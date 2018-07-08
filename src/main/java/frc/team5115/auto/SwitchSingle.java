package frc.team5115.auto;

import edu.wpi.first.wpilibj.Timer;
import frc.team5115.Constants;
import frc.team5115.robot.Robot;
import frc.team5115.statemachines.ElevatorManager;
import frc.team5115.statemachines.IntakeManager;
import frc.team5115.statemachines.StateMachineBase;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;

public class SwitchSingle extends StateMachineBase {

    //define our states
    public static final int INIT = 1;
    public static final int DRIVING = 2;
    public static final int DROPCUBE = 3;
    public static final int FINISHED = 4;

    Trajectory target;


    AutoDrive drive;

    protected void updateChildren() {
        drive.update();
        Robot.EM.update();
        Robot.IM.update();
    }

    public void update () {
        //Run switch block and check for number
        switch (state) {
            case INIT:
                //logic is as follows
                //if our objective(the switch) is on the left
                if (ObjectivePositions.switchpos == 'L'){
                    //check if we're lining up with the switch
                    if (Robot.OP.switchOurs()){
                        //if we are, set our follower to follow LL
                        target = Pathfinder.readFromFile(Robot.tw.LL);
                    } else if (ObjectivePositions.start == 'C'){
                        //if not, check if we're in the center, and set our follower to follow centerL
                        target = Pathfinder.readFromFile(Robot.tw.CL);
                    } else {
                        //if we arent in the center either, assume the switch is on the other side, follow LR
                        target = Pathfinder.readFromFile(Robot.tw.LR);
                    }
                    //this is the exact same thing, but for the right
                } else if(ObjectivePositions.switchpos == 'R'){
                    if (Robot.OP.switchOurs()){
                        target = Pathfinder.readFromFile(Robot.tw.RR);
                    } else if (ObjectivePositions.start == 'C'){
                        target = Pathfinder.readFromFile(Robot.tw.CR);
                    } else {
                        target = Pathfinder.readFromFile(Robot.tw.RL);
                    }
                }
                drive = new AutoDrive(target);
                drive.setState(AutoDrive.DRIVING);
                Robot.EM.setState(ElevatorManager.MOVING);
                Robot.IM.setState(IntakeManager.PASSNOWHEELS);
                Robot.grip.grip();
                setState(DRIVING);
                break;

            //when in case driving
            case DRIVING:
                Robot.EM.setTarget(Constants.SWITCH_HEIGHT);
                updateChildren();
                if(drive.state == AutoDrive.FINISHED){
                    setState(DROPCUBE);
                }
                break;
            case DROPCUBE:
                Timer.delay(0.5);
                Robot.grip.release();
                setState(FINISHED);
                break;

            case FINISHED:
                Robot.EM.setState(ElevatorManager.STOP);
                Robot.drivetrain.drive(0,0);
                break;
        }
    }


}