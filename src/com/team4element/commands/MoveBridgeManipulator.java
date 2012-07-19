/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.commands;

import com.team4element.subsystems.BridgeManipulator.MotorMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author joshua.souza
 */
public class MoveBridgeManipulator extends CommandBase {
    
    private MotorMode motorDirection;
    
    public MoveBridgeManipulator(MotorMode direction) {
        requires(wedge);
        motorDirection = direction;
    }

    protected void initialize() {
        wedge.runWedge(true);
    }

    protected void execute() {
        wedge.moveWedge(motorDirection);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        wedge.runWedge(false);
    }

    protected void interrupted() {
        end();
    }
}
