/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.commands;

import com.team4element.RobotMap;
import com.team4element.commands.CommandBase;

/**
 *
 * @author intern
 */
public class OperatorTankDrive extends CommandBase {
    
    public OperatorTankDrive() {
        requires(chassis);
    }

    protected void initialize() {
    }

    protected void execute() {
        if (oi.getButtonState(RobotMap.rightJoystick, 2))
            chassis.tankDrive(-oi.getLeftJoyY() * .25, -oi.getRightJoyY() *.25);
        else
            chassis.tankDrive(-oi.getLeftJoyY(), -oi.getRightJoyY());
    }

    protected boolean isFinished() {
        return false; //never finishes
    }

    protected void end() {
        
    }

    protected void interrupted() {
        end();
    }
    
}
