package org.usfirst.frc.team1729.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Wrist extends Subsystem {
	
	private TalonSRX Wrist = new TalonSRX(11);


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void WristMove(double speed){
		Wrist.set(ControlMode.PercentOutput, -speed);
		//SmartDashboard.putNumber("Wrist", Wrist.getMotorOutputVoltage());

		//SmartDashboard.putNumber("Wrist Amps", Wrist.getOutputCurrent());
    }
}

