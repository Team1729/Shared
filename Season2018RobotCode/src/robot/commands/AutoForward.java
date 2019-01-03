package org.usfirst.frc.team1729.robot.commands;

import org.usfirst.frc.team1729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoForward extends Command {

    public AutoForward() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }
    double timer;
    // Called just before this Command runs the first time
    protected void initialize() {
    	timer = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double EncL = Robot.sensors.GetLeftEncoder();
    	double EncR = Robot.sensors.GetRightEncoder();
    	Robot.sensors.Encoder();
    	timer += 0.02;
    	SmartDashboard.putNumber("Timer", timer);

    	if(timer < 4){
    	//Robot.drivetrain.Move(0.3, 0.25);
    		Robot.automove.GyroDrive(0, 0.4);
    	}else{
    		Robot.drivetrain.Move(0, 0);
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
