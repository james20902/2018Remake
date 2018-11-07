package frc.team5115.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team5115.commands.Grip.Close;
import frc.team5115.commands.Intake.Pass;
import frc.team5115.systems.Grip;

public class IntakeAndGrip extends CommandGroup {

    public IntakeAndGrip(){
        addSequential(new Pass(), 0.7);
        addSequential(new Close());
    }

}
