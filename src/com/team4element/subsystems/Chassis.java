/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.subsystems;

import com.team4element.RobotMap;
import edu.wpi.first.wpilibj.Jaguar;
import com.team4element.commands.OperatorTankDrive;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableGyro;
import edu.wpi.first.wpilibj.smartdashboard.SendablePIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author intern
 * @author Justin
 */
public class Chassis extends Subsystem implements PIDOutput {
    private double speed;
    private final Jaguar leftMotor;
    private final Jaguar leftBackMotor;
    private final Jaguar rightMotor;
    private final Jaguar rightBackMotor;
    double distancePerPulse;

    public void resetGyro() {
        gyro.reset();
    }
    
    public static class ClosedLoopMode {
        private final int value;
        
        public static final ClosedLoopMode NONE = new ClosedLoopMode(0);
        public static final ClosedLoopMode GYRO = new ClosedLoopMode(1);
        public static final ClosedLoopMode STRAIGHT = new ClosedLoopMode(1);
        public static final ClosedLoopMode CAMERA = new ClosedLoopMode(1);
        
        private ClosedLoopMode(int value) {
            this.value = value;
        }
    }
    
    private final SendableGyro gyro;
    private SendablePIDController pid;
    private ClosedLoopMode clMode = ClosedLoopMode.NONE;
    
    protected void initDefaultCommand() {
        setDefaultCommand( new OperatorTankDrive() );
    }

    public Chassis() {
        leftMotor = new Jaguar(RobotMap.leftMotor);
        leftBackMotor = new Jaguar(RobotMap.leftBackMotor);
        rightMotor = new Jaguar(RobotMap.rightMotor);
        rightBackMotor = new Jaguar(RobotMap.rightBackMotor);
        distancePerPulse = Math.PI * 8 / (360 * 14 / 3);
        
        gyro = new SendableGyro(RobotMap.gyro);
        double Kp = Preferences.getInstance().getDouble("GyroP", 0);
        double Ki = Preferences.getInstance().getDouble("GyroI", 0);
        double Kd = Preferences.getInstance().getDouble("GyroD", 0);
        pid = new SendablePIDController(Kp, Ki, Kd, gyro, this);
        pid.setInputRange(0, 360);
        pid.setContinuous(true);
        SmartDashboard.putData("Chassis Gyro", gyro);
    }
    
    public void tankDrive(double left, double right) {
        if (clMode == ClosedLoopMode.NONE) {
                leftMotor.set(left);
                leftBackMotor.set(left);
                rightMotor.set(-right);
                rightBackMotor.set(-right);
        }
    }
    
    public void drive(double speed, double turn) {
        if (clMode == ClosedLoopMode.NONE) {
            //drive.arcadeDrive(speed, turn);
        } else if (clMode == ClosedLoopMode.GYRO) {
            //TODO
        }
    }
    
    public double getAngle() {
        return gyro.getAngle();
    }
    //The buttons on the driver station
    public void driverButtons(){
        //TODO: Add object from bridge manip class to map buttons to trigger
    }
    
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    
    public void setAngleSetPoint(double angle) {
        pid.setSetpoint(angle);
    }
    
    public void setAngleSetPointRelative(double angle) {
        pid.setSetpoint(pid.getSetpoint() + angle);
    }
    
    public boolean atAngleSetPoint() {
        return pid.onTarget();
    }
   
    public void setClosedLoopMode(ClosedLoopMode mode) {
        if (mode == ClosedLoopMode.GYRO || mode == ClosedLoopMode.CAMERA) {
            pid.enable();
        } else if (clMode == ClosedLoopMode.GYRO || clMode == ClosedLoopMode.STRAIGHT || clMode == ClosedLoopMode.CAMERA) {
            pid.disable();
        }
        clMode = mode;
    }
    
    public ClosedLoopMode getClosedLoopMode() {
        return clMode;
    }
    
    public void pidWrite(double value) {
        if (clMode == ClosedLoopMode.GYRO) {
            //drive.arcadeDrive(0, value);
        } else if (clMode == ClosedLoopMode.STRAIGHT) {
            //drive.arcadeDrive(speed, value);
        }
    }
}
