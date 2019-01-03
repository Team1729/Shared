package org.usfirst.frc.team1729.robot.commands;

import org.usfirst.frc.team1729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveWrist extends Command {
	
	double speed;
	double time;
	double timer;

	/**
	 * 
	 * @param WantedSpeed - Wrist speed
	 * @param WantedTime - Time
	 */
    public MoveWrist(double WantedSpeed, double WantedTime) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//requires(Robot.wrist);
    	speed = WantedSpeed;
    	time = WantedTime;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	timer += 0.02;
    	Robot.wrist.WristMove(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(timer >= time){
    		return true;
    	}else{
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.wrist.WristMove(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
