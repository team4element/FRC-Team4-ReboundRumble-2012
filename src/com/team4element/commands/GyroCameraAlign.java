/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.commands;

import com.team4element.subsystems.CameraSubsystem;
import com.team4element.subsystems.Chassis.ClosedLoopMode;

/**
 *
 * @author Josh
 */

public class GyroCameraAlign extends CommandBase {
    private ClosedLoopMode oldmode;
    private double angularError = 0;

    public GyroCameraAlign() {
        requires(chassis);
    }
    
    protected void initialize() {
        oldmode = chassis.getClosedLoopMode();
        chassis.setClosedLoopMode(ClosedLoopMode.CAMERA);
    }

    protected void execute() {
        if (angularError == 0) {
            double tempError = CameraSubsystem.findRectangleAngularError();
            if (tempError != 0)
                angularError = tempError;
            chassis.setAngleSetPointRelative(-angularError);
            end();
        }
    }

    protected boolean isFinished() {
        return false;//it gets interrupted when the button is released
    }

    protected void end() {
        chassis.setClosedLoopMode(oldmode);
    }

    protected void interrupted() {
        end();
    }
    
}
