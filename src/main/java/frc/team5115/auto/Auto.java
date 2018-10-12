package frc.team5115.auto;

import frc.team5115.statemachines.StateMachineBase;

public class Auto extends StateMachineBase{

    int whatitdo;

    public static final int INIT = 0;
    public static final int SWITCH = 1;
    public static final int SCALE = 2;
    public static final int UHOH = 3;

    //define auto routines
    public static Switch switchstrategy;
    public static Scale scalestrategy;
    public static UHOH oshit;


    public Auto(int Strategy) {
        whatitdo = Strategy;
        switchstrategy = new Switch();
        scalestrategy = new Scale();
        oshit = new UHOH();
    }

    public void setState(int s) {
        switch (s) {
            case INIT:
                switchstrategy.setState(switchstrategy.INIT);
                scalestrategy.setState(scalestrategy.INIT);
                oshit.setState(oshit.INIT);
                break;
        }
        super.setState(s);
    }

    //each time update is called in AutoDrive
    public void update () {
        //Run switch block and check for number
        switch (state) {
            case INIT:
                setState(whatitdo);
                break;
            case SWITCH:
                System.out.println("Updating switch");
                switchstrategy.update();
                break;
            case SCALE:
                scalestrategy.update();
                break;
            case UHOH:
                oshit.update();
                break;
        }
    }
}




