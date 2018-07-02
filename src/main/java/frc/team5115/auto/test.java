package frc.team5115.auto;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team5115.robot.Robot;
import frc.team5115.statemachines.StateMachineBase;
import frc.team5115.systems.DriveTrain;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

public class test extends StateMachineBase {

    //define our states
    public static final int INIT = 1;
    public static final int DRIVING = 2;
    public static final int FINISHED = 3;

    AutoDrive drive;

    Trajectory.Config globalConfig;
    Trajectory target;

    Waypoint[] test;

    public test() {

    //create parameters for trajectory to follow
        // Create the Trajectory Configuration
//
// Arguments:
// Fit Method:          HERMITE_CUBIC or HERMITE_QUINTIC
// Sample Count:        SAMPLES_HIGH (100 000)
//                      SAMPLES_LOW  (10 000)
//                      SAMPLES_FAST (1 000)
// Time Step:           0.05 Seconds
// Max Velocity:        1.7 m/s
// Max Acceleration:    2.0 m/s/s
// Max Jerk:            60.0 m/s/s/s
}
    public void update () {
        //Run switch block and check for number
        switch (state) {
            case INIT:
                drive = new AutoDrive(Pathfinder.readFromFile(Robot.tg.myFile));
                drive.setState(AutoDrive.DRIVING);
                setState(DRIVING);
                break;

            //when in case driving
            case DRIVING:
                if(drive.state == AutoDrive.FINISHED){
                    setState(FINISHED);
                }
                drive.update();
                break;

            case FINISHED:
                Robot.drivetrain.drive(0,0);
                break;
        }
    }


}
