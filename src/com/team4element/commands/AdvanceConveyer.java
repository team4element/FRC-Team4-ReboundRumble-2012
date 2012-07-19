/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.commands;

/**
 *
 * @author CrazyHand
 */
public class AdvanceConveyer extends CommandBase {
    private boolean noBall = true;
    private boolean isDone = false;
    
    
    public AdvanceConveyer() {
        requires(roller);
    }

    protected void initialize() {
    }

    protected void execute() {
        roller.runTopConveyer(true);
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
