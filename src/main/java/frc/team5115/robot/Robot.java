package frc.team5115.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team5115.auto.Auto;
import frc.team5115.auto.AutoDrive;
import frc.team5115.auto.ObjectivePositions;
import frc.team5115.auto.TrajectoryGenerator;
import frc.team5115.statemachines.Drive;
import frc.team5115.systems.DriveTrain;

public class Robot extends TimedRobot {

    //define
    public static Auto auto;
    public static DriveTrain drivetrain;
    public static Drive drive;
    public static TrajectoryGenerator tg;
    public static ObjectivePositions OP;
    public static SendableChooser positionChooser;
    public static SendableChooser strategyChooser;

    public void robotInit() {
        //instantiate
        drivetrain = new DriveTrain();
        drive = new Drive();
        tg = new TrajectoryGenerator();

    }

    public void autonomousInit(){
        drivetrain.inuse = false;
        drivetrain.drive(0,0);
        drivetrain.resetEncoders();

        //OP = new ObjectivePositions((char)positionChooser.getSelected());

        //System.out.println((int)strategyChooser.getSelected());
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
    }
    public void autonomousPeriodic(){
        auto.update();
    }
    public void teleopPeriodic() {
        drive.update();
    }


}