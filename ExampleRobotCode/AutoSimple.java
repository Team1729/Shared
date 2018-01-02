/* AutoSimple.java   ---   Revision notes for entire TinyTestBot package are at OI.java */

package org.usfirst.frc.team1729.robot.commands;

import org.usfirst.frc.team1729.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *  This Command AutoSimple() is a simplification of the 2017 competition season
 *  autonomous routine that made the robot go 10 feet straight ahead.
 */

public class AutoSimple extends Command
	{
	
			// PROGRAM INTERNALS start here - please do not disturb

    
	public AutoSimple()
		{
			// Use requires() here to declare subsystem dependencies
			// eg. requires(chassis); [WPI comment]
		}
	
    
			// Called just before this Command runs the first time [WPI comment]
	protected void initialize()
		{
		}

	
			// Called repeatedly when this Command is scheduled to run [WPI comment]
	protected void execute()
		{
		Robot.driveTrain.moveSimple(0);
			//
			//    To experiment with small changes to this code, change 0
			// to other numbers
			//
		}

    
			//    Make this return true when this Command no longer needs to run
			// execute() [WPI comment]
	protected boolean isFinished()
		{
		return false;
		}
	
    
			// Called once after isFinished returns true [WPI comment]
	protected void end()
		{
		Robot.driveTrain.driveStop();
		}

    
			// Called when another command which requires one or more of the same
			// subsystems is scheduled to run [WPI comment]
	protected void interrupted()
		{
		Robot.driveTrain.driveStop();
		}
	}
