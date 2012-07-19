/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.commands;

import com.team4element.RobotMap;

/**
 *
 * @author justin.comins
 */

//TODO: DELETE
public class BallController extends CommandBase {
    private static final int MAX_BALLS = 3;
    
    public BallController() {
        requires(roller);
    }

    protected void initialize() {
    }

    protected void execute() {
        if (oi.getButtonState(RobotMap.rollerJoystick, RobotMap.rollerButton)) {
            if (!roller.ballAtTop())
                roller.runTopConveyer(true);
            else
                roller.runTopConveyer(false);
            
            roller.runBottomConveyer(true);
        } else {
            roller.runTopConveyer(false);
            roller.runBottomConveyer(false);
        }
    }

    protected boolean isFinished() {
        return false; //never finishes
    }

    protected void end() {
        
    }

    protected void interrupted() {
        end();
    }
}
