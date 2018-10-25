package frc.team5115.auto;

import edu.wpi.first.wpilibj.Timer;
import frc.team5115.Constants;
import frc.team5115.robot.Robot;
import frc.team5115.statemachines.ElevatorManager;
import frc.team5115.statemachines.GripManager;
import frc.team5115.statemachines.IntakeManager;
import frc.team5115.statemachines.StateMachineBase;

import java.lang.reflect.Field;


public class Switch_cheap extends StateMachineBase {


    public static final int INIT = 0;
    public static final int DRIVING = 1;
    public static final int FINISHED = 2;

    AutoDrive drive;
    double time;

    public Switch_cheap(){
        drive = new AutoDrive();
    }

    protected void updateChildren() {
        drive.update();
        Robot.EM.update();
        Robot.IM.update();
        Robot.GM.update();
    }

    public void resetEverything(){
        Robot.drivetrain.resetEncoders();
        Robot.drivetrain.resetGyro();
    }

    public void update(){
        switch(state) {
            case INIT:
                System.out.println("cross line selected!");
                Robot.EM.setState(ElevatorManager.MOVING);
                Robot.EM.setTarget(Constants.SWITCH_HEIGHT);
                Robot.IM.setState(IntakeManager.PASSNOWHEELS);
                Robot.GM.setState(GripManager.GRIP);
                drive.startLine(8, 0.5, false);
                setState(DRIVING);
                break;
            case DRIVING:
                updateChildren();
                if(drive.state == AutoDrive.FINISHED){
                    if(Robot.OP.switchOurs()){
                        time = Timer.getFPGATimestamp();
                    } else {
                        Robot.GM.setState(GripManager.GRIP);
                    }
                    drive.setState(drive.FINISHED);
                    setState(FINISHED);
                }
                break;
            case FINISHED:
                //Robot.drivetrain.drive(0,0);
                if(Timer.getFPGATimestamp() >= time + 1){
                    Robot.GM.setState(GripManager.RELEASE);
                }
                System.out.println("target hit!");
                updateChildren();
                break;
        }
    }

}