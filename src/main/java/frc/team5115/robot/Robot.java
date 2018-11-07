package frc.team5115.robot;

import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team5115.systems.*;
import edu.wpi.first.wpilibj.TimedRobot;

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