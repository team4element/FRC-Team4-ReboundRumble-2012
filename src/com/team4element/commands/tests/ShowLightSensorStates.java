/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.commands.tests;

import com.team4element.commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author sysadmin
 */
public class ShowLightSensorStates extends CommandBase {

    protected void initialize() {
    }

    protected void execute() {
        SmartDashboard.putBoolean("Top Light Sensor", roller.ballAtTop());
        SmartDashboard.putBoolean("Bottom Light Sensor", roller.ballAtBottom());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
