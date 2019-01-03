package org.usfirst.frc.team1729.robot.commands;

import org.usfirst.frc.team1729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveIntake extends Command {

	double time;
	double speed;
	double timer;
	/**
	 * 
	 * @param WantedSpeed - Intake speed
	 * @param WantedTime - Time
	 */
    public MoveIntake(double WantedSpeed, double WantedTime) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	speed = WantedSpeed;
    	time = WantedTime;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	timer += 0.02;
    	Robot.boxhandler.SpinGrab(speed);
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
