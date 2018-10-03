package frc.team5115.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team5115.auto.*;
import frc.team5115.statemachines.*;
import frc.team5115.systems.*;


public class Robot extends TimedRobot {

    //define
    public static Auto auto;
    public static DriveTrain drivetrain;
    public static Drive drive;
    public static ObjectivePositions OP;
    public static Intake intake;
    public static Grip grip;
    public static Elevator elevator;
    public static ElevatorManager EM;
    public static CubeManipulator CM;
    public static IntakeManager IM;
    public static SendableChooser positionChooser;
    public static SendableChooser strategyChooser;
    public static DriverStation DS;

    public void robotInit() {
        //instantiate
        drivetrain = new DriveTrain();
        drive = new Drive();
        intake = new Intake();
        grip = new Grip();
        elevator = new Elevator();
        IM = new IntakeManager();
        EM = new ElevatorManager();
        CM = new CubeManipulator();
        DS = DriverStation.getInstance();

        positionChooser = new SendableChooser();
        positionChooser.addDefault("Left", 'L');
        positionChooser.addObject("Right", 'R');
        positionChooser.addObject("Center", 'C');
        SmartDashboard.putData("Strategy", strategyChooser);

        strategyChooser = new SendableChooser();
        strategyChooser.addDefault("Switch", 1);
        strategyChooser.addObject("Scale", 2);
        strategyChooser.addObject("Everything broke, just cross the line", 3);
        SmartDashboard.putData("Position", positionChooser);

        SmartDashboard.putBoolean("Manual Control?", CM.dashControl);
        SmartDashboard.putNumber("Drive Controller", CM.driveSpeed);
        //SmartDashboard.putNumber("Arm Controller", CM.armSpeed);
    }

    public void autonomousInit(){
        drivetrain.resetGyro();
        drivetrain.resetEncoders();
        OP = new ObjectivePositions((char)positionChooser.getSelected());
        auto = new Auto((int)strategyChooser.getSelected());
    }


    public void teleopInit() {
        //assume the robot just turned on, and the drivetrain is not in use
        drivetrain.inuse = false;
        drivetrain.drive(0,0);
        drivetrain.resetEncoders();

        //allow our drivetrain and subsystems to accept controller input
        drive.setState(Drive.DRIVING);
        CM.setState(CubeManipulator.INPUT);
        EM.setState(ElevatorManager.MOVING);
    }
    public void autonomousPeriodic(){ /*do this every 5ms*/auto.update(); System.out.println("autoing"); }
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