package frc.team5115.auto;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team5115.Constants;
import frc.team5115.robot.Robot;
import frc.team5115.statemachines.StateMachineBase;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

import static frc.team5115.robot.Robot.drive;
import static frc.team5115.robot.Robot.drivetrain;

public class AutoDrive extends StateMachineBase {

    public static final int DRIVING = 1;
    public static final int FINISHED = 2;

    double gyro_heading, desired_heading, angleDifference, turn;

    //define our trajectory
    Trajectory trajectory;
    //define our encoders
    EncoderFollower left;
    EncoderFollower right;
    //define our modifier
    TankModifier modifier;


    public AutoDrive(Trajectory target){
        //assign our trajectory
        trajectory = target;
        //assign our modifier with our new trajectory
        modifier = new TankModifier(trajectory).modify(0.7112);
        //instantiate our left and right encoder followers with our modifier
        left = new EncoderFollower(modifier.getLeftTrajectory());
        right = new EncoderFollower(modifier.getRightTrajectory());
    }
    public void updateTarget(Trajectory target){
        trajectory = target;
        modifier = new TankModifier(trajectory).modify(0.7112);
        left = new EncoderFollower(modifier.getLeftTrajectory());
        right = new EncoderFollower(modifier.getRightTrajectory());
    }

    public void setState(int s) {
        switch (s) {
            case DRIVING:
                //configure the encoder data to follow the trajectory
                left.configureEncoder(drivetrain.leftDistRaw(), 1440, 0.1016);
                right.configureEncoder(drivetrain.rightDistRaw(), 1440, 0.1016);
                //configure PID variables for encoders
                left.configurePIDVA(1.5, 0.0, 0, 1 / 4.3, 0);
                right.configurePIDVA(1.5, 0.0, 0, 1 / 4.3, 0);

                drivetrain.resetGyro();

                left.reset();
                right.reset();
                break;
        }

        super.setState(s);
    }

    public void update() {

        switch (state) {
            case DRIVING:
                drivetrain.inuse = true;
                // if both controllers are finished, finish
                double leftOutput = left.calculate(drivetrain.leftDistRaw());
                double rightOutput = right.calculate(drivetrain.rightDistRaw());

                gyro_heading = drivetrain.getYaw();  // Assuming the gyro is giving a value in degrees
                desired_heading = Pathfinder.r2d(left.getHeading());  // Should also be in degrees

                angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);
                turn = 0.8 * (-1.0/80.0) * angleDifference;

                drivetrain.leftFollow(leftOutput, -(turn));
                drivetrain.rightFollow(rightOutput, -(turn));
                System.out.println("Left" + (drivetrain.leftDistRaw() + -(turn)));
                System.out.println("Right" + (drivetrain.rightDistRaw() + -(turn)));

                SmartDashboard.putNumber("left speed", drivetrain.leftSpeed());
                SmartDashboard.putNumber("right speed", drivetrain.rightSpeed());

                System.out.println(leftOutput);
                if (left.isFinished() && right.isFinished()) {
                    System.out.println("heyyyy it finished");
                    drivetrain.drive(0, 0);
                    drivetrain.resetEncoders();
                    setState(FINISHED);
                }

                break;
            case FINISHED:
                drivetrain.inuse = false;
                drivetrain.drive(0, 0);

                break;
        }
    }


}

