package org.usfirst.frc.team1729.robot.subsystems;

import org.usfirst.frc.team1729.robot.commands.BoxCommand;
import org.usfirst.frc.team1729.robot.commands.Drive;


import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BoxHandler extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private TalonSRX IntakeL = new TalonSRX(12);
	private TalonSRX IntakeR = new TalonSRX(13);
	


    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new BoxCommand());
    }
    
    public void SpinGrab(double speed){
		IntakeR.set(ControlMode.PercentOutput, -speed);
		IntakeL.set(ControlMode.PercentOutput, speed);
    }
    
    
}

