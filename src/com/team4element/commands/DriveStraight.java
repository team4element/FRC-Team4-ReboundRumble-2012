/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.commands;

import com.team4element.subsystems.Chassis;
import com.team4element.subsystems.Chassis.ClosedLoopMode;

/**
 *
 * @author Josh
 */
public class DriveStraight extends CommandBase {
    private ClosedLoopMode oldMode;
    private final double speed;
    private final double distance;
    
    public DriveStraight(double speed, double distance) {
        this.speed = speed;
        this.distance = distance;
        requires(chassis);
    }
    
    public DriveStraight(double distance) {
        this(-.5, distance);
    }
    
    public DriveStraight() {
        this(0);
    }

    protected void initialize() {
        oldMode = chassis.getClosedLoopMode();
        chassis.setClosedLoopMode(Chassis.ClosedLoopMode.STRAIGHT);
    }

    protected void execute() {
        chassis.setSpeed(-.5);
    }

    protected boolean isFinished() {
            return false;
    }

    protected void end() {
        chassis.setClosedLoopMode(oldMode);
    }

    protected void interrupted() {
        end();
    }
    
}
