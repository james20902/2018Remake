package frc.team5115.auto;

import frc.team5115.Constants;
import frc.team5115.robot.Robot;
import frc.team5115.statemachines.StateMachineBase;
import frc.team5115.systems.DriveTrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Auto extends StateMachineBase{

    public static final int INIT = 0;
    public static final int TEST = 1;

    //define auto routines
    public static SwitchRoutine switchauto;

    public Auto() {
        //instantiate auto routines
        switchauto = new SwitchRoutine();
    }

    //each time update is called in AutoDrive
    public void update () {
        //Run switch block and check for number
        switch (state) {
            case INIT:
                switchauto.setState(switchauto.INIT);
                break;

            case TEST:
                switchauto.update();
                break;
        }
    }
}




