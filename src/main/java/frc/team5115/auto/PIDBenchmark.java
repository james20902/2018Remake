package frc.team5115.auto;

import frc.team5115.robot.Robot;
import frc.team5115.statemachines.StateMachineBase;

public class PIDBenchmark extends StateMachineBase {

    public static final int INIT = 1;
    public static final int FORWARD = 2;
    public static final int TURN = 3;
    public static final int FINISH = 4;

    AutoDrive drive;

    public PIDBenchmark ()
    {
        drive = new AutoDrive();
    }

    public void update(){
        switch(state){
            case INIT:
                drive.startLine(5, 1, true);
                setState(FORWARD);
                break;
            case FORWARD:
                drive.update();
                if(drive.state == AutoDrive.FINISHED){
                    drive.startTurn(360, 1, true);
                }
                break;
            case TURN:
                drive.update();
                if(drive.state == AutoDrive.FINISHED){
                    drive.setState(drive.STOP);
                }
                break;
            case FINISH:
                drive.update();
                break;
        }
    }

}
