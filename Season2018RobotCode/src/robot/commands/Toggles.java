package org.usfirst.frc.team1729.robot.commands;

import org.usfirst.frc.team1729.robot.Robot;




import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This code is used for toggling different things on, off, or change with one BTN
 */
public class Toggles extends Command {

    public Toggles() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//requires(Robot.sensors);
    }

    boolean BTN;//This is for getting the BTN that you want to toggle with
    boolean BTN4;
    boolean SwapBlock; //This is a check to stop the program from skipping thourgh the different modes before you can release the BTN
    double swap;//This is the number that is used for the change of modes
    boolean SwapBlock2;
    double Swap2;
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	SwapBlock = true;
    	swap = 1;
    	SwapBlock2 = true;
    	Swap2 = 1;
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*This rename is just to make it easy to type multiple times and call the get once each time through */
        BTN = Robot.oi.ArcadeBTN(2);
    	
    	if(BTN & SwapBlock){
    		SwapBlock = false;
    		
    		swap = (swap + 1) % 2; /* change 2 to any number that you want for however many modes you have. Remember in the is way it needs to be one more than 
    		the number of modes you have */
    		
    		SmartDashboard.putNumber("swapNumber", swap); /*Print out what mode you are on*/
    	} 
    	else if(!BTN && !SwapBlock){
    		SwapBlock = true;
    	}
    	
    	if(swap == 1){
    		
    				switch(Robot.ThisRobot){
    				case 1: // Prototype
    					Robot.pneumatics.LowGear();
    					break;
    				case 2: // Fezzik
    					Robot.servos.LowGear();
    					break;
    				default:
    					break;
    					
    		}
    	}
    	
    	if(swap == 0){
    		
    		switch(Robot.ThisRobot){
			case 1: // Prototype
				Robot.pneumatics.HighGear();
				break;
			case 2: // Fezzik
				Robot.servos.HighGear();
				break;
			default:
				break;
				
    		}
    	}
    	
    	/////////////////////////////////////////////////////////////////////////////////////
    	if(Robot.ThisRobot == 15){ // Prototype robot
    		// Grabber Arm Toggle 
    
    		BTN4 = Robot.oi.OpBTN(1);
     	
    		if(BTN4 & SwapBlock2){
    			SwapBlock2 = false;
     		
    			Swap2 = (Swap2 + 1) % 2; /* change 2 to any number that you want for however many modes you have. Remember in the is way it needs to be one more than 
     			the number of modes you have */
     		
    			SmartDashboard.putNumber("swapNumber", Swap2); /*Print out what mode you are on*/
    		} 
    		else if(!BTN4 && !SwapBlock2){
    			SwapBlock2 = true;
    		}
     	
    		if(Swap2 == 0){
    			/*Add code here for robot functions. e.g. Turn a motor on or off*/	
    			Robot.pneumatics.Release();
    		}
     	
    		if(Swap2 == 1){
    			/*Add code here for robot functions. e.g. Turn a motor on or off*/
    			Robot.pneumatics.Grab();
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
