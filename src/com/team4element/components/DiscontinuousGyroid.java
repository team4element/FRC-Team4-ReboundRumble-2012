/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.components;

import edu.wpi.first.wpilibj.smartdashboard.SendableGyro;

/*
 * 
 * @author Shawn
 * @author Joshua
 * @author Julian
 * @author Mitchell
 * 
 */
public class DiscontinuousGyroid extends SendableGyro {

    public DiscontinuousGyroid(int channel) {
        super(channel);
    }

    private double offset = 0;
    
    public double getAngle() {
        //editing just this should work because the "pidGet" function that is 
        //used by our PIDController just calls this function
        double angle = (super.getAngle() - offset) % 360;
        if (angle < 0)
            //It is important to note that the return value should be within the
            //range of 0-360 and not over. 360 - (-82) would return 442. When it
            //should be 360 + (-82) which would return 278
            return 360 + angle;
        else
            return angle;
        
        // return ((( angle % 360 ) + 360 ) % 360 ); // One line solution?
    }
}
