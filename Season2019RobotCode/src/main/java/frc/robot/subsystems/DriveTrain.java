package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Instrum;
import frc.robot.commands.Driver;

import java.util.concurrent.TimeUnit;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;



public class DriveTrain extends Subsystem {

	private TalonSRX FL = new TalonSRX(2); // test changed from 2
	private TalonSRX FR = new TalonSRX(3);
	private TalonSRX RR = new TalonSRX(4);
	private TalonSRX RL = new TalonSRX(5);
	



    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
       // setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Driver());
    }
    
 // LEFT MOTORS ON FEZZIK ARE REVERSED
    /**
     * @param Lspeed Left motor speed (-1 to 1)
     * @param Rspeed Right motor speed ( -1 to 1)
     */

    public void Move(double Lspeed, double Rspeed){
    	
    	FR.set(ControlMode.PercentOutput, -Rspeed);
    	RR.set(ControlMode.PercentOutput, -Rspeed);
		
		FL.set(ControlMode.PercentOutput, Lspeed);
    	RL.set(ControlMode.PercentOutput, Lspeed);

	}
	
	/**
	 * 
	 * @param ArcadeY , Y axis of driver joystick
	 * @param ArcadeX , X axis of driver joystick
	 */
	public void Arcade(double ArcadeY, double ArcadeX) {
		double V;
		double W;
		double L;
		double R;
		double RV = 1;
		double LV = 1;
		ArcadeY *= 100;
		ArcadeX *= -100;
		
		V = (100 - Math.abs(ArcadeX)) * (ArcadeY/100) + ArcadeY;
		W = (100 - Math.abs(ArcadeY)) * (ArcadeX/100) + ArcadeX;
		
		R = ((V + W) / 2) / 100;
		L = ((V - W) / 2) / 100;

		R *= RV;
		L *= LV;
		SmartDashboard.putNumber("Left Drive Motor", L);
		SmartDashboard.putNumber("Right Drive Motor", R);
		Move(L, R);
	}

			public double TrackSpeed(double Setpoint){
				StringBuilder _sb = new StringBuilder();
					/* Get gamepad axis - forward stick is positive */
			
					/* Get current Talon SRX motor output */
					double motorOutput = FL.getMotorOutputPercent();
			
					/* Prepare line to print */
					_sb.append("\tOut%:");
					_sb.append(motorOutput);
					_sb.append("\tVel:");
					_sb.append(FL.getSelectedSensorVelocity(Constants.kPIDLoopIdx));
			
					/**
					 * Peform Motion Magic when Button 1 is held,
					 * else run Percent Output, which can be used to confirm hardware setup.
					 */
					
						/* Motion Magic */ 
						
						/*4096 ticks/rev * 10 Rotations in either direction */
						double targetPos = Setpoint;//leftYstick * 4096 * 10.0;
						FL.set(ControlMode.MotionMagic, targetPos);
			
						/* Append more signals to print when in speed mode */
						_sb.append("\terr:");
						_sb.append(FL.getClosedLoopError(Constants.kPIDLoopIdx));
						_sb.append("\ttrg:");
						_sb.append(targetPos);
					
			
					/* Instrumentation */
					Instrum.Process(FL, _sb,"Track");
			
					/* 10 Ms timeout, allow CAN Frames to process */
					try { TimeUnit.MILLISECONDS.sleep(10); } 	
					catch (Exception e) { /* Do Nothing */ }

					return motorOutput;
				}
			
			
	public void Amps(){
		SmartDashboard.putNumber("Drive Amps", FR.getOutputCurrent());
	}

 }



