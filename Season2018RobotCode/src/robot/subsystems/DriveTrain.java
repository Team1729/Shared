package org.usfirst.frc.team1729.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1729.robot.Robot;
import org.usfirst.frc.team1729.robot.commands.Drive;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.*;

public class DriveTrain extends Subsystem {

	private TalonSRX FL = new TalonSRX(2); 
	private TalonSRX FR = new TalonSRX(3);
	private TalonSRX RL = new TalonSRX(4);
	private TalonSRX RR = new TalonSRX(5);

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
       // setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Drive());
    }
    
 // LEFT MOTORS ON FEZZIK ARE REVERSED
    /**
     * @param Lspeed Left motor value ( + is forward)
     * @param Rspeed Right motor value ( + is forward)
     */
    public void Move(double Lspeed, double Rspeed){
    	switch(Robot.ThisRobot){
    		case 1:
    			// Prototype
    			Lspeed *= 1; 
            	Rspeed *= -1;
            	break;
    		case 2:
    			// Fezzik
    			Lspeed *= -1; 
            	Rspeed *= 1;
            	break;
            default:
            	// Unknown, or new bot
            	Lspeed *= 1;
            	Rspeed *= 1;
            	break;
    	}
    	
    	FR.set(ControlMode.PercentOutput, Rspeed);
    	RR.set(ControlMode.PercentOutput, Rspeed);
    	
    	RL.set(ControlMode.PercentOutput, Lspeed);
    	FL.set(ControlMode.PercentOutput, Lspeed);
    	
    	//SmartDashboard.putNumber("FR amps", FR.getOutputCurrent());
    	//SmartDashboard.putNumber("FL amps", FL.getOutputCurrent());
    	//SmartDashboard.putNumber("L speed", Lspeed);
    	//SmartDashboard.putNumber("R speed", Rspeed);


    }
    
    public double FRamps(){
    	return FR.getOutputCurrent();
    }
    public double FLamps(){
    	return FL.getOutputCurrent();
    }
    
	public void Arcade(double ArcadeY, double ArcadeX) {
		double V;
		double W;
		double L;
		double R;
		double RV = 0.8;
		double LV = 1;
		ArcadeY *= 100;
		ArcadeX *= -100;
		
		V = (100 - Math.abs(ArcadeX)) * (ArcadeY/100) + ArcadeY;
		W = (100 - Math.abs(ArcadeY)) * (ArcadeX/100) + ArcadeX;
		
		R = ((V + W) / 2) / 100;
		L = ((V - W) / 2) / 100;

		R *= RV;
		L *= LV;
		
		Move(L, R);
	}

 }



