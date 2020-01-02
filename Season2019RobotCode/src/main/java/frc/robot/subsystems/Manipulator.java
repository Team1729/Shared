/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.concurrent.TimeUnit;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

/**
 * Add your docs here.
 */
public class Manipulator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private TalonSRX Wheels = new TalonSRX(11);
  private TalonSRX Wrist = new TalonSRX(12);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public int WristEncoder(){
	  return Wrist.getSelectedSensorPosition(Constants.kPIDLoopIdx);
  }
  public void printEncoder(){
	SmartDashboard.putNumber("WristEncoder", Wrist.getSelectedSensorPosition(Constants.kPIDLoopIdx));
  }

  public void Manual(double speed){
	  Wrist.set(ControlMode.PercentOutput, speed);
  }

  public void WristInt(){
    /* Factory default hardware to prevent unexpected behavior */
		Wrist.configFactoryDefault();
		
		/* Configure Sensor Source for Pirmary PID */
		Wrist.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,
											Constants.kPIDLoopIdx, 
											Constants.kTimeoutMs);

		/**
		 * Configure Talon SRX Output and Sesnor direction accordingly
		 * Invert Motor to have green LEDs when driving Talon Forward / Requesting Postiive Output
		 * Phase sensor to have positive increment when driving Talon Forward (Green LED)
		 */
		Wrist.setSensorPhase(true);
		Wrist.setInverted(true);

		/* Set relevant frame periods to be at least as fast as periodic rate */
		Wrist.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);
		Wrist.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);

		/* Set the peak and nominal outputs */
		Wrist.configNominalOutputForward(0, Constants.kTimeoutMs);
		Wrist.configNominalOutputReverse(0, Constants.kTimeoutMs);
		Wrist.configPeakOutputForward(Constants.WristGains.kPeakOutput, Constants.kTimeoutMs);
		Wrist.configPeakOutputReverse(-Constants.WristGains.kPeakOutput, Constants.kTimeoutMs);

		/* Set Motion Magic gains in slot0 - see documentation */
		Wrist.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
		Wrist.config_kF(Constants.kSlotIdx, Constants.WristGains.kF, Constants.kTimeoutMs);
		Wrist.config_kP(Constants.kSlotIdx, Constants.WristGains.kP, Constants.kTimeoutMs);
		Wrist.config_kI(Constants.kSlotIdx, Constants.WristGains.kI, Constants.kTimeoutMs);
		Wrist.config_kD(Constants.kSlotIdx, Constants.WristGains.kD, Constants.kTimeoutMs);

		/* Set acceleration and vcruise velocity - see documentation */
		Wrist.configMotionCruiseVelocity(15000, Constants.kTimeoutMs);
		Wrist.configMotionAcceleration(6000, Constants.kTimeoutMs);

		/* Zero the sensor */
		Wrist.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
	}

  public void Wrist(double Setpoint){
    StringBuilder _sb = new StringBuilder();
		/* Get gamepad axis - forward stick is positive */

		/* Get current Talon SRX motor output */
		double motorOutput = Wrist.getMotorOutputPercent();

		/* Prepare line to print */
		_sb.append("\tOut%:");
		_sb.append(motorOutput);
		_sb.append("\tVel:");
		_sb.append(Wrist.getSelectedSensorVelocity(Constants.kPIDLoopIdx));

		/**
		 * Peform Motion Magic when Button 1 is held,
		 * else run Percent Output, which can be used to confirm hardware setup.
		 */
		
			/* Motion Magic */ 
			
			/*4096 ticks/rev * 10 Rotations in either direction */
			double targetPos = Setpoint;//leftYstick * 4096 * 10.0;
			Wrist.set(ControlMode.MotionMagic, targetPos);

			/* Append more signals to print when in speed mode */
			_sb.append("\terr:");
			_sb.append(Wrist.getClosedLoopError(Constants.kPIDLoopIdx));
			_sb.append("\ttrg:");
			_sb.append(targetPos);
		

		/* Instrumentation */
		//Instrum.Process(Wrist, _sb);///////////////////////////

		/* 10 Ms timeout, allow CAN Frames to process */
		try { TimeUnit.MILLISECONDS.sleep(10); } 	
		catch (Exception e) { /* Do Nothing */ }
  }
  
  public void Wheels(double speed){
    Wheels.set(ControlMode.PercentOutput, speed);
  }

  public String GamePiece(String Piece){
	  String p = Piece;
	  return p;
  }

}
