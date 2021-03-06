package frc.team5115;

public class Constants {
    // Define deadzones and axis
    public static final int AXIS_X = 0;
    public static final int AXIS_Y = 1;
    public static final int AXIS_THROTTLE = 3;
    public static final double JOYSTICK_DEADBAND = 0.025;
    public static final int JOYSTICK_EXPO = 2;

    //Actual Button Binds
    public static final int UP = 4;
    public static final int DOWN = 2;
    public static final int INTAKE = 9;
    public static final int DROP = 10;
    public static final int RELEASEGRIP = 1;

    //PID
    public static final double DELAY = 0.005;
    public static final double ARM_KP = 0.015;
    public static final double ARM_KI = 0;
    public static final double ARM_KD = 0.02;

    //Speed and Delay parameters
    public static final double ELEVATOR_SPEED_SCALE = 1;
    public static final double ELEVATOR_SPEED_SWITCH = 0.5;
    public static final double ELEVATOR_THRESHOLD = 20;
    public static final double ELEVATOR_SPEED = 0.75;
    public static final int POT_THRESHOLD = 900;
    public static final int ELEVATOR_MAX = 817;
    public static final int ELEVATOR_MIN = 0;
    public static final int ELEVATOR_STEP = 100;
    public static final double INTAKE_SPEED = 0.65;

    // Physical robot attributes (these typically shouldn't exceed 1)
    public static final double TOP_SPEED = 1;
    public static final double TOP_TURN_SPEED = 0.75;
    public static final double WHEELBASE = 0.6096;
    // DIFFERENT BETWEEN ROBOTS - DO NOT COPY
    public static final double RETURN_HEIGHT = 0;
    public static final double INTAKE_HEIGHT = 160;
    public static final double SWITCH_HEIGHT = 250;
    public static final double SCALE_HEIGHT = 720;

    //CAN motors
    public static final int FRONT_LEFT_MOTOR_ID = 3;
    public static final int FRONT_RIGHT_MOTOR_ID = 4;
    public static final int BACK_LEFT_MOTOR_ID = 1;
    public static final int BACK_RIGHT_MOTOR_ID = 2;
    public static final int PNEUMATIC_PCM_0_ID = 7;
    public static final int PNEUMATIC_PCM_1_ID = 8;
    public static final int ELEVATORMOVER = 5;

    //PWM
    public static final int INTAKE_SPARK_LEFT = 2;
    public static final int INTAKE_SPARK_RIGHT = 1;
    //DIO
    public static final int CUBE_DETECTOR_L = 9;
    public static final int CUBE_DETECTOR_R = 8;

    //PCM
    public static final int INTAKE_FORWARD_CHANNEL_LEFT = 5;	//retract; grip	//YOU CANNOT COPY THIS TO OTHER BOTS
    public static final int INTAKE_REVERSE_CHANNEL_LEFT = 4;	//extend; release
    public static final int INTAKE_FORWARD_CHANNEL_RIGHT = 6;	//retract; grip
    public static final int INTAKE_REVERSE_CHANNEL_RIGHT = 7;	//extend, release

    public static final int CARRIAGE_FORWARD_CHANNEL  = 0;		//retract; grab
    public static final int CARRIAGE_REVERSE_CHANNEL = 1;		//extend; release

    public static final int LIFTER_FORWARD_CHANNEL_LEFT = 1;	//retract; lift
    public static final int LIFTER_REVERSE_CHANNEL_LEFT = 0;	//extend; lower
    public static final int LIFTER_FORWARD_CHANNEL_RIGHT = 3;	//retract; lift
    public static final int LIFTER_REVERSE_CHANNEL_RIGHT = 2;	//extend; lower
}
