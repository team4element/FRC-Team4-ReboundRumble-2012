/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.commands.tests;

import com.team4element.commands.CommandBase;
import com.team4element.subsystems.CameraSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author justin.comins
 */
public class CameraTest extends CommandBase {

    protected void initialize() {
    }

    protected void execute() {
        CameraSubsystem.HoopRectangle rectangle = CameraSubsystem.getTopRectangle();
        if (rectangle != null)
            SmartDashboard.putDouble("Normalized Hoop Height", rectangle.h / rectangle.ih);
    }

    protected boolean isFinished() {
        return false;//doesn't finish
    }

    protected void end() {
        //doesn't end
    }

    protected void interrupted() {
    }
    
}
