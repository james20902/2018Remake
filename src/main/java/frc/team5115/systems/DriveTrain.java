package frc.team5115.systems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.kauailabs.navx.frc.AHRS;
import frc.team5115.Constants;

import edu.wpi.first.wpilibj.SPI;


public class DriveTrain {
    public boolean inuse;
    //define motor objects
    TalonSRX frontleft;
    TalonSRX frontright;
    TalonSRX backleft;
    TalonSRX backright;
    //define gyroscope object
    AHRS navx;

    public int direction;

    public DriveTrain(){
        //instantiate the things
        navx = new AHRS(SPI.Port.kMXP);
        frontleft = new TalonSRX(Constants.FRONT_LEFT_MOTOR_ID);
        frontright = new TalonSRX(Constants.FRONT_RIGHT_MOTOR_ID);
        backleft = new TalonSRX(Constants.BACK_LEFT_MOTOR_ID);
        backright = new TalonSRX(Constants.BACK_RIGHT_MOTOR_ID);

        //front left and front right motors will do the same thing that the back left and back right motor does
        frontright.set(ControlMode.Follower, Constants.BACK_RIGHT_MOTOR_ID);
        frontleft.set(ControlMode.Follower, Constants.BACK_LEFT_MOTOR_ID);

        //assign encoder data to back left and back right motors respectively
        backright.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 5);
        backleft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 5);

    }

    public void drive(double speed, double turn){
        double leftspeed = speed + turn;
        double rightspeed = speed - turn;
        //if our joystick gets weird, and somehow surpasses 1.0
        //assume it's 1.0
        if(Math.abs(leftspeed) > 1){
            leftspeed = Math.signum(leftspeed);
        }
        if(Math.abs(rightspeed) > 1){
            rightspeed = Math.signum(rightspeed);
        }
        //set our "speed" or voltage output to left and right speeds
        backleft.set(ControlMode.PercentOutput, -leftspeed);
        backright.set(ControlMode.PercentOutput, rightspeed);
    }
    public double leftDist() {
        double leftDist = -direction * backleft.getSelectedSensorPosition(0);
        return leftDist / 1440 * 6 * Math.PI / 12;
    }

    public double rightDist() {
        System.out.println(backright.getSelectedSensorPosition(0));
        double rightDist = direction * backright.getSelectedSensorPosition(0);
        return rightDist / 1440 * 6 * Math.PI / 12;
    }

    public double distanceTraveled() {
        return (rightDist() + leftDist()) / 2;
    }

    public double leftSpeed() {
        double leftspeed = -backleft.getSelectedSensorVelocity(0);
        return ((leftspeed * 6 * Math.PI * 10) / (1440 * 12));

    }

    public double rightSpeed() {
        double rightspeed = backright.getSelectedSensorVelocity(0);
        return ((rightspeed * 6 * Math.PI * 10) / (1440 * 12));
    }


    public double averageSpeed() {
        return (leftSpeed() + rightSpeed()) / 2;
    }
    public double getYaw() {
        return navx.getYaw();
    }
    public double getTurnVelocity(){
        return navx.getRate();
    }
    public void resetEncoders() {
        backleft.setSelectedSensorPosition(0, 0, 5); //5 ms
        backright.setSelectedSensorPosition(0, 0, 5);
    }
    public void resetGyro(){
        navx.reset(); //takes some time
    }

}
