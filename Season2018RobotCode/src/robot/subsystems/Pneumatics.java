package org.usfirst.frc.team1729.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team1729.robot.Robot;
import org.usfirst.frc.team1729.robot.commands.*;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Pneumatics extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	DoubleSolenoid Shifter = new DoubleSolenoid(0, 1);
	DoubleSolenoid Grabber = new DoubleSolenoid(2, 3);

	double Gear = 1; // 1 is low gear.... 2 is high gear
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new PneumaticsCommand());
	}
	
	public void HighGear(){
		Shifter.set(DoubleSolenoid.Value.kReverse);
		Gear = 2;
	}
	
	public void LowGear(){
		Shifter.set(DoubleSolenoid.Value.kForward);
		Gear = 1;
	}
	
	public void Grab(){
		Grabber.set(DoubleSolenoid.Value.kForward);
		Robot.boxhandler.SpinGrab(0.1);
	}
	
	public void Release(){
		Grabber.set(DoubleSolenoid.Value.kReverse);
	}	
}
