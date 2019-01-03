package org.usfirst.frc.team1729.robot.commands;

import org.usfirst.frc.team1729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class BoxCommand extends Command {

    public BoxCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.boxhandler);
    }

    // Called just before this Command runs the first time
    
    
    
    
    
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double OpHat = Robot.oi.OpHat();
        double OpTh = Robot.oi.OpTh();
        double OpY = Robot.oi.OpY();
        double OpX = Robot.oi.OpX();
        double OpZ = Robot.oi.OpZ();
        boolean OpBTN1 = Robot.oi.OpBTN(1);
        boolean OpBTN2 = Robot.oi.OpBTN(2);
        boolean OpBTN3 = Robot.oi.OpBTN(3);
        boolean OpBTN4 = Robot.oi.OpBTN(4);
        boolean OpBTN5 = Robot.oi.OpBTN(5);
        boolean OpBTN6 = Robot.oi.OpBTN(6);
        boolean OpBTN7 = Robot.oi.OpBTN(7);
        boolean OpBTN9 = Robot.oi.OpBTN(9);
        
        boolean OpBTN11 = Robot.oi.OpBTN(11);
        boolean OpBTN12 = Robot.oi.OpBTN(12);
        
    	OpTh = Robot.oi.OpTh();
    	
    	if(Robot.ThisRobot == 1){
    		
    		if(OpHat == 0){
    			Robot.wrist.WristMove(-0.8);
    		}
    		else if(OpHat == 180){
    			Robot.wrist.WristMove(0.8);
    		}else{
    			Robot.wrist.WristMove(0);
    		}
    		
    		if(OpY < -0.16 || OpY > 0.16 && OpBTN9 == false){
    			Robot.lifter.LifterMove(OpY);
    		}
    		else if(OpBTN9 == true){
    			Robot.lifter.LiftOverride(OpY);
    		}else{
    			Robot.lifter.LifterMove(0);
    		}
    		
    		
    		if(OpBTN3 == true){
    			Robot.boxhandler.SpinGrab(-0.6);
    		}
    		else if(OpBTN2 == true){
    			Robot.boxhandler.SpinGrab(0.7);
    		}else{
    			Robot.boxhandler.SpinGrab(0);
    		}
    		
    		if(OpBTN1 == true){
    			Robot.pneumatics.Grab();
    		}
    		
    		if(OpBTN4 == true){
    			Robot.pneumatics.Release();
    		}
    	}
	}
    

    // Make tshis return true when this Command no longer needs to run execute()
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
