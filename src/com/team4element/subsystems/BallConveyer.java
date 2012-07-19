/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.subsystems;

import com.team4element.RobotMap;
import com.team4element.commands.BallController;
import com.team4element.commands.tests.RunConveyer;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 * @author Josh
 * @author Justin
 * @author Shawn
 */
public class BallConveyer extends Subsystem {
    private Victor bottomConveyer;
    private Victor topConveyerMotor;
    private final Counter topCounter;
    private final Counter bottomCounter;
    private double bottomConveyerSpeed;
    private double topConveyerSpeed;
    private final DigitalInput topLightSensor;
    private final DigitalInput bottomLightSensor;
            
    public BallConveyer() {
         bottomConveyer = new Victor(RobotMap.ballRollerAndBottomConveyerMotor);
         bottomConveyerSpeed = Preferences.getInstance().getDouble("ballRollerAndBottomConveyerSpeed", .5);
         
         topConveyerMotor = new Victor(RobotMap.topConveyerMotor);
         topConveyerSpeed = Preferences.getInstance().getDouble("topConveyerSpeed", .25);
         
         //Counts all rising (passing) edge balls at ball roller
         bottomLightSensor = new DigitalInput(RobotMap.bottomLightSensor);
         bottomCounter = new Counter();
            bottomCounter.setUpSource(bottomLightSensor);
            bottomCounter.setUpSourceEdge(false, true);
            bottomCounter.start();
            
         //Counts balls at top of conveyer and shooter
         topLightSensor = new DigitalInput(RobotMap.topLightSensor);   
         topCounter = new Counter();
            topCounter.setUpSource(topLightSensor);
            topCounter.setUpSourceEdge(false, true);
            topCounter.start();
            
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public void initDefaultCommand() {
        //setDefaultCommand(new BallController());
        setDefaultCommand(new BallController());
    }
    
    public int getTotalBallCount() {
        //total count is bottom minus top
        //plus one if we have a ball sitting at the top
        return bottomCounter.get() - topCounter.get() + ((ballAtTop())?1:0);
    }
    
    public boolean ballAtTop() {
        //The light sensors are GND when activated
        return !topLightSensor.get();
    }
    
    public void runBottomConveyer(boolean yesno) {
        if (yesno) {
            if (!runReverse) 
                bottomConveyer.set(-bottomConveyerSpeed);
            else
                bottomConveyer.set(bottomConveyerSpeed);
        }
        else
            bottomConveyer.set(0);
    }
        
    public void runTopConveyer(boolean yesno) {
        if (yesno) {
            if (!runReverse)
                topConveyerMotor.set(topConveyerSpeed);
            else
                topConveyerMotor.set(-topConveyerSpeed);
        }
        else
            topConveyerMotor.set(0);
    }
    
    public void setSystemSpeed(double speed) {
        topConveyerSpeed = speed;
        bottomConveyerSpeed = speed;
    }

    public int getBottomCount() {
        return bottomCounter.get();
    }

    public int getTopCount() {
        return topCounter.get();
    }

    public boolean ballAtBottom() {
        return !bottomLightSensor.get();
    }

    private boolean runReverse = false;
    public void runReverse(boolean b) {
        runReverse = b;
    }
}
