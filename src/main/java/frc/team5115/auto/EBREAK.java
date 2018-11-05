package frc.team5115.auto;

import frc.team5115.statemachines.StateMachineBase;


public class EBREAK extends StateMachineBase {


    public static final int INIT = 0;
    public static final int BREAKING = 1;
    public static final int KILL = 2;

    AutoDrive drive;

    public EBREAK(){
        drive = new AutoDrive();
    }


    public void update(){
        switch(state) {
            case INIT:
                drive.startLine(1, 1, false);
                break;
            case BREAKING:
                drive.update();
                if(drive.state == AutoDrive.FINISHED){
                    setState(KILL);
                }
                break;
            case KILL:
                int whoops = 1/0;
                break;
        }
    }

}
