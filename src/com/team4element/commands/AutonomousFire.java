/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.commands;

/**
 *
 * @author sysadmin
 */
public class AutonomousFire extends CommandBase {
    private boolean haveBall;
    private boolean isFiring = false;
    private boolean isDone = false;
    private final double firingSpeed;
    
    public AutonomousFire(double speed) {
        requires(roller);
        requires(turret);
        firingSpeed = speed;
    }

    protected void initialize() {
        haveBall = roller.ballAtTop();
        turret.runShooter(true);
        turret.setSpeed(firingSpeed);
    }

    protected void execute() {
        if (!haveBall) {
            roller.runTopConveyer(true);
            roller.runBottomConveyer(true);
            haveBall = roller.ballAtTop();
        } else {
            if (!isFiring) {
                roller.runTopConveyer(false);
                roller.runBottomConveyer(false);
            }
        }
        
        if (turret.atSpeed() && haveBall) {
            isFiring = true;
            roller.runTopConveyer(true);
            if (!roller.ballAtTop())
                isDone = true;
        }
            
    }

    protected boolean isFinished() {
        return isDone;
    }

    protected void end() {
        turret.runShooter(false);
        roller.runTopConveyer(false);
        roller.runBottomConveyer(false);
    }

    protected void interrupted() {
        end();
    }
    
}
