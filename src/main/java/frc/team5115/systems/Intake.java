package frc.team5115.systems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import frc.team5115.Constants;


public class Intake {

	DigitalInput cubeDetectorL;
	DigitalInput cubeDetectorR;
	DoubleSolenoid cubeSolenoidLeft;
	DoubleSolenoid cubeSolenoidRight;
	DoubleSolenoid intakeLifterLeft;
	DoubleSolenoid intakeLifterRight;
	public Spark intakeWheelsLeft;
	public Spark intakeWheelsRight;
	public Intake(){
		cubeDetectorL = new DigitalInput(Constants.CUBE_DETECTOR_L);
		cubeDetectorR = new DigitalInput(Constants.CUBE_DETECTOR_R);
		cubeSolenoidLeft = new DoubleSolenoid(Constants.PNEUMATIC_PCM_0_ID, Constants.INTAKE_FORWARD_CHANNEL_LEFT, Constants.INTAKE_REVERSE_CHANNEL_LEFT);
		cubeSolenoidRight = new DoubleSolenoid(Constants.PNEUMATIC_PCM_0_ID, Constants.INTAKE_FORWARD_CHANNEL_RIGHT, Constants.INTAKE_REVERSE_CHANNEL_RIGHT);
		intakeLifterLeft = new DoubleSolenoid(Constants.PNEUMATIC_PCM_0_ID, Constants.LIFTER_FORWARD_CHANNEL_LEFT, Constants.LIFTER_REVERSE_CHANNEL_LEFT);
		intakeLifterRight = new DoubleSolenoid(Constants.PNEUMATIC_PCM_0_ID, Constants.LIFTER_FORWARD_CHANNEL_RIGHT, Constants.LIFTER_REVERSE_CHANNEL_RIGHT);
		intakeWheelsLeft = new Spark(Constants.INTAKE_SPARK_LEFT);
		intakeWheelsRight = new Spark(Constants.INTAKE_SPARK_RIGHT);
	}
	
	public void grip(){
		System.out.println("grip");
		cubeSolenoidLeft.set(DoubleSolenoid.Value.kForward);
		cubeSolenoidRight.set(DoubleSolenoid.Value.kForward);
	}
	
	public void relax(){
		System.out.println("relax");
		cubeSolenoidLeft.set(DoubleSolenoid.Value.kOff);
		cubeSolenoidRight.set(DoubleSolenoid.Value.kOff);
	}
	public void release(){
		System.out.println("release");
		cubeSolenoidLeft.set(DoubleSolenoid.Value.kReverse);
		cubeSolenoidRight.set(DoubleSolenoid.Value.kReverse);
	}
	public void liftIntake(){
		intakeLifterLeft.set(DoubleSolenoid.Value.kForward);
		intakeLifterRight.set(DoubleSolenoid.Value.kForward);
	}
	public void lowerIntake(){
		intakeLifterLeft.set(DoubleSolenoid.Value.kReverse);
		intakeLifterRight.set(DoubleSolenoid.Value.kReverse);
	}
	
	public boolean isCube(){
		//System.out.println("l" + cubeDetectorL.get());
		//System.out.println("r" + cubeDetectorR.get());
		return !cubeDetectorL.get() && !cubeDetectorR.get();
	}
	public void intake(double dir){
		intakeWheelsLeft.set(-dir);
		//intakeWheelsRight.set(dir * 1.75);
	}
	public void bump(){
		intakeWheelsRight.set(-0.1);
	}
	
}