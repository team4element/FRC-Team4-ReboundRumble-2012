/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.components;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author sysadmin
 */
public class SmoothedRateEncoder extends Encoder {
    
    double[] ringBuffer;
    int ringPosition = 0;
    private final Timer taskTimer;
    
    private class SmoothedRateTask extends TimerTask {
        private SmoothedRateEncoder encoder;
        
        public SmoothedRateTask(SmoothedRateEncoder enc) {
            encoder = enc;
        }
        
        public void run() {
            if (encoder != null)
                encoder.calculate();
        }
        
    }
    
    public SmoothedRateEncoder(DigitalInput a, DigitalInput b, int ringSize) {
        super(a,b, false, EncodingType.k1X);
        ringBuffer = new double[ringSize];
        taskTimer = new java.util.Timer();
        taskTimer.schedule(new SmoothedRateTask(this), 20, 20);
    }
    
    
    private void calculate() {
        //TODO: ringBuffer update
        if (ringPosition < ringBuffer.length)
            ringPosition = 0;
        ringBuffer[ringPosition] = super.getRate();
        ringPosition++;
    }
    
    public double getRate() {
        double result = 0;
        for(int i=0; i < ringBuffer.length; i++) {
            result += ringBuffer[i];
        }
        return result / ringBuffer.length;
    }
    
}
