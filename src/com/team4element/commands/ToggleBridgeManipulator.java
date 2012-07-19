/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.commands;

import com.team4element.subsystems.BridgeManipulator.MotorMode;

/**
 *
 * @author sysadmin
 */
public class ToggleBridgeManipulator extends CommandBase {
    private MotorMode oldPosition;
    
    public ToggleBridgeManipulator() {
        requires(wedge);
    }

    protected void initialize() {
        wedge.runWedge(true);
        oldPosition = wedge.getSwitchState();
    }

    protected void execute() {
        if (oldPosition == MotorMode.UP)
            wedge.moveWedge(MotorMode.DOWN);
        if (oldPosition == MotorMode.DOWN)
            wedge.moveWedge(MotorMode.UP);
    }

    protected boolean isFinished() {
        if (oldPosition == MotorMode.DOWN)
            return wedge.getSwitchState() == MotorMode.UP;
        else if (oldPosition == MotorMode.UP)
            return wedge.getSwitchState() == MotorMode.DOWN;
        return true;
    }

    protected void end() {
        wedge.moveWedge(MotorMode.NONE);
    }

    protected void interrupted() {
        end();
    }
    
}
