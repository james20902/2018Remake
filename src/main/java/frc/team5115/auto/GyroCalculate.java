package frc.team5115.auto;

import frc.team5115.robot.Robot;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.followers.EncoderFollower;


public class GyroCalculate {

    double gyro_heading, desired_heading, angleDifference;

    public double calculate(EncoderFollower e){
        gyro_heading = Robot.drivetrain.getYaw();  // Assuming the gyro is giving a value in degrees
        desired_heading = Pathfinder.r2d(e.getHeading());  // Should also be in degrees

        angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);
        return 0.8 * (-1.0/80.0) * angleDifference;
    }

}
