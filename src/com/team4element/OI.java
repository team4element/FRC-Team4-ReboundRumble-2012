
package com.team4element;

import com.team4element.commands.AdvanceConveyer;
import com.team4element.commands.MoveBridgeManipulator;
import com.team4element.commands.ReverseConveyer;
import com.team4element.commands.ToggleBridgeManipulator;
import com.team4element.commands.tests.ResetConstants;
import com.team4element.commands.tests.RunShooterAtSpeed;
import com.team4element.commands.tests.ShowLightSensorStates;
import com.team4element.commands.tests.ShowWedgeState;
import com.team4element.subsystems.BridgeManipulator.MotorMode;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
    private final Joystick leftJoy;
    private final Joystick rightJoy;
    private final Joystick operatorJoy;
    // Process operator interface input here.
    public OI() {
        /* ****DEFAULT COMMANDS:****
         * 
         * BallConveyer: BallController
         * BridgeManipulator: [none]
         * Chassis: OperatorTankDrive
         * Turret: TurretTest
         */
        
        leftJoy = new Joystick(RobotMap.leftJoystick);
        rightJoy = new Joystick(RobotMap.rightJoystick);
        operatorJoy = new Joystick(RobotMap.operatorJoystick);
        
        //WEDGE
        /*getButton(RobotMap.rightJoystick, RobotMap.wedgeToggleButton)
                .whileHeld(new ToggleBridgeManipulator());
        getButton(RobotMap.rightJoystick, RobotMap.wedgeUpButton)
                .whileHeld(new MoveBridgeManipulator(
                        MotorMode.UP));*/
        
        //FIRE
        getButton(RobotMap.operatorJoystick, RobotMap.shootButton)
                .whileHeld(new RunShooterAtSpeed());
        getButton(RobotMap.operatorJoystick, 2)
                .whileHeld(new AdvanceConveyer());
        
        getButton(RobotMap.operatorJoystick, 4)
                .whileHeld(new ReverseConveyer());
        
        //ALIGN ROBOT
        /*getButton(RobotMap.operatorJoystick, RobotMap.alignButton)
                .whenPressed(new GyroCameraAlign());*/
        
        //DEBUG/DASHBOARD commands
        getButton(RobotMap.operatorJoystick, 11)
                .whenReleased(new ResetConstants());
        
        new ShowLightSensorStates().start();
        new ShowWedgeState().start();
    }
    
    public double getLeftJoyY() {
        return leftJoy.getY();
    }
    
    public double getRightJoyY() {
        return rightJoy.getY();
    }  
    
    public double getOpJoyThrottle() {
        return (-operatorJoy.getZ() + 1) / 2;
    }

    public JoystickButton getButton(int joyid, int button) {
        switch(joyid) {
            case RobotMap.leftJoystick:
                return new JoystickButton(leftJoy, button);
            case RobotMap.rightJoystick:
                return new JoystickButton(rightJoy, button);
            case RobotMap.operatorJoystick:
                return new JoystickButton(operatorJoy, button);
            default:
                return null;
        }
    }
    
    public boolean getButtonState(int joyid, int button) {
        return getButton(joyid, button).get();
    }
    
    public double getThrottle(int joyid) {
        switch(joyid) {
            case RobotMap.leftJoystick:
                return (leftJoy.getZ() + 1) / 2;
            case RobotMap.rightJoystick:
                return (rightJoy.getZ() + 1) / 2;
            case RobotMap.operatorJoystick:
                return (operatorJoy.getZ() + 1) / 2;
            default:
                return 0;
        }
    }

    public double getRightJoyThrottle() {
        return (-rightJoy.getZ() + 1) / 2;
    }
}

