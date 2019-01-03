package org.usfirst.frc.team1729.robot.commands;

import org.usfirst.frc.team1729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MoveLift extends Command {

	double Ltime;
	double Wtime;
	double liftspeed;
	double wristspeed;
    double timer;

   /**
    * 
    * @param LiftSpeed - Speed for Lift
    * @param LiftTime - Wime Lift should run for, will auto stop when it hits max up or down
    * @param WristSpeed - Speed for wrist
    * @param WristTime - Time Wrist should run for, must be less that time for Lift
    */
    public MoveLift(double LiftSpeed, double LiftTime, double WristSpeed, double WristTime) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//requires(Robot.lifter);
    	Ltime = LiftTime;
    	Wtime = WristTime;
    	liftspeed = LiftSpeed;
    	wristspeed = WristSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer = 0;
    }

    boolean MaxUp;
    boolean MaxDown;
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	MaxUp = Robot.lifter.MaxUP();
    	MaxDown = Robot.lifter.MaxDown();
    	timer += 0.02;
    	
    	Robot.lifter.LifterMove(liftspeed);
    	
    	if(timer > Wtime){
    		Robot.wrist.WristMove(0);
    	}else{
        	Robot.wrist.WristMove(wristspeed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(timer >= Ltime || MaxUp == true && liftspeed > 0 || MaxDown == true && liftspeed < 0){
            return true;
        }else{
        	return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.lifter.LifterMove(0);
    	Robot.wrist.WristMove(0);

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
}
