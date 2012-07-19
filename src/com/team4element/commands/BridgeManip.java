/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.commands;

import com.team4element.RobotMap;

/**
 *
 * @author justin.comins
 */
public class BridgeManip extends CommandBase {

    protected void initialize() {
    }

    protected void execute() {
            //BridgeManipSub.moveWedge(oi.getButton(RobotMap.operatorJoystick, RobotMap.bridgeButton).get());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
