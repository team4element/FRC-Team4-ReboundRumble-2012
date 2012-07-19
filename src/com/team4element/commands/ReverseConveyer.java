/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.commands;

/**
 *
 * @author sysadmin
 */
public class ReverseConveyer extends CommandBase {

    protected void initialize() {
        roller.runReverse(true);
    }

    protected void execute() {
        roller.runTopConveyer(true);
        roller.runBottomConveyer(true);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        roller.runTopConveyer(false);
        roller.runBottomConveyer(false);
        roller.runReverse(false);
    }

    protected void interrupted() {
        end();
    }
    
}
