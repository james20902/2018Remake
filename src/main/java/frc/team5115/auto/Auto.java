package frc.team5115.auto;

import frc.team5115.statemachines.StateMachineBase;

public class Auto extends StateMachineBase{

    int whatitdo;

    public static final int INIT = 0;
    public static final int SWITCH = 1;

    public static Switch switchstrategy;


    //define auto routines


    public Auto(int Strategy) {
        whatitdo = Strategy;
        switchstrategy = new Switch();
    }

    public void setState(int s) {
        switch (s) {
            case INIT:
                switchstrategy.setState(switchstrategy.INIT);

        }
        super.setState(s);
    }

    //each time update is called in AutoDrive
    public void update () {
        //Run switch block and check for number
        switch (state) {
            case INIT:
                setState(whatitdo);
            case SWITCH:
                System.out.println("Updating switch");
                switchstrategy.update();
        }
    }
}




