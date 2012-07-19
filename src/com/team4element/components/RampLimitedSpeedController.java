/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.components;

import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 * @author sysadmin
 */
public class RampLimitedSpeedController implements SpeedController {
    double currentSpeed = 0;
    double rampLimit;
    SpeedController controller;
    
    public RampLimitedSpeedController(SpeedController control, double rampLimit) {
        controller = control;
        this.rampLimit = rampLimit;
    }

    public double get() {
        return controller.get();
    }

    public void set(double speed, byte syncGroup) {
        set(speed);
    }

    public void set(double speed) {
        if (speed == 0)
            controller.set(0);
        if (Math.abs(get() - speed) > rampLimit) {
            if (speed > get())
                controller.set(get() + rampLimit);
            else
                controller.set(get() - rampLimit);
        } else {
            controller.set(speed);
        }
    }

    public void disable() {
        controller.disable();
    }

    public void pidWrite(double output) {
        set(output);
    }
    
}
