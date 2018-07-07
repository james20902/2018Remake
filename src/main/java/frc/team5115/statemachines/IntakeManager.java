package frc.team5115.statemachines;

import edu.wpi.first.wpilibj.Timer;
import frc.team5115.Constants;
import frc.team5115.robot.Robot;

public class IntakeManager extends StateMachineBase {

    public static final int INTAKE = 1;
    public static final int PASS = 2;
    public static final int PASSNOWHEELS = 3;
    public static final int DROP = 4;

    public void update(){
        switch(state){
            case STOP:
                Robot.intake.relax();
                Robot.intake.intake(0);
                Robot.intake.liftIntake();
                break;
            case INTAKE:
                Robot.intake.relax();
                Robot.intake.intake(Constants.INTAKE_SPEED);
                Robot.intake.lowerIntake();
                break;
            case PASS:
                Robot.intake.grip();
                Robot.intake.intake(0.2);
                Robot.intake.liftIntake();
                break;
            case PASSNOWHEELS:
                Robot.intake.grip();
                Robot.intake.intake(0);
                Robot.intake.liftIntake();
                break;
            case DROP:
                Robot.intake.intake(0);
                Robot.intake.lowerIntake();
                Timer.delay(0.5);
                Robot.intake.release();
                break;


        }
    }

}
