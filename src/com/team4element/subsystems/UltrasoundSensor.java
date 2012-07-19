package com.team4element.subsystems;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.SensorBase;

public class UltrasoundSensor extends SensorBase {

    private int sensorAngle = 10;
    private double voltageSupply = 5.0;
    private double voltSteps = 512;
    private double voltageScaling;
    AnalogChannel channel;
    //constructor
    public UltrasoundSensor(int _channel) {
        channel = new AnalogChannel(_channel);
        //default values
    }
    
    
    //constructor
    public UltrasoundSensor(int _channel, double voltageSteps) {
        channel = new AnalogChannel(_channel);
        voltSteps = voltageSteps;
        voltageScaling = voltageSupply / voltSteps;
    }
    
    // Just get the voltage.
    double GetVoltage() {
        return channel.getVoltage();
    }
    /* GetRangeInInches
     * Returns the range in inches
     * Returns -1.0 if units are not being used
     * Returns -2.0 if the voltage is below the minimum voltage
     */

   public double GetRangeInInches() {
        return GetVoltage() * Math.cos(sensorAngle)/ voltageScaling;
        //cos(angle) = adjacent/hypotenuse
   }
    
}
