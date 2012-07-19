/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.commands.tests;

import com.team4element.commands.DriveStraight;
import com.team4element.commands.RotateChassisTo;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author sysadmin
 */
public class GyroChassisTest extends CommandGroup {
    
    public GyroChassisTest() {
        for (int i=1; i <= 4; i++) {
            addSequential(new DriveStraight(2)); //drive for 2 seconds
            addSequential(new RotateChassisTo(i*90)); //rotate another 90 degrees
        } //repeat this for a full rotation
    }
    
}
