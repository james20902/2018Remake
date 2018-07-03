package frc.team5115.auto;

import frc.team5115.robot.Robot;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;

import java.io.File;

public class TrajectoryGenerator {

    Waypoint[] CL, CR, LL, RR, LR, RL;


    Trajectory.Config configSwitch;
    Trajectory.Config configScale;

    public TrajectoryGenerator(){
        CL = new Waypoint[]{
                new Waypoint(0.482, 4, 0),
                new Waypoint(4, 5.5, Pathfinder.d2r(0))
        };
        CR = new Waypoint[]{
                new Waypoint(0.482, 4, 0),
                new Waypoint(4, 2.5, Pathfinder.d2r(0))
        };
        LL = new Waypoint[]{
                new Waypoint(0.482, 4, 0),
                new Waypoint(4, 2.6, Pathfinder.d2r(0))
        };
        RR = new Waypoint[]{
                new Waypoint(0.482, 4, 0),
                new Waypoint(4, 2.6, Pathfinder.d2r(0))
        };
        LR = new Waypoint[]{
                new Waypoint(0.482, 4, 0),
                new Waypoint(4, 2.6, Pathfinder.d2r(0))
        };
        RL = new Waypoint[]{
                new Waypoint(0.482, 4, 0),
                new Waypoint(4, 2.6, Pathfinder.d2r(0))
        };
        configSwitch = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 0.5, 0.2, 60.0);
        configScale = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 0.5, 0.2, 60.0);

        Robot.tw.write("CL",Pathfinder.generate(CL, configSwitch));
        Robot.tw.write("CR",Pathfinder.generate(CR, configSwitch));
        Robot.tw.write("LL",Pathfinder.generate(LL, configSwitch));
        Robot.tw.write("RR",Pathfinder.generate(RR, configSwitch));
        Robot.tw.write("LR",Pathfinder.generate(LR, configSwitch));
        Robot.tw.write("RL",Pathfinder.generate(RL, configSwitch));

    }


}
