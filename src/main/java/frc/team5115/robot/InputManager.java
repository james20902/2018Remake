package frc.team5115.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.team5115.Constants;
import frc.team5115.commands.Intake.*;
import frc.team5115.commands.Elevator.*;
import frc.team5115.commands.Grip.*;
import frc.team5115.commands.*;

public class InputManager {

    /**
     * InputManager is the place where inputs from the joystick go to die
     * Here, they are converted into methods for the rest of the code
     * In order to reference a joystick input, you must reference the InputManager class
     * If you don't really understand Getters and Setters you should go to Stack overflow or try (through trial and error) making them in a new project to see how they work
     */
    //define and instantiate our joystick object
    static Joystick joy = new Joystick(0);
    Button intake = new JoystickButton(joy, Constants.INTAKE),
            eject = new JoystickButton(joy, Constants.RELEASEGRIP),
            correct = new JoystickButton(joy, Constants.CORRECTCUBE),
            moveUp = new JoystickButton(joy, Constants.UP),
            moveDown = new JoystickButton(joy, Constants.DOWN);

    public InputManager(){
        intake.whileHeld(new Intake());
        intake.whenReleased(new IntakeAndGrip());
        eject.whileHeld(new Release());
        correct.whileHeld(new Correct());
        moveUp.whileHeld(new MoveAndAvoid(true));
        moveDown.whileHeld(new MoveAndAvoid(false));
    }

    //The following methods deal with the basic driving functionalities
    public static double getForward() {
        return -treatAxis(joy.getRawAxis(Constants.AXIS_Y));
    }

    public static double getTurn() {
        return joy.getRawAxis(Constants.AXIS_X);
    }

    //These methods are controlled by the nub on the top of the joystick
    public static double getHat() {
        return joy.getPOV();
    }

    public static double getThrottle() {
        // Joystick give 1 to -1 but we need 0 to 1
        //by subtracting 1 for every value the throttle gets and dividing by two we can accomplish this
        //ex: (1 - (-1) = 2) / 2 = 1
        //ex: (1 - 1 = 0) / 2 = 0
        return (1 - joy.getRawAxis(Constants.AXIS_THROTTLE)) / 2;
    }

    // Handles expo and deadband
    public static double treatAxis(double val) {
        if (Math.abs(val) < Constants.JOYSTICK_DEADBAND) {
            val = 0;
        }
        else {
            double sign = (Math.signum(val));
            val = Math.pow(Math.abs(val), Constants.JOYSTICK_EXPO);

            if(sign != Math.signum(val)){
                val *= sign;
            }
        }

        return val;
    }

    public static boolean switchHeight(){
        return getHat() == 270 || getHat() == 90;
    }
    public static boolean scaleHeight(){
        return getHat() == 0;
    }

    public static boolean returnHeight(){
        return getHat() == 180;
    }


    public static boolean killkey1(){return joy.getRawButton(Constants.KILL1);}

    public static boolean killkey2(){return joy.getRawButton(Constants.KILL2);}


    //joy.getRawButton will check if the assigned button from joystick object is being pressed, and returns t/f
    /*public static boolean kill(){
        return joy.getRawButton(Constants.KILL);
    }*/


    //for debug purposes
    public static boolean getButton(int b) {
        return joy.getRawButton(b);
    }
}