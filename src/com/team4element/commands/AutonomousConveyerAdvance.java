/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.commands;

/**
 *
 * @author sysadmin
 */
public class AutonomousConveyerAdvance extends CommandBase {
    private boolean isDone = false;
    
    public AutonomousConveyerAdvance() {
        requires(roller);
    }
    
    protected void initialize() {
        isDone = roller.ballAtTop();
    }

    protected void execute() {
        if (!isDone) {
            roller.runTopConveyer(true);
            roller.runBottomConveyer(true);
            if (roller.ballAtTop())
                isDone = roller.ballAtTop();
        }
    }

    protected boolean isFinished() {
        return isDone;
    }

    protected void end() {
        roller.runTopConveyer(false);
        roller.runBottomConveyer(false);
    }

    protected void interrupted() {
        end();
    }
    
}
