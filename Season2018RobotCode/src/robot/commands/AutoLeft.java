package org.usfirst.frc.team1729.robot.commands;

import org.usfirst.frc.team1729.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoLeft extends Command {

    public AutoLeft() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    double timer;
    double stage;
    double lock;
    double tp1;
    protected void initialize() {
    	timer = 0;
    	stage = 1;
		lock = 0;
		tp1 = 0;

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	String gameData = DriverStation.getInstance().getGameSpecificMessage();
    	double NavHeading = Robot.sensors.NavHeading();
    	double FRamps = Robot.drivetrain.FRamps();
    	double FLamps = Robot.drivetrain.FLamps();
    	double EncL = Robot.sensors.GetLeftEncoder();
		timer += 0.02;
		

    	if(gameData.charAt(0) == 'L'){
    		if(timer <= 3.2){
        		Robot.lifter.LifterMove(0.6);
        	}else{
        		Robot.lifter.LifterMove(0);
        	}
        	
        	if(timer <= 2){
        		Robot.wrist.WristMove(0.42);
        	}else{
        		Robot.wrist.WristMove(0);
        	}
        	
    		if(stage == 1){
    			Robot.drivetrain.Move(0.5, 0.5);
    			if(timer >= 4.8){
        			stage = 2;
        		}
    		}
    		
    		if(stage == 2){
    			Robot.drivetrain.Move(0.5, -0.5);
    			if(NavHeading > 85 && NavHeading < 180){
        			stage = 3;
    			}
    		}	
    		if(stage == 3){
    			if(lock == 0){
    				tp1 = timer;
    				lock = 1;
    			}
    			Robot.drivetrain.Move(0.3, 0.3);
    			if(timer - tp1 >= 0.5){
    				stage = 4;
    			}
    		}
    		
    		if(stage == 4){
    			if(FRamps > 6 || FLamps > 6){
    				Robot.drivetrain.Move(0, 0);
        			Robot.pneumatics.Release();
        			stage = 5;
        		}else{
        			Robot.drivetrain.Move(0.3, 0.3);
        		}
    		}
    	}
	
    	
    	else if(gameData.charAt(0) == 'R'){
    		if(timer < 5){
    	    	Robot.drivetrain.Move(0.3, 0.3);
    	    	}else{
    	    		Robot.drivetrain.Move(0, 0);
    	    	}
    	    	
		}
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
