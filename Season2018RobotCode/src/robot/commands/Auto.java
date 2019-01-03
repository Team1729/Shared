package org.usfirst.frc.team1729.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1729.robot.Robot;
import com.kauailabs.navx.frc.AHRS;

/**
 *
 */
public class Auto extends Command {
	public Auto() {
		// Use requires() here to declare subsystem dependencies
		
	}
	double Timer;
	double Point;
	double LastPos;
	boolean Abtn9 = Robot.oi.ArcadeBTN(9);

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Timer = 0;
		Point = 0;
		LastPos = 0;
	}
   
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Timer += 0.02;
		double Wait = 1.65; 
		//SmartDashboard.putNumber("Timer", Timer);
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		NetworkTable table = NetworkTable.getTable("limelight");
		boolean led = table.putNumber("ledMode", 1); // <--- 0 = on......... 1 = off
		boolean pipeline = table.putNumber("pipeline", 0);
		double VisX = table.getNumber("tx", 0);
		double Dis = table.getNumber("ta", 0);
		double target = table.getNumber("tv", 0);
        double SM = 60; //Smooth Move
       
        SmartDashboard.putNumber("Target#", target);
        
			if(target == 1){
				Robot.automove.VisionMove(VisX/SM, VisX/SM, Dis);
				if(VisX < 0){
	        		LastPos = 0.4;
	        	}
	        	else if(VisX > 0){
	        		LastPos = -0.4;
	        	}
			}
			if(target == 0){
				Robot.drivetrain.Move(LastPos, -LastPos);
			}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
