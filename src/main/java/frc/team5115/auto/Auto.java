package frc.team5115.auto;

import frc.team5115.statemachines.StateMachineBase;

public class Auto extends StateMachineBase{

    public static final int INIT = 0;
    public static final int SWITCHSINGLE = 1;
    public static final int SCALESINGLE = 2;

    //define auto routines
    public static SwitchSingle switchsingle;
    public static ScaleSingle scalesingle;

    public Auto() {
        //instantiate auto routines
        switchsingle = new SwitchSingle();
        scalesingle = new ScaleSingle();
    }

    //each time update is called in AutoDrive
    public void update () {
        //Run switch block and check for number
        switch (state) {
            case INIT:
                //for testing purposes, we just wanna test switch auto
                switchsingle.setState(SwitchSingle.INIT);
                setState(SWITCHSINGLE);
                break;

            case SWITCHSINGLE:
                //loop the current state of switch single
                switchsingle.update();
                break;

            case SCALESINGLE:
                scalesingle.update();
                break;
        }
    }
}




