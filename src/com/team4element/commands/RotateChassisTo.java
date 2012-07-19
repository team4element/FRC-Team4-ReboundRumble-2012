/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.commands;

import com.team4element.subsystems.Chassis.ClosedLoopMode;

/**
 *
 * @author sysadmin
 */
public class RotateChassisTo extends CommandBase {
    double toAngle = 0;
    private ClosedLoopMode oldMode;
    
    public RotateChassisTo(double angle) {
        toAngle = angle;
        requires(chassis);
    }

    protected void initialize() {
        oldMode = chassis.getClosedLoopMode();
        chassis.setClosedLoopMode(ClosedLoopMode.GYRO);
        chassis.setAngleSetPoint(toAngle);
    }

    protected void execute() {
        chassis.setAngleSetPoint(toAngle);
    }

    protected boolean isFinished() {
        return chassis.atAngleSetPoint();
    }

    protected void end() {
        chassis.setClosedLoopMode(oldMode);
    }

    protected void interrupted() {
        end();
    }
    
}
