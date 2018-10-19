package frc.team5115.auto;

import edu.wpi.first.wpilibj.Timer;
import frc.team5115.Constants;
import frc.team5115.robot.Robot;
import frc.team5115.statemachines.ElevatorManager;
import frc.team5115.statemachines.GripManager;
import frc.team5115.statemachines.IntakeManager;
import frc.team5115.statemachines.StateMachineBase;


public class UHOH extends StateMachineBase {


    public static final int INIT = 0;
    public static final int DRIVING = 1;
    public static final int FINISHED = 2;

    AutoDrive drive;

    public UHOH(){
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
                drive.startLine(11.6, 0.75, false);
                setState(DRIVING);
                break;
            case DRIVING:
                updateChildren();
                if(drive.state == AutoDrive.FINISHED) {
                    drive.setState(drive.STOP);
                    setState(FINISHED);
                }
                break;
            case FINISHED:
                updateChildren();
                break;
        }
    }

}
