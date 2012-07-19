

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.commands.tests;

import com.team4element.RobotMap;
import com.team4element.commands.CommandBase;

/**
 *
 * @author sysadmin
 */
public class RunConveyer extends CommandBase {
    
    public RunConveyer() {
        requires(roller);
    }

    protected void initialize() {
    }

    protected void execute() {
        //roller.runTopConveyer(oi.getButtonState(RobotMap.leftJoystick, 1));
        //roller.runBottomConveyer(oi.getButtonState(RobotMap.rightJoystick, 1));
        //roller.runBallRoller(oi.getButtonState(RobotMap.operatorJoystick, 1));
        
        roller.runTopConveyer(oi.getButton(RobotMap.rightJoystick, 1).get());
        roller.runBottomConveyer(oi.getButton(RobotMap.rightJoystick, 1).get());
        roller.setSystemSpeed(1);

    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
