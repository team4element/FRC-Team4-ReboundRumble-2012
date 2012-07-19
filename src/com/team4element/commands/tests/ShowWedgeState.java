/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.commands.tests;

import com.team4element.commands.CommandBase;
import com.team4element.subsystems.BridgeManipulator.MotorMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author sysadmin
 */
public class ShowWedgeState extends CommandBase {

    protected void initialize() {
    }

    protected void execute() {
        MotorMode state = wedge.getSwitchState();
        String value = "";
        if (state.value == MotorMode.UP.value)
            value = "UP";
        if (state.value == MotorMode.DOWN.value)
            value = "DOWN";
        if (state.value == MotorMode.NONE.value)
            value = "NONE";
        
        SmartDashboard.putString("Switch State", value);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
