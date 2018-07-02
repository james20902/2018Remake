package frc.team5115.auto;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;

import java.io.File;

public class TrajectoryGenerator {

    Waypoint[] test = new Waypoint[]{
        new Waypoint(0, 4, 0),
                new Waypoint(4, 2.6, Pathfinder.d2r(0))
    };
    Waypoint[] test2 = new Waypoint[]{
            new Waypoint(0, 4, 0),
            new Waypoint(4, 5.5, Pathfinder.d2r(0))
    };

    Trajectory testtraj;
    Trajectory testtraj2;

    Trajectory.Config config;
    Trajectory.Config config2;

    File myFile;
    File myFile2;

    public TrajectoryGenerator(){
        config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 0.5, 0.2, 60.0);
        config2 = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 0.5, 0.2, 60.0);


        myFile = new File("testtraj.traj");
        myFile2 = new File("testtraj2.traj");
        System.out.println("generating...");
        testtraj = Pathfinder.generate(test, config);
        testtraj2 = Pathfinder.generate(test2, config2);
        System.out.println("finished generating, writing to file");
        Pathfinder.writeToFile(myFile, testtraj);
        Pathfinder.writeToFile(myFile2, testtraj2);
        System.out.println("serialization complete");
    }


}
