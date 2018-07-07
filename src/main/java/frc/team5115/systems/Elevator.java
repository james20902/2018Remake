package frc.team5115.systems;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.team5115.Constants;


public class Elevator {

    double lastAngle = 0;
    public boolean movingArm = false;
    TalonSRX armMover;

    public Elevator(){
        armMover = new TalonSRX(Constants.ELEVATORMOVER);
        armMover.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 5);
        armMover.configSetParameter(ParamEnum.eFeedbackNotContinuous, 1, 0x00, 0x00, 0x00);
        armMover.configForwardSoftLimitThreshold(Constants.POT_THRESHOLD, 5);
        armMover.configForwardSoftLimitEnable(false, 0);
    }

    public double getAngle(){
        return armMover.getSelectedSensorPosition(0);
    }

    public double getAngleSpeed(){
        return armMover.getSelectedSensorVelocity(0);
    }

    public void move(double speed){
        movingArm = Math.abs(speed) > 0.1;

        armMover.set(ControlMode.PercentOutput, speed);
    }

    public boolean maxHeight(){
        return (getAngle() + Constants.ELEVATOR_THRESHOLD >= Constants.ELEVATOR_MAX);
    }
    public boolean minHeight(){
        return (getAngle() - Constants.ELEVATOR_THRESHOLD <= Constants.ELEVATOR_MIN);
    }
}
