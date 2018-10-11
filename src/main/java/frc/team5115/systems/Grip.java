package frc.team5115.systems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.team5115.Constants;

public class Grip {
    DoubleSolenoid grabberSolenoid;


    public Grip(){
        grabberSolenoid = new DoubleSolenoid(Constants.PNEUMATIC_PCM_1_ID, Constants.CARRIAGE_FORWARD_CHANNEL, Constants.CARRIAGE_REVERSE_CHANNEL);
    }

    public void grip(){
        grabberSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    public void release(){
        grabberSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    public void stop(){
        grabberSolenoid.set(DoubleSolenoid.Value.kOff);
    }

}
