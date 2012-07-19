/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.commands.tests;

import com.team4element.commands.CommandBase;
import com.team4element.subsystems.CameraSubsystem;
import com.team4element.subsystems.CameraSubsystem.HoopRectangle;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.RGBImage;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author JULIAN
 * @author JUSTIN
 */
public class TestCameraRectangle extends CommandBase {
    RGBImage image = null;
    final int numImages = 2;
    boolean run = false;
    
    public TestCameraRectangle(String filename) {
        this();
        try {
            image = new RGBImage(filename);
        } catch (NIVisionException ex) {
            ex.printStackTrace();
            image = null;
        }
    }
    
    public TestCameraRectangle() {
    }

    protected void initialize() {
    }

    protected void execute() {
        HoopRectangle rectangle = null;
        if (image == null) {
            if (CameraSubsystem.newImage()) {
                rectangle = CameraSubsystem.getTopRectangle();
            } 
        } else {
            //nothing
            run = true;
        }
        if (rectangle != null) {
            SmartDashboard.putDouble("Hoop Top", rectangle.coy);
            SmartDashboard.putDouble("Hoop Right", rectangle.cox);
            SmartDashboard.putDouble("Hoop Width", rectangle.w);
            SmartDashboard.putDouble("Hoop Height", rectangle.h);
            SmartDashboard.putDouble("Hoop Score", rectangle.score);
            SmartDashboard.putBoolean("Have Hoop", true);
        } else {
            SmartDashboard.putBoolean("Have Hoop", false);
        }
        
    }

    protected boolean isFinished() {
        if (image == null)
            return false;
        else
            return run;
    }

    protected void end() {
    }

    protected void interrupted() {
        end();
    }
    
}
