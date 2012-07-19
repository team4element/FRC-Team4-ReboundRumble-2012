/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.subsystems;

import com.team4element.RobotMap;
import com.team4element.commands.tests.ManualBridgeControl;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author Justin
 */
public class BridgeManipulator extends Subsystem{

    private DigitalInput topSwitch;
    private DigitalInput bottomSwitch;

    private double currentMotorSpeed;
    private double defaultMotorSpeed;
    private final Victor wedgeMotor;
    private boolean wedgeRunning;
    
    public static class MotorMode {
        public final int value;
        
        public static final MotorMode UP = new MotorMode(1);
        public static final MotorMode NONE = new MotorMode(0);
        public static final MotorMode DOWN = new MotorMode(-1);
        
        private MotorMode(int value) {
            this.value = value;
        }
    }
    
    protected void initDefaultCommand() {
        //setDefaultCommand(new MoveBridgeManipulator(MotorMode.NONE));
        setDefaultCommand(new ManualBridgeControl());
    }

    public BridgeManipulator() {
        defaultMotorSpeed = 0.75;
        
        topSwitch = new DigitalInput(RobotMap.wedgeTopLimitSwitch);
        bottomSwitch = new DigitalInput(RobotMap.wedgeDownLimitSwitch);
        
        wedgeMotor = new Victor(RobotMap.WedgeVic);
    }
    
    
    /*
     * Up is true
     * Down is false
     */
    public void moveWedge(MotorMode position) {
        /*if(motorMode == MotorMode.DOWN && bottomSwitch.get()){
            motorMode = MotorMode.NONE;
        } else if(motorMode == MotorMode.UP && topSwitch.get()) {
            motorMode = MotorMode.NONE;
        }*/
        //-1 is backwards
        //1 is forwards
        if (!wedgeRunning) {
            wedgeMotor.set(0);
            return;
        }
        
        if (position == MotorMode.UP)
            wedgeMotor.set(-defaultMotorSpeed);
        else if (position == MotorMode.DOWN)
            wedgeMotor.set(defaultMotorSpeed);
        else
            wedgeMotor.set(0);
        
        //if we're going in the same direction as the tripped switch then stop
        //the wedge
        if (position == getSwitchState())
            wedgeMotor.set(0);
    }
    
    public void manualMove(MotorMode position) {
        if (position == MotorMode.UP)
            wedgeMotor.set(-defaultMotorSpeed);
        else if (position == MotorMode.DOWN)
            wedgeMotor.set(defaultMotorSpeed);
        else
            wedgeMotor.set(0);
    }
    
    
    public void runWedge(boolean yesno) {
        wedgeRunning = yesno;
        if (!yesno)
            wedgeMotor.set(0);
    }
    
    public MotorMode getSwitchState() {
        if (!topSwitch.get())
            return MotorMode.UP;
        if (!bottomSwitch.get())
            return MotorMode.DOWN;
        return MotorMode.NONE;
    }
}
