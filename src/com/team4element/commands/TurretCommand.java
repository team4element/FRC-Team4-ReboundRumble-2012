/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.commands;

import com.team4element.RobotMap;
import com.team4element.subsystems.CameraSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author sysadmin
 */

public class TurretCommand extends CommandBase {
    
    public TurretCommand() {
        requires(turret);
    }

    protected void initialize() {
        turret.runTurret(true);
    }

    protected void execute() {
        if (oi.getButton(RobotMap.operatorJoystick, RobotMap.turretAuto).get())
                turret.setIsAuto(!turret.isAuto());
        if (!turret.isAuto()) {
            if (oi.getButtonState(RobotMap.operatorJoystick, RobotMap.turretLeft))
                turret.moveLeft();
            if (oi.getButtonState(RobotMap.operatorJoystick, RobotMap.turretRight))
                turret.moveRight();
        } else {
            if(turret.isNotInDeadband(CameraSubsystem.findRectangleAngularError()+turret.getAngle()))
                turret.setAngleRelative(CameraSubsystem.findRectangleAngularError());
            else {
                if (turret.isInLowerDeadband(CameraSubsystem.findRectangleAngularError()))
                    turret.setAngle(turret.TKmin + 1);
                else
                    turret.setAngle(turret.TKmax - 1);
            }
            turret.setIsAuto(!turret.isAuto());
        }
        SmartDashboard.putDouble("Turret Angle", turret.getAngle());
        SmartDashboard.putBoolean("Turret isAuto", turret.isAuto());
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
