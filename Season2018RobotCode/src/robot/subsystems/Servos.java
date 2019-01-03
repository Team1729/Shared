package org.usfirst.frc.team1729.robot.subsystems;

import org.usfirst.frc.team1729.robot.commands.*;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Servos extends Subsystem {
	
	private Servo Rshifter = new Servo(0);
	private Servo Lshifter = new Servo(1);

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ServosCommand());
    }
    
    public void HighGear(){
    	Rshifter.setAngle(123);
    	Lshifter.setAngle(55);
    }
    
    public void LowGear(){
    	Rshifter.setAngle(54);
    	Lshifter.setAngle(124);
    }
}

