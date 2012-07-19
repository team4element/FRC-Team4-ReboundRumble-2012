package com.team4element.commands;

import com.team4element.subsystems.BallConveyer;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.team4element.OI;
import com.team4element.subsystems.*;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author Author
 */
public abstract class CommandBase extends Command {

    public static OI oi;
    // Create a single static instance of all of your subsystems
    public static Chassis chassis = new Chassis();
    
    public static BridgeManipulator wedge = new BridgeManipulator();
    
    public static BallConveyer roller = new BallConveyer();
    public static Turret turret = new Turret();
    public static Preferences pref = Preferences.getInstance();
    
    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();

        // Show what command your subsystem is running on the SmartDashboard
        SmartDashboard.putData("SchedulerData", Scheduler.getInstance());
        SmartDashboard.putData(chassis);
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }

  
}
