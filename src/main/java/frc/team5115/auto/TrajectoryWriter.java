package frc.team5115.auto;

import frc.team5115.robot.Robot;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;

import java.io.File;

public class TrajectoryWriter {

    File CL, CR, LL, RR, LR, RL;

    public TrajectoryWriter(){
        CL = new File("CL.traj");
        CR = new File("CR.traj");
        LL = new File("LL.traj");
        RR = new File("RR.traj");
        LR = new File("LR.traj");
        RL = new File("RL.traj");

    }

    public void write(String name, Trajectory t) {
            Pathfinder.writeToFile(new File(name + ".traj"), t);
    }

}
