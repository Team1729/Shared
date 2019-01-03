package org.usfirst.frc.team1729.robot.commands;



import org.usfirst.frc.team1729.robot.Robot;


import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ServosCommand extends Command {

    public ServosCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.servos);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	//Code for shifting is in togges not here LEAVE THIS COMMAND NOW!
    	
    	/* 
    	boolean Abtn5 = Robot.oi.ArcadeBTN(5);
    	boolean Abtn6 = Robot.oi.ArcadeBTN(6);
    	
    	if(Robot.ThisRobot == 2){
    		if(Abtn5 == true){
    			Robot.servos.LowGear();
    		}
    		if(Abtn6 == true){
    			Robot.servos.HighGear();
    		}
    	}*/
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
