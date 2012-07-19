package com.team4element;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    
    /*
     * OPERATOR INTERFACE
     */
    //JOYSTICKS
    public static final int leftJoystick = 1;
    public static final int rightJoystick = 2;
    public static final int operatorJoystick = 3;
    
    //BUTTON DEFINITIONS
    public static final int wedgeUpButton = 11;
    public static final int wedgeToggleButton = 1;
    public static final int ConveyorTestButton = 10;
    public static final int shootButton = 1;
    public static final int turretLeft = 4;
    public static final int turretRight = 5;
    public static final int turretAuto = 6;
    public static final int alignButton = 4;
    public static final int reverseConveyorButton = 3;
    
    public static final int reverseDriveButton = 2;//LEFT
    public static final int reverseDriveJoystick = leftJoystick;
    
    public static final int rollerJoystick = operatorJoystick;
    public static final int rollerButton = 3;
    
    /*
     * ACUTATOR DEFINITIONS
     */
    
    //DRIVETRAIN MOTORS
    public static final int leftMotor = 1;
    public static final int leftBackMotor = 2;
    public static final int rightMotor = 3;
    public static final int rightBackMotor = 4;
    
    //BALLCONVEYER MOTORS
    public static final int ballRollerAndBottomConveyerMotor = 9;
    public static final int topConveyerMotor = 8;
    
    //TURRET MOTORS
    public static final int windowMotor = 6;
    
    //SHOOTER MOTORS
    public static final int shooterTopMotor = 7;
    public static final int shooterBottomMotor = 5;
    
    //BRIDGEMANIP MOTORS
    public static final int WedgeVic = 6;
    
    /*
     * SENSOR DEFINITIONS
     */
    
    //BALLCONVEYER SENSORS
    public static final int topLightSensor = 4;
    public static final int bottomLightSensor = 3;
   
    //TURRET SENSORS
    public static final int turretAChannel = 5;
    public static final int turretBChannel = 6;
    
    //SHOOTER SENSORS
    public static final int shooterTopEncoderA = 7;
    public static final int shooterTopEncoderB = 8;
    public static final int shooterBottomEncoderA = 9;
    public static final int shooterBottomEncoderB = 10;

    //BRIDGEMANIP SENSORS
    public static final int wedgeTopLimitSwitch = 1;
    public static final int wedgeDownLimitSwitch = 2;
    
    //ULTRASUOND SENSOR
    public static final int gyro = 1;
    public static final int ultrasoundSensor = 2;
    public static int wedgeManualDown = 7;
    public static int wedgeManualUp = 6;
}


/*
 * Joystick 1 Controls:
 *      Button 6: Horse Down
 *      Button 7: Horse Up
 * Operator Controls:
 *      Button 1: Shooter Start (Release to stop...)
 *      Button 2: Top Conveyor Up
 *      Button 3: Lower and Top Conveyer Up (Siml.)
 *      Button 4: Lower and Top Conveyer Down (Siml.)
 * 
 * 
 * 
 * 
 */