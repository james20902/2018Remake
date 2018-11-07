package frc.team5115.commands.Intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.team5115.Constants;
import frc.team5115.robot.Robot;

public class Avoid extends Command {

    public Avoid(){
        requires(Robot.intake);
    }

    protected void execute(){
        if (Robot.elevator.minHeight() && !Robot.elevator.movingArm) {
            //pass
        } else if ((Robot.elevator.getAngle() <= Constants.INTAKE_HEIGHT)) {
            //open
        } else {
            //close
        }
    }

    protected boolean isFinished(){
        return true;
    }
}
