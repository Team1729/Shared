package org.usfirst.frc.team1729.robot.commands;

import org.usfirst.frc.team1729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveTurn extends Command {

	double WantedAngle;
	double WantedSpeed;
	/**
	 * 
	 * @param Angle - Angle robot will turn to
	 * @param Speed - Speed robot will turn
	 */
    public MoveTurn(double Angle, double Speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	WantedAngle = Angle;
    	WantedSpeed = Speed;
    }
    
    double R;
	double L;
	int i;
	
    // Called just before this Command runs the first time
    protected void initialize() {
    	R = 0;
    	L = 0;
    	i = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	    	double gyro = Robot.sensors.NavHeading();
    	    	
    	 	  i = (int) (WantedAngle - gyro) + 3600;
    	 	   
    	 	  i = i % 360;    	  	
    	  	
    			if(i <  0.5 || i > 360 - 0.5){
    				R = 0;
    				L = 0;
    			} else if(i < 180){
    				L = WantedSpeed;
    				R = -WantedSpeed;
    			} else {
    				L = -WantedSpeed;
    				R = WantedSpeed;
    			}
    			
    			Robot.drivetrain.Move(L, R);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(i <  0.5 || i > 360 - 0.5){
        return true;
        
    	} else {
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
