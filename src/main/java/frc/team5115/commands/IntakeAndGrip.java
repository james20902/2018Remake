package frc.team5115.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team5115.commands.Grip.Grip;
import frc.team5115.commands.Grip.Release;
import frc.team5115.commands.Intake.Pass;

public class IntakeAndGrip extends CommandGroup {

    public IntakeAndGrip(){
        addSequential(new Release());
        addSequential(new Pass(), 0.7);
        addSequential(new Grip());
    }

}
