/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.commands;

//import com.team4element.subsystems.CameraSubsystem;

import com.team4element.subsystems.CameraSubsystem;
import edu.wpi.first.wpilibj.camera.AxisCamera.ResolutionT;


/**
 *
 * @author CrazyHand
 */

//TODO: DELETE
public class ShooterByDistance extends CommandBase{
    
    public ShooterByDistance(){
        
    }
    
    protected void initialize() {
        //turret.runShooter(true);
        //TODO undo^
        CameraSubsystem.setFPS(5);        
        CameraSubsystem.setSize(ResolutionT.k320x240);
    }

    protected void execute() {
        //turret.setDistanceWithCamera(CameraSubsystem.getVerticalPixels());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
