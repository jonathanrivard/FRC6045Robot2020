/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //Settings
    public static int SETTING_DRIVE_TYPE = 1; //0=tank ; 1=arcade
    public static double SETTING_SPIN_UP = 1.5;
    //Joysticks
    public static int USB_XBOX_CONTROLLER = 3; //Xbox controller that may or may not be plugged in
    public static int USB_RIGHT_JOYSTICK = 2; //Rightmost joystick to the right of driver station
    public static int USB_LEFT_JOYSTICK = 1; //Leftmost joystick to the right of driver station
    public static int USB_MAIN_JOYSTICK = 0; //Single joystick to left of driver station
    //Buttons
    //  Left Joystick
    public static int BUTTON_L_INTAKE_WHEEL_IN = 1;
    public static int BUTTON_L_INTAKE_ELEVATOR_UP = 2;
    public static int BUTTON_L_INTAKE_WHEEL_OUT = 3;
    public static int BUTTON_L_TURN_THREE_TIMES = 4;
    public static int BUTTON_L_TURN_ONCE = 5;
    //  Right Joystick
    public static int BUTTON_R_SHOOT = 1;
    public static int BUTTON_R_INTAKE_ElEVATOR_UP_R = 2;
    public static int BUTTON_R_LIMELIGHT = 3;
    public static int BUTTON_R_INTAKE_ELEVATOR_DOWN = 4;
    //  Main Joystick
    public static int BUTTON_M_SHOOT = 2;
    public static int BUTTON_M_INTAKE_AND_ELEVATOR = 12;
    public static int BUTTON_M_INTAKE_AND_ELEVATOR_TWO = 11;
    public static int BUTTON_M_ELEVATOR_UP = 8;
    public static int BUTTON_M_ELEVATOR_DOWN = 10;
    public static int BUTTON_M_INTAKE_WHEEL_IN = 7;
    public static int BUTTOn_M_INTAKE_WHEEL_OUT = 9;
    public static int BUTTON_M_LIMELIGHT = 1;
    //Motors
    public static int PORT_MOTOR_FRONT_LEFT = 0; //Talon SRX CAN
    public static int PORT_MOTOR_BACK_LEFT = 1; //Talon SRX CAN
    public static int PORT_MOTOR_FRONT_RIGHT = 3; //Talon SRX CAN
    public static int PORT_MOTOR_BACK_RIGHT = 4; //Talon SRX CAN
    public static int PORT_MOTOR_SHOOTER_WHEEL_ONE = 6; //Victor SPX CAN
    public static int PORT_MOTOR_SHOOTER_WHEEL_TWO = 7; //Victor SPX CAN
    public static int PORT_MOTOR_INTAKE_ELEVATOR = 5; //Victor SPX CAN
    public static int PORT_MOTOR_INTAKE_WHEEL = 8; //Victor SPX CAN
    public static int PORT_MOTOR_LIFT = 1; //Victor SP PWM
    public static int PORT_MOTOR_INTAKE_WINCH = 0; //Spark PWM
    public static int PORT_MOTOR_COLOR_WHEEL = 2;
    //Sensors
    public static int PORT_ANALOG_ULTRASONIC_TOP = 1;
    public static int PORT_ANALOG_ULTRASONIC_BOTTOM = 0;
    //Scalers
    public static double SCALER_XBOX_CONTROLLER = 0.7;
    public static double SCALER_INTAKE_WHEEL = 0.5;
    public static double SCALER_INTAKE_ELEVATOR = 0.25; //The Zuck Constant
    public static double SCALER_INTAKE_WINCH = 0.6;
    public static double SCALER_LIFT = 1.0;
    public static double SCALER_DRIVE_FORWARD = 1.0;
    public static double SCALER_DRIVE_SIDE = 0.8;
    public static double SCALER_ULTRASONIC_SENSOR = 0.125;
    public static double SCALER_COLOR_WHEEL = 0.3;
    //Threshholds
    public static double THRESHHOLD_JOYSTICKS = 0.01;
    public static double THRESHHOLD_LIMELIGHT_X = 1.5;
    //Limelight Constants
    public static boolean LIMELIGHT_DEBUG = false;
    public static double LIMELIGHT_TURN_MAX_X = 22.0;
    public static double LIMELIGHT_TURN_MAX_SPEED = 0.5;
    public static double LIMELIGHT_TURN_MIN_SPEED = 0.3;
    public static double LIMELIGHT_DELAY_START = 500;
}
