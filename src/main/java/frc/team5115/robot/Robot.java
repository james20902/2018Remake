package frc.team5115.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.team5115.auto.*;
import frc.team5115.statemachines.CubeManipulator;
import frc.team5115.statemachines.Drive;
import frc.team5115.statemachines.ElevatorManager;
import frc.team5115.statemachines.IntakeManager;
import frc.team5115.systems.Grip;
import frc.team5115.systems.DriveTrain;
import frc.team5115.systems.Elevator;
import frc.team5115.systems.Intake;

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
        tg = new TrajectoryGenerator();
        tw = new TrajectoryWriter();
        intake = new Intake();
        grip = new Grip();
        elevator = new Elevator();
        IM = new IntakeManager();
        EM = new ElevatorManager();
        CM = new CubeManipulator();

    }

    public void autonomousInit(){
        drivetrain.inuse = false;
        drivetrain.drive(0,0);
        drivetrain.resetEncoders();
        OP = new ObjectivePositions((char)positionChooser.getSelected());
        System.out.println((int)strategyChooser.getSelected());
        auto = new Auto();
        auto.setState(Auto.INIT);
    }

    public void teleopInit() {
        //execute
        //first, reset EVERYTHING
        drivetrain.inuse = false;
        drivetrain.drive(0,0);
        drivetrain.resetEncoders();

        //now set it to accept controller input
        drive.setState(Drive.DRIVING);
        CM.setState(CubeManipulator.INPUT);
    }
    public void autonomousPeriodic(){
        auto.update();
    }
    public void teleopPeriodic() {
        drive.update();
        CM.update();
    }


}