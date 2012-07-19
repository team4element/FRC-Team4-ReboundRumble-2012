/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.subsystems;

import com.sun.squawk.util.MathUtils;
import com.team4element.RobotMap;
import com.team4element.components.RampLimitedSpeedController;
import com.team4element.components.SmoothedRateEncoder;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendablePIDController;
import com.team4element.components.LookupTable;
import com.team4element.components.PIDFFController;

/**
 *
 * @author sysadmin
 */
public class Turret extends Subsystem {
    
    private final double IN_TO_CM_CONVERSION = 2.54;
    private final double IN_TO_FEET_CONVERSION = 12;
    private final double RAMP_LIMIT = .1;
    
    //1.9689 is the small wheel diameter
    //13.5 is the big wheel diameter, divide by that   
    private final double TURRET_COUNTS_TO_DEGREES = 1.9689/13.5;
    private final double TURRET_DEGREES_TO_COUNTS = 13.5/1.9689;
    
    //private Jaguar windowMotor;
    
    private SpeedController shooterTopMotor;
    private SpeedController shooterBottomMotor;
    private PIDFFController shooterTopPID;
    private PIDFFController shooterBottomPID;
    private Encoder shooterTopEncoder;
    private Encoder shooterBottomEncoder;
    
    //private SendablePIDController turretPID;
    private Encoder turretEncoder;
    public final double TKmax;
    public final double TKmin;
    private final UltrasoundSensor ultrasoundsensor;
    private LookupTable distanceTable = new LookupTable();
    private LookupTable rpmTable = new LookupTable();
    
    private boolean isAuto = false;
    private double backspin;
    
    public Turret() {
        ultrasoundsensor = new UltrasoundSensor(RobotMap.ultrasoundSensor);
        //windowMotor = new Jaguar(RobotMap.windowMotor);//Digital Modual, Channel
        turretEncoder = new Encoder(new DigitalInput(RobotMap.turretAChannel), 
                                          new DigitalInput(RobotMap.turretBChannel));
        turretEncoder.setDistancePerPulse(1);
        turretEncoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
        turretEncoder.start();
        double TKp = Preferences.getInstance().getDouble("TurretP", .01);
        double TKi = Preferences.getInstance().getDouble("TurretI", 0);
        double TKd = Preferences.getInstance().getDouble("TurretD", 0);
        TKmax = Preferences.getInstance().getDouble("TurretMax", 90);
        TKmin = Preferences.getInstance().getDouble("TurretMin", -90);
        //turretPID = new SendablePIDController(TKp, TKi, TKd, turretEncoder, windowMotor);
        //turretPID.setInputRange(TKmin, TKmax);
        //SmartDashboard.putData("Turret Encoder", turretPID);
        //probably not continuous since it's going to have a deadband
        //pid.setContinuous();
        
        //The gearing from the CIMple gearbox to the shooter wheels is 4:1
        //This means that one turn of the encoder wheel (which connected 1:1 to
        //to the gearbox output shaft) is equal to 4 turns of the shooter wheel.
        //Since we want the RPM of the shooter wheels the "distance per pulse" 
        //should be (1/360) * (1/4) [the encoder has 360 pulses per rev]
        double distancePerPulse = 1;
        shooterTopEncoder = new SmoothedRateEncoder(
                new DigitalInput(RobotMap.shooterTopEncoderA), 
                new DigitalInput(RobotMap.shooterTopEncoderB),
                4);
        shooterTopEncoder.setDistancePerPulse(distancePerPulse);
        shooterTopEncoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kRate);
        shooterTopEncoder.start();
        shooterTopMotor = new RampLimitedSpeedController(new Jaguar(RobotMap.shooterTopMotor), RAMP_LIMIT);
        double STKp = Preferences.getInstance().getDouble("ShooterTP", .001);
        double STKi = Preferences.getInstance().getDouble("ShooterTI", 0);
        double STKd = Preferences.getInstance().getDouble("ShooterTD", 0.01);
        double STKff = Preferences.getInstance().getDouble("ShooterTff", 1/1500);
        shooterTopPID = new PIDFFController(STKp, STKi, STKd, STKff, shooterTopEncoder, shooterTopMotor);
        //NEVER EVER EVER drive the motors backwards
        shooterTopPID.setOutputRange(0, 1);
        shooterTopPID.disable();
        shooterTopPID.setInputRange(0, 1500);
        shooterTopPID.setTolerance(15);
        //SmartDashboard.putData("Top Shooter PID", shooterTopPID);
        
        shooterBottomEncoder = new SmoothedRateEncoder(
                new DigitalInput(RobotMap.shooterBottomEncoderA), 
                new DigitalInput(RobotMap.shooterBottomEncoderB),
                4);
        shooterBottomMotor = new RampLimitedSpeedController(new Jaguar(RobotMap.shooterBottomMotor), RAMP_LIMIT);
        shooterBottomEncoder.setDistancePerPulse(distancePerPulse);
        shooterBottomEncoder.start();
        shooterBottomEncoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kRate);
        double SBKp = Preferences.getInstance().getDouble("ShooterBP", .001);
        double SBKi = Preferences.getInstance().getDouble("ShooterBI", 0);
        double SBKd = Preferences.getInstance().getDouble("ShooterBD", 0.01);
        double SBKff = Preferences.getInstance().getDouble("ShooterBff", 1/1500);
        shooterBottomPID = new PIDFFController(SBKp, SBKi, SBKd, SBKff, shooterBottomEncoder, shooterBottomMotor);
        //NEVER EVER EVER drive the motors backwards
        shooterBottomPID.setOutputRange(0, 1);
        shooterBottomPID.disable();
        shooterBottomPID.setInputRange(0, 1500);
        shooterBottomPID.setTolerance(15);
        //SmartDashboard.putData("Top Bottom PID", shooterBottomPID);
        
        distanceTable.addEntry(.08438, 15);
        distanceTable.addEntry(.06875, 20);
        distanceTable.addEntry(.05625, 25);
        distanceTable.addEntry(.04688, 30);
        distanceTable.addEntry(.04063, 35);
        
        rpmTable.addEntry(5, 250);
        rpmTable.addEntry(35, 1500);
    }
    
    public void resetConstants() {
        double TKp = Preferences.getInstance().getDouble("TurretP", .01);
        double TKi = Preferences.getInstance().getDouble("TurretI", 0);
        double TKd = Preferences.getInstance().getDouble("TurretD", 0);
        double TKmax = Preferences.getInstance().getDouble("TurretMax", 90);
        double TKmin = Preferences.getInstance().getDouble("TurretMin", -90);
        //turretPID.setPID(TKp, TKi, TKd);
        
        double STKp = Preferences.getInstance().getDouble("ShooterTP", .001);
        double STKi = Preferences.getInstance().getDouble("ShooterTI", 0);
        double STKd = Preferences.getInstance().getDouble("ShooterTD", 0.01);
        double STKff = Preferences.getInstance().getDouble("ShooterTff", 1/1500);
        shooterTopPID.setPID(STKp, STKi, STKd, STKff);
        
        double SBKp = Preferences.getInstance().getDouble("ShooterBP", .001);
        double SBKi = Preferences.getInstance().getDouble("ShooterBI", 0);
        double SBKd = Preferences.getInstance().getDouble("ShooterBD", 0.01);
        double SBKff = Preferences.getInstance().getDouble("ShooterBff", 1/1500);
        shooterBottomPID.setPID(SBKp, SBKi, SBKd, SBKff);
    }

    protected void initDefaultCommand() {
    }
    
    public void setIsAuto(boolean auto){
        isAuto = auto;
    }
    
    public boolean isAuto() {
        return isAuto;
    }
    
    public void setSpeed(double speed) {
        shooterTopPID.setSetpoint(speed * (1-backspin));
        shooterBottomPID.setSetpoint(speed);
    }
    
    public void setBackspin(double backspin) {
        this.backspin = backspin;
    }
    
    public void setSpeedWithCamera(double VPixels) {
        //double value = distanceTable.calculate(VPixels);
        double distance = .9152967/MathUtils.pow(VPixels, 1.1421);
        //double value = 74.254715*MathUtils.pow(5.284E-9, VPixels);//Exp
        //setSpeed(value/MAX_Distance);
        double rpm = rpmTable.calculate(distance);
        SmartDashboard.putDouble("Vertical Pixels", VPixels);
        SmartDashboard.putDouble("Camera's Distance From Hoop", distance);
        SmartDashboard.putDouble("Camera RPM", rpm);
        SmartDashboard.putBoolean("Have target", CameraSubsystem.haveRectangle());
    }
    
    private boolean shooterRunning = false;
    public void runShooter(boolean yesno) {
        shooterRunning = yesno;
        if (yesno) {
            shooterBottomPID.enable();
            shooterTopPID.enable();
            //shooterTopPID.disable();
        } else {
            setSpeed(0);
            shooterBottomPID.disable();
            shooterTopPID.disable();
            shooterBottomMotor.set(0);
            shooterTopMotor.set(0);
        }
    }
    
    public boolean isShooterRunning() {
        return shooterRunning;
    }
    
    private boolean turretRunning = false;
    public void runTurret(boolean yesno) {
        turretRunning = yesno;
        if (yesno) {
            //turretPID.enable();
        } else {
            //turretPID.disable();
        }
    }
    
    public boolean isTurrentRunning() {
        return turretRunning;
    }
    
    public void setAngle(double angle) {
        //turretPID.setSetpoint(angle * TURRET_DEGREES_TO_COUNTS);
    }
    
    public void setAngleRelative(double angle) {
        setAngle(getAngle() + angle);
    }
    
    public double getSpeed() {
        return turretEncoder.getRate();
    }
    
    public double getAngle() {
        return turretEncoder.getDistance() * TURRET_COUNTS_TO_DEGREES;
    }
    
    public boolean atSpeed() {
        return shooterTopPID.onTarget() && shooterBottomPID.onTarget();    
    }
    
    public boolean inSpread(double a, double b, double spread) {
        return (a < b * (1+spread)) && (a > b * (1-spread));
    }
    
    public boolean atAngle(double angle) {
        //return turretPID.onTarget();
        return false;
    }
    
    public boolean isNotInDeadband(double angle) {
        return (angle <= TKmax && angle >= TKmin); 
    }
    
    public boolean isInLowerDeadband(double angle) {
        return (angle <= TKmin); 
    }
    
    public boolean isInUpperDeadband(double angle) {
        return (angle >= TKmax); 
    }
    
    public boolean hasTarget() {
        return CameraSubsystem.haveRectangle();
    }
    
    public double getDeadbandMax() {
        return TKmax;
    }
    
    public double getDeadbandMin() {
        return TKmin;
    }

    public double getTopSpeed() {
        // divide by 60 because getRate returns unit per second
        // we want unit per minute
        return shooterTopEncoder.getRate();
    }

    public double getBottomSpeed() {
        //as above
        return shooterBottomEncoder.getRate();
    }
    
    public void moveLeft() {
        setAngleRelative(5);
    }
    
    public void moveRight() {
        setAngleRelative(-5);
    }
    
    public double GetRangeInFeet(){
        return ultrasoundsensor.GetRangeInInches()/IN_TO_FEET_CONVERSION;
    }
    
    public double GetRangeInCM() {
        return ultrasoundsensor.GetRangeInInches()*IN_TO_CM_CONVERSION;
    }

    public double getNeededWheelSpeed() {
        double distance = .9152967/MathUtils.pow(CameraSubsystem.getVerticalPixels(), 1.1421);
        if (distance < 15)
            distance = GetRangeInFeet();
        return getNeededWheelSpeed(distance);
    }
    
    public double getNeededWheelSpeed(double distance) {
        return rpmTable.calculate(distance);
    }

    public void dumpShooterInfo() {
        SmartDashboard.putDouble("Shooter setpoint", shooterBottomPID.getSetpoint());
        SmartDashboard.putDouble("Shooter top speed", getTopSpeed());
        SmartDashboard.putDouble("Shooter bottom speed", getBottomSpeed());
        SmartDashboard.putDouble("Shooter backspin", backspin*100);
    }
}
