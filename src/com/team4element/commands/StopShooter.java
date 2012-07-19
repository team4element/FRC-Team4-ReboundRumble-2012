/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.commands;

/**
 *
 * @author CrazyHand
 */

//TODO: DELETE
public class StopShooter extends CommandBase {
    
    public StopShooter() {
        requires(turret);
    }

    protected void initialize() {
        turret.runShooter(false);
    }

    protected void execute() {
        turret.runShooter(false);
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
