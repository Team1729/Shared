/* DriverDrive.java   ---   Revision notes for entire TinyTestBot package are at OI.java */ 

package org.usfirst.frc.team1729.robot.commands;

import org.usfirst.frc.team1729.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *    This Command calls Robot.drivetrain.driverDrive() and nothing else.
 *
 *    PLEASE NOTE that everything else in this command (except "requires(Robot.drivetrain)")
 * is the same as in their ExampleCommand file.
 *    However, it seems better to leave all these WPI comments in Command files in case
 * the file needs to be modified, especially if in a hurry.
 */

public class DriverDrive extends Command
	{

	public DriverDrive()
		{
			// Use requires() here to declare subsystem dependencies
			// eg. requires(chassis);
		
		requires(Robot.driveTrain);
		}
	
	
			// Called just before this Command runs the first time
	protected void initialize()
		{
		}

	
			// Called repeatedly when this Command is scheduled to run
	protected void execute()
		{
		Robot.driveTrain.driverDrive();
		}

	
			// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
		{
		return false;
		}

	
			// Called once after isFinished returns true
	protected void end()
		{
		}

	
			// Called when another command which requires one or more of the same
			// subsystems is scheduled to run
	protected void interrupted()
		{
		}
	}
