/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author sysadmin
 */
public class Wait extends CommandBase {
    private final Timer timer;
    private final double seconds;
    
    public Wait(double seconds) {
        this.seconds = seconds;
        timer = new Timer();
    }

    protected void initialize() {
        timer.start();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return timer.get() > (seconds * 100000);
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
