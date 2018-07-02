package frc.team5115.systems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.kauailabs.navx.frc.AHRS;
import frc.team5115.Constants;
import frc.team5115.robot.Robot;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.I2C;


public class DriveTrain {
    //auto values for later
    double max = 0;
    double min = 0;
    double lastValue = 0;
    public boolean inuse;
    //define motor objects
    TalonSRX frontleft;
    TalonSRX frontright;
    TalonSRX backleft;
    TalonSRX backright;
    //define gyroscope object
    AHRS navx;

    //auto values for later
    public double lastLeftSpeed = 0;
    public double lastRightSpeed = 0;

    //grabbing our direction for encoder data
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

        //define final direction
        direction = 1;

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

        //set out "speed" or voltage output to left and right speeds
        backleft.set(ControlMode.PercentOutput, -leftspeed);
        backright.set(ControlMode.PercentOutput, rightspeed);
    }
    public void leftFollow(double left, double turn){
        double leftspeed = left + turn;
        backleft.set(ControlMode.PercentOutput, -leftspeed);
    }
    public void rightFollow(double right, double turn){
        double rightspeed = right - turn;
        backright.set(ControlMode.PercentOutput, rightspeed);
    }
    public double leftDist() {
        //get encoder data (position)
        double leftDist = -direction * backleft.getSelectedSensorPosition(0);
        //convert to feet
        //formula is data / ticksperrevolution * wheel diameter * pi(area) / 12 (what is 12?)
        return leftDist / 1440 * 6 * Math.PI / 12;
    }

    public double rightDist() {
        System.out.println(backright.getSelectedSensorPosition(0));
        double rightDist = direction * backright.getSelectedSensorPosition(0);
        return rightDist / 1440 * 6 * Math.PI / 12;
    }
    public int leftDistRaw(){
        return -backleft.getSelectedSensorPosition(0);
    }
    public int rightDistRaw(){
        return backright.getSelectedSensorPosition(0);
    }

    public double distanceTraveled() {
        //grab approx distance travelled by averaging the 2 together
        return (rightDist() + leftDist()) / 2;
    }

    public double leftSpeed() {
        //get derivative data (velocity)
        double leftspeed = -backleft.getSelectedSensorVelocity(0);
        //convert to feet/sec
        //formula is (data * wheel diameter * pi(area) * 10(what is 10?))/ (ticksperrevolution * 12(what is 12?)
        return ((leftspeed * 6 * Math.PI * 10) / (1440 * 12));

    }

    public double rightSpeed() {
        double rightspeed = backright.getSelectedSensorVelocity(0);
        return ((rightspeed * 6 * Math.PI * 10) / (1440 * 12));
    }

    //grab approx speed by averaging the 2 together
    public double averageSpeed() {
        return (leftSpeed() + rightSpeed()) / 2;
    }

    //grab gyroscope data on all 3 axis
    public double getPitch(){
        return navx.getPitch();
    }

    public double getRoll(){
        return navx.getRoll();
    }

    public double getYaw() {
        return navx.getYaw();
    }

    public double getTurnVelocity() {
        return navx.getRate();
    }
    public double forwarAccel(){
        return -navx.getRawAccelY(); //this way points forward on our robot
    }
    // This method resets the values given by the encoders to a default of 0
    //manually set encoder data back to 0
    public void resetEncoders() {
        backleft.setSelectedSensorPosition(0, 0, 5); //5 ms
        backright.setSelectedSensorPosition(0, 0, 5);
    }
    //manually set gyro data back to 0
    public void resetGyro(){
        navx.reset(); //takes some time
    }

}
