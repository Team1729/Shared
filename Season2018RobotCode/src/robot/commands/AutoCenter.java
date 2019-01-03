package org.usfirst.frc.team1729.robot.commands;

import org.usfirst.frc.team1729.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class AutoCenter extends Command {
    public AutoCenter() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    double timer;
    double stage;
    double tp1;
    double lock;
    double done;
    double start;
    double peak;
 
    // Called just before this Command runs the first time
    protected void initialize() {
    	timer = 0;
    	stage = 1;
    	tp1 = 0;
    	lock = 0;
    	done = 0;
    	start = 0;
    	peak = 0;
    	switch(Robot.ThisRobot){
		case 1:
			Robot.pneumatics.LowGear();
			Robot.pneumatics.Grab();
			break;
		case 2:
			Robot.servos.LowGear();
			break;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	String gameData = DriverStation.getInstance().getGameSpecificMessage();
    	double NavHeading = Robot.sensors.NavHeading();
    	timer += 0.02;
    	double GyroAngle = 200;
    	double speed = 0.3;
    	double wait = 1;
    	double turnspeed = 0.55;
    	double FRamps = Robot.drivetrain.FRamps();
    	double FLamps = Robot.drivetrain.FLamps();
    	
    	SmartDashboard.putNumber("NavX", NavHeading);
    	SmartDashboard.putNumber("Stage", stage);

    	if(timer <= 3.5){
    		Robot.lifter.LifterMove(0.4);
    	}else{
    		Robot.lifter.LifterMove(0);
    	}
    	
    	if(timer <= 2){
    		Robot.wrist.WristMove(0.4);
    	}else{
    		Robot.wrist.WristMove(0);
    	}
    	
    	if(timer <= 1.2){
    		Robot.drivetrain.Move(0.3, 0.3);
    	}else{
    		start = 1;
    	}
    	
    	
    	if(gameData.charAt(0) == 'R' && start == 1){
    		
    		if(stage == 1){
        		Robot.drivetrain.Move(turnspeed, -turnspeed);
        		if(NavHeading >= 45 && NavHeading < 180){
        			stage = 2;
        		}
    		}
    		
    		if(stage == 2){
    			if(lock == 0){
    				tp1 = timer;
    				lock = 1;
    			}
    			Robot.drivetrain.Move(speed, speed);
    			if(timer - tp1 >= 2.2){
        			stage = 3;
        			lock = 0;
        		}
    		}

    		if(stage == 3){
    			Robot.drivetrain.Move(-turnspeed, turnspeed);
    			if(NavHeading < 1 || NavHeading > 359){
        			stage = 4;
        		}
    		}

    		if(stage == 4){
    			if(lock == 0){
    				tp1 = timer;
    				lock = 1;
    			}
    			Robot.drivetrain.Move(speed, speed);
    			if(timer - tp1 > 0.8){
        			stage = 5;
        			lock = 0;
        		}
    		}
    		
    		if(stage == 5){
    			if(lock == 0){
    				tp1 = timer;
    				lock = 1;
    			}
    			double a = Math.max(FLamps, FRamps);
    			double t = (timer - tp1);
    	    	peak = Math.max(peak, a);
    	    	//SmartDashboard.putNumber("Peak", t);
    	    	
        		if(FRamps >= 5 || FLamps >= 5 || t > 3.2){
        			done = 1;
        		}
        		if(done == 0){
        			Robot.drivetrain.Move(0.4, 0.4);
        		}
        		if(done == 1){
        			Robot.boxhandler.SpinGrab(-0.4);
        			Robot.drivetrain.Move(0, 0);
        		}
        	}
    		
    		
    		
    		
    		
    	}
    	else if(gameData.charAt(0) == 'L' && start == 1){
    		
    		if(stage == 1){
        		Robot.drivetrain.Move(-turnspeed, turnspeed);
        		if(NavHeading <= 315 && NavHeading > 180){
        			stage = 2;
        		}
    		}
    		
    		if(stage == 2){
    			if(lock == 0){
    				tp1 = timer;
    				lock = 1;
    			}
    			Robot.drivetrain.Move(speed, speed);
    			if(timer - tp1 >= 2.6){
        			stage = 3;
        			lock = 0;
        		}
    		}

    		if(stage == 3){
    			Robot.drivetrain.Move(turnspeed, -turnspeed);
    			if(NavHeading < 2 || NavHeading > 358){
        			stage = 4;
        		}
    		}

    		if(stage == 4){
    			if(lock == 0){
    				tp1 = timer;
    				lock = 1;
    			}
    			Robot.drivetrain.Move(speed, speed);
    			if(timer - tp1 > 0.6){
        			stage = 5;
        		}
    		}
    		
    		if(stage == 5){
    			double a = Math.max(FLamps, FRamps);
    	    	peak = Math.max(peak, a);
    			double t = (timer - tp1);
    	    	//SmartDashboard.putNumber("Peak", peak);
        		if(FRamps >= 5 || FLamps >= 5 || t > 3.2){
        			done = 1;
        		}
        		if(done == 0){
        			Robot.drivetrain.Move(0.4, 0.4);
        		}
        		if(done == 1){
        			Robot.boxhandler.SpinGrab(-0.4);
        			Robot.drivetrain.Move(0, 0);
        		}
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
