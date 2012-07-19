/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.commands.tests;

import com.team4element.RobotMap;
import com.team4element.commands.CommandBase;
import com.team4element.subsystems.CameraSubsystem;

/**
 *
 * @author Justin
 */
public class TurretWithCamera extends CommandBase {
    private double lastImage = -1;
    
    public TurretWithCamera() {
        requires(turret);
    }

    protected void initialize() {
    }

    protected void execute() {
        if (lastImage != CameraSubsystem.getLastImageTimestamp()) {
            //new image data to work with
            turret.setAngleRelative(CameraSubsystem.findRectangleAngularError());
            lastImage = CameraSubsystem.getLastImageTimestamp();
        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
