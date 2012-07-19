/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.commands.tests;

import com.team4element.commands.CommandBase;
import edu.wpi.first.wpilibj.Preferences;

/**
 *
 * @author sysadmin
 */
public class ResetConstants extends CommandBase {
    
    public ResetConstants() {
        requires(turret);
    }

    protected void initialize() {
        //Since we're assuming we just made changes, save them first
        Preferences.getInstance().save();
        turret.resetConstants();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
