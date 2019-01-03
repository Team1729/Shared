


package org.usfirst.frc.team1729.robot.commands;

import org.usfirst.frc.team1729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class EncoderStop extends Command {
	
	double WantedAngle;
	double WantedSpeed;
	/**
	 * 
	 * @param angle - Angle to follow
	 * @param speed - Speed of motors
	 */
    public EncoderStop(double angle, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	WantedAngle = angle;
    	WantedSpeed = speed; 
    }

    double CurrentVal;
    double LastVal;
    double dif;
    double skip;
    // Called just before this Command runs the first time
    protected void initialize() {
    	CurrentVal = Robot.sensors.GetRightEncoder();
    	LastVal = CurrentVal;
    	dif = 0;
    	skip = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.automove.GyroDrive(WantedAngle, WantedSpeed);
        CurrentVal = Robot.sensors.GetRightEncoder();
       dif = CurrentVal - LastVal;
       LastVal = CurrentVal;
       skip += 1;
       System.out.println(dif);
       //SmartDashboard.putNumber("Dif", dif);
       //SmartDashboard.putNumber("Current", CurrentVal);
       //SmartDashboard.putNumber("Last", LastVal);

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(dif == -1 && skip > 30){
    		
     	   return true;
        }else{
            return false;
        }
    	//return false;
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
