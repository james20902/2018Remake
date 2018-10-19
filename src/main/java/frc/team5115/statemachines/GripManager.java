package frc.team5115.statemachines;

import edu.wpi.first.wpilibj.Timer;
import frc.team5115.robot.Robot;

public class GripManager extends StateMachineBase {

    public static final int STARTCLOCK = 1;
    public static final int INTAKEDELAY = 2;
    public static final int RELEASE = 3;
    public static final int GRIP = 4;

    private double time;

    public void update(){
        switch (state){
            case STOP:
                Robot.grip.stop();
                break;
            case STARTCLOCK:
                Robot.grip.release();
                time = Timer.getFPGATimestamp();
                setState(INTAKEDELAY);
                break;
            case INTAKEDELAY:
                if(time >= Timer.getFPGATimestamp() + 1){
                    setState(GRIP);
                }
                break;
            case RELEASE:
                Robot.grip.release();
                break;
            case GRIP:
                Robot.grip.grip();
                break;

        }
    }
}