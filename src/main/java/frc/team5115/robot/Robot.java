package frc.team5115.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team5115.Constants;
import frc.team5115.UDPClient;
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
    public static GripManager GM;
    public static SendableChooser positionChooser;
    public static SendableChooser strategyChooser;
    public static SendableChooser colorTarget;
    public static DriverStation DS;
    public static UDPClient udpclient;

    public static double kpline = 0;
    public static double kiline = 0;
    public static double kdline = 0;

    public static double kpturn = 0;
    public static double kiturn = 0;
    public static double kdturn = 0;

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
        GM = new GripManager();
        DS = DriverStation.getInstance();

        positionChooser = new SendableChooser();
        positionChooser.addDefault("Left", 'L');
        positionChooser.addObject("Right", 'R');
        positionChooser.addObject("Center", 'C');
        SmartDashboard.putData("Position", positionChooser);

        strategyChooser = new SendableChooser();
        strategyChooser.addDefault("Switch", 1);
        strategyChooser.addObject("Scale", 2);
        strategyChooser.addObject("Everything broke, just cross the line", 3);
        SmartDashboard.putData("Strategy", strategyChooser);

        SmartDashboard.putBoolean("Manual Control?", CM.dashControl);
        SmartDashboard.putNumber("Drive Controller", CM.driveSpeed);
        //SmartDashboard.putNumber("Arm Controller", CM.armSpeed);

        //test for bunnybots
//        colorTarget = new SendableChooser();
//        colorTarget.addDefault("Red", "red");
//        colorTarget.addObject("Blue", "blue");
//
//        udpclient = new UDPClient("10.51.15.30", 5803);
//        udpclient.start();

        SmartDashboard.putNumber("P line", kpline);
        SmartDashboard.putNumber("I line", kiline);
        SmartDashboard.putNumber("D line", kdline);

        SmartDashboard.putNumber("P turn", kpturn);
        SmartDashboard.putNumber("I turn", kiturn);
        SmartDashboard.putNumber("D turn", kdturn);
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
    public void testInit(){
        drivetrain.resetGyro();
        drivetrain.resetEncoders();
        auto = new Auto(4);
    }


    public void autonomousPeriodic(){ /*do this every 5ms*/auto.update(); System.out.println("autoing"); }
    public void teleopPeriodic() {
        //collect input every 5ms
        drive.update();
        CM.update();
    }
    public void testPeriodic(){
//        Robot.IM.update();
//        System.out.println(udpclient.getLastResponse());
//        if (udpclient.getLastResponse() == colorTarget.getSelected()){
//            Robot.IM.setState(IntakeManager.INTAKE);
//        } else {
//            Robot.IM.setState(IntakeManager.PASS);
//        }
        auto.update();
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