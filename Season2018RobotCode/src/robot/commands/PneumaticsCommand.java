package org.usfirst.frc.team1729.robot.commands;

import org.usfirst.frc.team1729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1729.robot.commands.*;

/**
 *
 */
public class PneumaticsCommand extends Command {

    public PneumaticsCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.pneumatics);
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(Robot.ThisRobot == 1){
	    	boolean Abtn5 = Robot.oi.ArcadeBTN(5);
	    	boolean Abtn6 = Robot.oi.ArcadeBTN(6);
	    	
	    	if(Abtn5 == true){
	    		Robot.pneumatics.LowGear();
	    	}
	    	if(Abtn6 == true){
	    		Robot.pneumatics.HighGear();
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
