package frc.team5115.statemachines;

import frc.team5115.Constants;
import frc.team5115.robot.Robot;

public class IntakeManager extends StateMachineBase {

    public static final int INTAKE = 1;
    public static final int PASS = 2;

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
                break;

        }
    }

}
