package org.usfirst.frc.team1729.robot.commands;

import org.usfirst.frc.team1729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MoveDrive extends Command {

	double Speed;
	double Distance;
	double WantedAngle;
	
	/**
	 * 
	 * @param DriveSpeed - Speed of Motor
	 * @param DriveDistance - Encoder Distance
	 * @param Angle - Gyro Drive Straight angle
	 */
    public MoveDrive(double DriveSpeed, double DriveDistance, double Angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	Speed = DriveSpeed;
        Distance = DriveDistance /* 15.65*/;
        WantedAngle = Angle;
    }

    double R;
   	double L;
   	double Gyro;
   	double Encoder;
   	int i;
    // Called just before this Command runs the first time
    protected void initialize() {
    	R = 0;
		L = 0;
		Robot.ResetLeftEcoder = 0;
		Robot.ResetRightEcoder = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
       		Encoder = Robot.sensors.GetRightEncoder();
    	    Robot.automove.GyroDrive(WantedAngle, Speed);	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Encoder >= Distance){
    		//SmartDashboard.putString("Auto", "OFF");
    		Robot.ResetLeftEcoder = 0;
    		Robot.ResetRightEcoder = 0;
	    	return true;
	     } else{
	    	 //SmartDashboard.putString("Auto", "ON");
	    	 return false;
	     }
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.Move(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.Move(0, 0);
    	
    }
}
