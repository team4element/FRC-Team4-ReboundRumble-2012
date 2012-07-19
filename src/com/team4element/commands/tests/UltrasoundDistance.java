package com.team4element.commands.tests;
import com.team4element.commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Mitchell
 */
public class UltrasoundDistance extends CommandBase {
    
    public UltrasoundDistance(){
        requires(turret);
    }
    protected void initialize(){
    
    }

    protected void execute() {
        //periodic
        //.getDistanceToTarget has an arg of either "inch", "cm", or "feet" to
        //denote the conversion factor
        SmartDashboard.putDouble("Ultrasonic Sensor: ", turret.GetRangeInFeet());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        //
    }

    protected void interrupted() {
        end();
    }
}
