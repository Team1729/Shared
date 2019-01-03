package org.usfirst.frc.team1729.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Lifter extends Subsystem {
	
	private TalonSRX Lifter1 = new TalonSRX(10);
	private TalonSRX Lifter2 = new TalonSRX(14);
	private DigitalInput MaxDown = new DigitalInput(0);
	private DigitalInput MaxUp = new DigitalInput(1);



    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public boolean MaxUP(){
    	return MaxUp.get();
    }
    
    public boolean MaxDown(){
    	return MaxDown.get();
    }
    
    public void LifterMove(double speed){
    	
    	if(MaxDown.get() == true && speed < 0){
    		Lifter1.set(ControlMode.PercentOutput, 0);
    		Lifter2.set(ControlMode.PercentOutput, 0);
    	}
    	else if(MaxUp.get() == true && speed > 0){
    		Lifter1.set(ControlMode.PercentOutput, 0);
    		Lifter2.set(ControlMode.PercentOutput, 0);
    	}else{
    		Lifter1.set(ControlMode.PercentOutput, speed);
    		Lifter2.set(ControlMode.PercentOutput, speed);
    	}
		SmartDashboard.putBoolean("MaxDown", MaxDown.get());
		SmartDashboard.putBoolean("MaxUp", MaxUp.get());
		//SmartDashboard.putNumber("Lift", Lifter1.getMotorOutputVoltage());
    }
    
    public void LiftOverride(double speed){
    	Lifter1.set(ControlMode.PercentOutput, speed);
		Lifter2.set(ControlMode.PercentOutput, speed);	
    }
    
}

