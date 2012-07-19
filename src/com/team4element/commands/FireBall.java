/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author CrazyHand
 */
public class FireBall extends CommandBase {
    private boolean canFire;
    private boolean isFiring = false;
    private boolean isDone = false;
    private double firingDistance = 0;
    
    public FireBall() {
        requires(turret);
        //requires(roller);
    }
    
    public FireBall(double distance) {
        this();
        firingDistance = distance;
    }

    protected void initialize() {
        isDone = false;
        canFire = roller.ballAtTop()/* && turret.hasTarget()*/;
        if (canFire) {
            turret.runShooter(true);
            turret.runTurret(false);
        }
    }

    private static final double MAX_RPM = 1500;
    protected void execute() {
        if (canFire && !isDone) {
            double wheelSpeed;
            if (firingDistance == 0)
                wheelSpeed = oi.getOpJoyThrottle() * MAX_RPM;
            else
                wheelSpeed = firingDistance;
            turret.setSpeed(wheelSpeed);
            if (turret.atSpeed() && !isFiring) {
                isFiring = true;
                roller.runTopConveyer(true);
            }
            if (isFiring && !roller.ballAtTop()) {
                roller.runTopConveyer(false);
                isDone = true;
            }
            turret.dumpShooterInfo();
        }
        String state = "NONE";
        if (canFire)
            state = "SPIN-UP";
        if (isFiring)
            state = "FIRING";
        if (isDone)
            state = "DONE";
        SmartDashboard.putString("FireBall State:", state);
        
    }

    protected boolean isFinished() {
        if (!canFire)
            return true;
        if (isDone)
            SmartDashboard.putString("FireBall State:", "FINISHED");
        return isDone;
    }

    protected void end() {
        roller.runTopConveyer(false);
        turret.runShooter(false);
        turret.runTurret(false);
    }

    protected void interrupted() {
        end();
    }
}
