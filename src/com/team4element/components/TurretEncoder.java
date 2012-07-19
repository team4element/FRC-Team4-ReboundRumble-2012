/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.components;

import com.team4element.commands.CommandBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

/**
 *
 * @author joshua.souza
 */
public class TurretEncoder extends Encoder {
    private int gearRatio = 1; // 1/gearRatio
    
    public TurretEncoder(DigitalInput aChannel, DigitalInput bChannel){
        super(aChannel, bChannel);
    }
    
    public double getAngle(){
        return (super.getDistance() * gearRatio) % 360;
    }
    
    public double getDistance(){
        return (getAngle() + CommandBase.chassis.getAngle()) % 360;
    }
}
