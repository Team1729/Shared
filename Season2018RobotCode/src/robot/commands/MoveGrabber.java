package org.usfirst.frc.team1729.robot.commands;

import org.usfirst.frc.team1729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveGrabber extends Command {

	String Grab;
	/**
	 * 
	 * @param WantedGrab - Enter "grab" or "release"
	 */
    public MoveGrabber(String WantedGrab) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	Grab = WantedGrab;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Grab == "grab"){
    		Robot.pneumatics.Grab();
    	}
    	if(Grab == "release"){
    		Robot.pneumatics.Release();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
