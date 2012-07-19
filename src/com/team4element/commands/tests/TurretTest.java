/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.commands.tests;

import com.team4element.RobotMap;
import com.team4element.commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author sysadmin
 */

public class TurretTest extends CommandBase {
    
    public TurretTest() {
        requires(turret);
    }

    protected void initialize() {
        turret.runTurret(true);
        
    }

    protected void execute() {
        /*if (oi.getButtonState(RobotMap.operatorJoystick, 4))
            turret.moveLeft();
        if (oi.getButtonState(RobotMap.operatorJoystick, 5))
            turret.moveRight();
        
        if (!turret.isNotInDeadband(turret.getAngle())) {
            if (turret.isInLowerDeadband(turret.getAngle()))
                turret.moveRight();
            if (turret.isInUpperDeadband(turret.getAngle()))
                turret.moveLeft();
        }*/
        SmartDashboard.putDouble("Turret Angle", turret.getAngle());
    }


    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        turret.runTurret(false);
    }

    protected void interrupted() {
        end();
    }
    
}
