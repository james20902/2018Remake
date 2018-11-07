package frc.team5115.commands.Intake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team5115.Constants;
import frc.team5115.robot.Robot;

public class Avoid extends CommandGroup {

    public Avoid(){
        requires(Robot.intake);
    }

    protected void execute(){
        if (Robot.elevator.minHeight() && !Robot.elevator.movingArm) {
            new Pass();
        } else if ((Robot.elevator.getAngle() <= Constants.INTAKE_HEIGHT)) {
            new Open();
        } else {
            new Close();
        }
    }

    protected boolean isFinished(){
        return false;
    }
}
