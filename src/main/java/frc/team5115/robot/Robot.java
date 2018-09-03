package frc.team5115.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.team5115.auto.*;
import frc.team5115.statemachines.*;
import frc.team5115.systems.*;


public class Robot extends TimedRobot {

    //define
    public static Auto auto;
    public static DriveTrain drivetrain;
    public static Drive drive;
    public static TrajectoryGenerator tg;
    public static TrajectoryWriter tw;
    public static ObjectivePositions OP;
    public static Intake intake;
    public static Grip grip;
    public static Elevator elevator;
    public static ElevatorManager EM;
    public static CubeManipulator CM;
    public static IntakeManager IM;
    public static SendableChooser positionChooser;
    public static SendableChooser strategyChooser;

    public void robotInit() {
        //instantiate
        drivetrain = new DriveTrain();
        drive = new Drive();
        //tg = new TrajectoryGenerator();
        //tw = new TrajectoryWriter();
        intake = new Intake();
        grip = new Grip();
        elevator = new Elevator();
        IM = new IntakeManager();
        EM = new ElevatorManager();
        CM = new CubeManipulator();

    }

    public void autonomousInit(){
        //When the autonomous starts,
        //we will assume the robot just turned on, and will say the drivetrain is currently not in use
        drivetrain.inuse = false;
        drivetrain.drive(0,0);
        drivetrain.resetEncoders();
        OP = new ObjectivePositions((char)positionChooser.getSelected());
        System.out.println((int)strategyChooser.getSelected());
        auto = new Auto();
        //we set our auto file to set all of our possible strategies to init
        auto.setState(Auto.INIT);
    }

    public void teleopInit() {
        //assume the robot just turned on, and the drivetrain is not in use
        drivetrain.inuse = false;
        drivetrain.drive(0,0);
        drivetrain.resetEncoders();

        //allow our drivetrain and subsystems to accept controller input
        drive.setState(Drive.DRIVING);
        CM.setState(CubeManipulator.ARMUP);
        EM.setState(ElevatorManager.MOVING);
    }
    public void autonomousPeriodic(){ /*do this every 5ms*/auto.update(); }
    public void teleopPeriodic() {
        //collect input every 5ms
        drive.update();
        CM.update();
    }

    public void disabledInit() {
        //stop accepting input
        CM.setState(CubeManipulator.STOP);
    }


    public void disabledPeriodic() {
        //keep everything stopped for good measure
        drivetrain.drive(0,0);
        EM.setState(CubeManipulator.STOP);
        IM.setState(IntakeManager.STOP);
        grip.stop();

    }

}