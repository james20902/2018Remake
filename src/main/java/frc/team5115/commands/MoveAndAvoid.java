package frc.team5115.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team5115.commands.Elevator.ElevatorMove;
import frc.team5115.commands.Grip.Grip;
import frc.team5115.commands.Intake.Avoid;
import frc.team5115.commands.Intake.Pass;

public class MoveAndAvoid extends CommandGroup {

    public MoveAndAvoid(double angle){
        addParallel(new ElevatorMove(angle));
        addParallel(new Avoid());
    }

    public MoveAndAvoid(boolean direction){
        addParallel(new ElevatorMove(direction));
        addParallel(new Avoid());
    }

}
