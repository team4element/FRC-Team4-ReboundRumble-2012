/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.commands.tests;

import com.team4element.RobotMap;
import com.team4element.commands.CommandBase;
import com.team4element.subsystems.BridgeManipulator.MotorMode;

/**
 *
 * @author sysadmin
 */
public class ManualBridgeControl extends CommandBase {
    
    public ManualBridgeControl() {
        requires(wedge);
    }

    protected void initialize() {
    }

    protected void execute() {
        if (oi.getButtonState(RobotMap.rightJoystick, RobotMap.wedgeManualUp)) {
            if (wedge.getSwitchState() != MotorMode.UP)
                wedge.manualMove(MotorMode.UP);
            else
                wedge.manualMove(MotorMode.NONE);
        } else if (oi.getButtonState(RobotMap.rightJoystick, RobotMap.wedgeManualDown))
            wedge.manualMove(MotorMode.DOWN);
        else
            wedge.manualMove(MotorMode.NONE);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
