package frc.team5115.robot;

import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team5115.Constants;
import frc.team5115.commands.Elevator.ElevatorMove;
import frc.team5115.commands.MoveAndAvoid;
import frc.team5115.systems.*;
import edu.wpi.first.wpilibj.TimedRobot;
import jdk.internal.util.xml.impl.Input;

public class Robot extends TimedRobot {

    public static DriveTrain drivetrain;
    public static Elevator elevator;
    public static Grip grip;
    public static Intake intake;
    public static InputManager input;

    @Override
    public void robotInit() {
        drivetrain = new DriveTrain();
        elevator = new Elevator();
        grip = new Grip();
        intake = new Intake();
        input = new InputManager();
    }

    @Override
    public void disabledInit() { }

    @Override
    public void autonomousInit() { }

    @Override
    public void teleopInit() {
        Scheduler.getInstance().run();
        if(InputManager.switchHeight()){
            new MoveAndAvoid(Constants.SWITCH_HEIGHT);
        } else if(InputManager.scaleHeight()){
            new MoveAndAvoid(Constants.SCALE_HEIGHT);
        } else if(InputManager.returnHeight()){
            new MoveAndAvoid(Constants.RETURN_HEIGHT);
        }
    }

    @Override
    public void testInit() { }


    @Override
    public void disabledPeriodic() { }
    
    @Override
    public void autonomousPeriodic() { }

    @Override
    public void teleopPeriodic() { }

    @Override
    public void testPeriodic() { }
}