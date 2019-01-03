package org.usfirst.frc.team1729.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Wait extends Command {

	double WantedTime;
	double time;
    public Wait(double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	WantedTime = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	time = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	time += 0.02;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(time >= WantedTime){
    		return true;
    	}else{
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
