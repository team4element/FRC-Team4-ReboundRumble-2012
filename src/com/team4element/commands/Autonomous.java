/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author sysadmin
 */
public class Autonomous extends CommandGroup {

    public Autonomous() {
        //addSequential(new Wait(0));//This is to wait for other hybrid shooters
        addSequential(new AutonomousFire(206));
        addSequential(new AutonomousFire(206));
        //TODO: change 12
    }
}
