/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.commands.tests;

import com.team4element.commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 29 and 3/4
 * @author sysadmin
 */
public class ShowLightSensorCounts extends CommandBase {

    protected void initialize() {
    }

    protected void execute() {
        SmartDashboard.putInt("Total Ball Count", roller.getTotalBallCount()); 
       /* SmartDashboard.putInt("Bottom Light Count", roller.getBottomCount());
        SmartDashboard.putInt("Top Light Count", roller.getTopCount());*/
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
