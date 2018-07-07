package frc.team5115.auto;

import frc.team5115.statemachines.StateMachineBase;

public class Auto extends StateMachineBase{

    public static final int INIT = 0;
    private static final int SWITCHSINGLE = 1;

    //define auto routines
    private static SwitchRoutine switchauto;

    public Auto() {
        //instantiate auto routines
        switchauto = new SwitchRoutine();
    }

    //each time update is called in AutoDrive
    public void update () {
        //Run switch block and check for number
        switch (state) {
            case INIT:
                switchauto.setState(SwitchRoutine.INIT);
                break;

            case SWITCHSINGLE:
                switchauto.update();
                break;
        }
    }
}




