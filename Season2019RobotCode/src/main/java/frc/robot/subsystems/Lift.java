/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Instrum;

import java.util.concurrent.TimeUnit;
/**
 * Add your docs here.
 */
public class Lift extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private TalonSRX Lift = new TalonSRX(6);// test changed from 6
  private TalonSRX Lift2 = new TalonSRX(7);
  private TalonSRX Lift3 = new TalonSRX(8);
  private TalonSRX Lift4 = new TalonSRX(9); 
  private TalonSRX Hab2 = new TalonSRX(10);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
	// setDefaultCommand(new MySpecialCommand());
	//setDefaultCommand(new Operator());
  }

  public void resetLiftEnc(){
	Lift4.setSelectedSensorPosition(0);
  }

  public int LiftEncoder(){
	  return Lift4.getSelectedSensorPosition(Constants.kPIDLoopIdx);
  }

  public int Hab2Encoder(){
	  return Hab2.getSelectedSensorPosition(Constants.kPIDLoopIdx);
  }
  
  public void printEncoders(){
	SmartDashboard.putNumber("LiftEncoder", Lift4.getSelectedSensorPosition(Constants.kPIDLoopIdx));
	SmartDashboard.putNumber("Hab2Encoder", Hab2.getSelectedSensorPosition(Constants.kPIDLoopIdx));
  }

  public void Manual(double speed){
	Lift4.set(ControlMode.PercentOutput, speed);
	Lift2.set(ControlMode.PercentOutput, speed);
	Lift3.set(ControlMode.PercentOutput, speed);
	Lift.set(ControlMode.PercentOutput, speed);
  }

  public void ManualHab2(double speed){
	  Hab2.set(ControlMode.PercentOutput, speed);
  }

  public void LiftInt(){
    /* Factory default hardware to prevent unexpected behavior */
		Lift4.configFactoryDefault();
		
		/* Configure Sensor Source for Pirmary PID */
		Lift4.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,
											Constants.kPIDLoopIdx, 
											Constants.kTimeoutMs);

		/**
		 * Configure Talon SRX Output and Sesnor direction accordingly
		 * Invert Motor to have green LEDs when driving Talon Forward / Requesting Postiive Output
		 * Phase sensor to have positive increment when driving Talon Forward (Green LED)
		 */
		Lift4.setSensorPhase(true);
		Lift4.setInverted(false); // <- sets robot depression
		/* Set relevant frame periods to be at least as fast as periodic rate */
		Lift4.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);
		Lift4.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);

		/* Set the peak and nominal outputs */
		Lift4.configNominalOutputForward(0, Constants.kTimeoutMs);
		Lift4.configNominalOutputReverse(0, Constants.kTimeoutMs);
		Lift4.configPeakOutputForward(Constants.LiftGains.kPeakOutput, Constants.kTimeoutMs);
		Lift4.configPeakOutputReverse(-Constants.LiftGains.kPeakOutput, Constants.kTimeoutMs);

		/* Set Motion Magic gains in slot0 - see documentation */
		Lift4.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
		Lift4.config_kF(Constants.kSlotIdx, Constants.LiftGains.kF, Constants.kTimeoutMs);
		Lift4.config_kP(Constants.kSlotIdx, Constants.LiftGains.kP, Constants.kTimeoutMs);
		Lift4.config_kI(Constants.kSlotIdx, Constants.LiftGains.kI, Constants.kTimeoutMs);
		Lift4.config_kD(Constants.kSlotIdx, Constants.LiftGains.kD, Constants.kTimeoutMs);

		/* Set acceleration and vcruise velocity - see documentation */
		Lift4.configMotionCruiseVelocity(15000, Constants.kTimeoutMs);
		Lift4.configMotionAcceleration(6000, Constants.kTimeoutMs);

		/* Zero the sensor */
		Lift4.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
	}

	public void HabLiftInthvhb(){
		/* Factory default hardware to prevent unexpected behavior */
			Lift4.configFactoryDefault();
			
			/* Configure Sensor Source for Pirmary PID */
			Lift4.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,
												Constants.kPIDLoopIdx, 
												Constants.kTimeoutMs);
	
			/**
			 * Configure Talon SRX Output and Sesnor direction accordingly
			 * Invert Motor to have green LEDs when driving Talon Forward / Requesting Postiive Output
			 * Phase sensor to hatruerement when driving Talon F
			 * 
			 * orward (Green LED)
			 */
			Lift4.setSensorPhase(false);
			Lift4.setInverted(true);
	
			/* Set relevant frame periods to be at least as fast as periodic rate */
			Lift4.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);
			Lift4.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);
	
			/* Set the peak and nominal outputs */
			Lift4.configNominalOutputForward(0, Constants.kTimeoutMs);
			Lift4.configNominalOutputReverse(0, Constants.kTimeoutMs);
			Lift4.configPeakOutputForward(Constants.HabGains.kPeakOutput, Constants.kTimeoutMs);
			Lift4.configPeakOutputReverse(-Constants.HabGains.kPeakOutput, Constants.kTimeoutMs);
	
			/* Set Motion Magic gains in slot0 - see documentation */
			Lift4.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
			Lift4.config_kF(Constants.kSlotIdx, Constants.HabGains.kF, Constants.kTimeoutMs);
			Lift4.config_kP(Constants.kSlotIdx, Constants.HabGains.kP, Constants.kTimeoutMs);
			Lift4.config_kI(Constants.kSlotIdx, Constants.HabGains.kI, Constants.kTimeoutMs);
			Lift4.config_kD(Constants.kSlotIdx, Constants.HabGains.kD, Constants.kTimeoutMs);
	
			/* Set acceleration and vcruise velocity - see documentation */
			Lift4.configMotionCruiseVelocity(15000, Constants.kTimeoutMs);
			Lift4.configMotionAcceleration(6000, Constants.kTimeoutMs);
	
			/* Zero the sensor */
			Lift4.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
		}

	public void Hab2Int(){
		/* Factory default hardware to prevent unexpected behavior */
			Hab2.configFactoryDefault();
			
			/* Configure Sensor Source for Pirmary PID */
			Hab2.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,
												Constants.kPIDLoopIdx, 
												Constants.kTimeoutMs);
	
			/**
			 * Configure Talon SRX Output and Sesnor direction accordingly
			 * Invert Motor to have green LEDs when driving Talon Forward / Requesting Postiive Output
			 * Phase sensor to have positive increment when driving Talon Forward (Green LED)
			 */
			Hab2.setSensorPhase(true); //Used for inverting the direction of motor - RW
			Hab2.setInverted(false);
	
			/* Set relevant frame periods to be at least as fast as periodic rate */
			Hab2.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);
			Hab2.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);
	
			/* Set the peak and nominal outputs */
			Hab2.configNominalOutputForward(0, Constants.kTimeoutMs);
			Hab2.configNominalOutputReverse(0, Constants.kTimeoutMs);
			Hab2.configPeakOutputForward(Constants.HabLegGains.kPeakOutput, Constants.kTimeoutMs);
			Hab2.configPeakOutputReverse(-Constants.HabLegGains.kPeakOutput, Constants.kTimeoutMs);
	
			/* Set Motion Magic gains in slot0 - see documentation */
			Hab2.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
			Hab2.config_kF(Constants.kSlotIdx, Constants.HabLegGains.kF, Constants.kTimeoutMs);
			Hab2.config_kP(Constants.kSlotIdx, Constants.HabLegGains.kP, Constants.kTimeoutMs);
			Hab2.config_kI(Constants.kSlotIdx, Constants.HabLegGains.kI, Constants.kTimeoutMs);
			Hab2.config_kD(Constants.kSlotIdx, Constants.HabLegGains.kD, Constants.kTimeoutMs);
	
			/* Set acceleration and vcruise velocity - see documentation */
			Hab2.configMotionCruiseVelocity(15000, Constants.kTimeoutMs);
			Hab2.configMotionAcceleration(6000, Constants.kTimeoutMs);
	
			/* Zero the sensor */
			Hab2.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
		} 
  

  public void Lifter(double Setpoint){
    StringBuilder _sb = new StringBuilder();
		/* Get gamepad axis - forward stick is positive */

		/* Get current Talon SRX motor output */
		double motorOutput = Lift4.getMotorOutputPercent();

		/* Prepare line to print */
		_sb.append("\tOut%:");
		_sb.append(motorOutput);
		_sb.append("\tVel:");
		_sb.append(Lift4.getSelectedSensorVelocity(Constants.kPIDLoopIdx));

		/**
		 * Peform Motion Magic when Button 1 is held,
		 * else run Percent Output, which can be used to confirm hardware setup.
		 */
		
			/* Motion Magic */ 
			
			/*4096 ticks/rev * 10 Rotations in either direction */
			double targetPos = Setpoint;//leftYstick * 4096 * 10.0;
			Lift4.set(ControlMode.MotionMagic, targetPos);
			Lift2.follow(Lift4);
			Lift3.follow(Lift4);
			Lift.follow(Lift4);
			
			/* Append more signals to print when in speed mode */
			_sb.append("\terr:");
			_sb.append(Lift4.getClosedLoopError(Constants.kPIDLoopIdx));
			_sb.append("\ttrg:");
			_sb.append(targetPos);
		

		/* Instrumentation */
		Instrum.Process(Lift4, _sb,"Lift");

		/* 10 Ms timeout, allow CAN Frames to process */
		//try { TimeUnit.MILLISECONDS.sleep(10); } 	
		//catch (Exception e) { /* Do Nothing */ }                                                                                                                                                                                                        // congrats!!!
	}

	public void Hab2Lift(double Setpoint){
		StringBuilder _sb = new StringBuilder();
			/* Get gamepad axis - forward stick is positive */
	
			/* Get current Talon SRX motor output */
			double motorOutput = Hab2.getMotorOutputPercent();
	
			/* Prepare line to print */
			_sb.append("\tOut%:");
			_sb.append(motorOutput);
			_sb.append("\tVel:");
			_sb.append(Hab2.getSelectedSensorVelocity(Constants.kPIDLoopIdx));
	
			/**
			 * Peform Motion Magic when Button 1 is held,
			 * else run Percent Output, which can be used to confirm hardware setup.
			 */
			
				/* Motion Magic */ 
				
				/*4096 ticks/rev * 10 Rotations in either direction */
				double targetPos = Setpoint;//leftYstick * 4096 * 10.0;
				Hab2.set(ControlMode.MotionMagic, targetPos);
	
				/* Append more signals to print when in speed mode */
				_sb.append("\terr:");
				_sb.append(Hab2.getClosedLoopError(Constants.kPIDLoopIdx));
				_sb.append("\ttrg:");
				_sb.append(targetPos);
			
	
			/* Instrumentation */
			Instrum.Process(Hab2, _sb,"Hab2");///////////////////////////////
	
			/* 10 Ms timeout, allow CAN Frames to process */
			try { TimeUnit.MILLISECONDS.sleep(10); } 	
			catch (Exception e) { /* Do Nothing */ }
		}
  
	public double LiftValue(){
		return Lift4.getSelectedSensorPosition(Constants.kPIDLoopIdx);
	}

	public double Hab2Value(){
		return Hab2.getSelectedSensorPosition(Constants.kPIDLoopIdx);
	}

	public double Amps(){
		double a = Lift4.getOutputCurrent();
		SmartDashboard.putNumber("Lift power", a);
		return a;
	}



}





