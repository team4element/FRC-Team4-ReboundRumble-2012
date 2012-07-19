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
public class RunShooterAtSpeed extends CommandBase {
    public static double MAX_RPM = 1000;
    
    public RunShooterAtSpeed() {
        requires(turret);
    }

    protected void initialize() {
        turret.runShooter(true);
    }

    protected void execute() {
        turret.setSpeed(MAX_RPM * oi.getOpJoyThrottle());
        turret.setBackspin(oi.getRightJoyThrottle());
        turret.dumpShooterInfo();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        turret.setSpeed(0);
        turret.runShooter(false);
    }

    protected void interrupted() {
        end();
    }
    
}
