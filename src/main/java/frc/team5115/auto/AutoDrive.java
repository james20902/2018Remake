package frc.team5115.auto;

import frc.team5115.Constants;
import frc.team5115.robot.Robot;
import frc.team5115.statemachines.StateMachineBase;

import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;


public class AutoDrive extends StateMachineBase {

    public static GyroCalculate gyro;

    public static final int DRIVING = 1;
    public static final int FINISHED = 2;



    //define our trajectory
    Trajectory trajectory;
    //define our encoders
    EncoderFollower left;
    EncoderFollower right;
    //define our modifier
    TankModifier modifier;


    public AutoDrive(Trajectory target){
        //gyrocalculationator
        gyro = new GyroCalculate();
        //assign our trajectory
        trajectory = target;
        //assign our modifier with our new trajectory
        modifier = new TankModifier(trajectory).modify(Constants.WHEELBASE);
        //instantiate our left and right encoder followers with our modifier
        left = new EncoderFollower(modifier.getLeftTrajectory());
        right = new EncoderFollower(modifier.getRightTrajectory());
    }
    public void updateTarget(Trajectory target){
        trajectory = target;
        modifier = new TankModifier(trajectory).modify(Constants.WHEELBASE);
        left = new EncoderFollower(modifier.getLeftTrajectory());
        right = new EncoderFollower(modifier.getRightTrajectory());
    }

    public void setState(int s) {
        switch (s) {
            case DRIVING:
                //configure the encoder data to follow the trajectory
                left.configureEncoder(Robot.drivetrain.leftDistRaw(), 1440, 0.1524);
                right.configureEncoder(Robot.drivetrain.rightDistRaw(), 1440, 0.1524);
                //configure PID variables for encoders
                left.configurePIDVA(1.5, 0.0, 0, 1 / 4.3, 0);
                right.configurePIDVA(1.5, 0.0, 0, 1 / 4.3, 0);
                break;
        }
        super.setState(s);
    }

    public void update() {

        switch (state) {
            case DRIVING:
                Robot.drivetrain.inuse = true;

                Robot.drivetrain.leftFollow(left.calculate(Robot.drivetrain.leftDistRaw()), gyro.calculate(left));
                Robot.drivetrain.rightFollow(left.calculate(Robot.drivetrain.leftDistRaw()), gyro.calculate(right));

                if (left.isFinished() && right.isFinished()) {
                    System.out.println("heyyyy it finished");
                    Robot.drivetrain.drive(0, 0);
                    setState(FINISHED);
                }

                break;
            case FINISHED:
                Robot.drivetrain.inuse = false;
                Robot.drivetrain.resetEncoders();
                left.reset();
                right.reset();
                break;
        }
    }


}

