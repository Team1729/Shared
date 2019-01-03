package org.usfirst.frc.team1729.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SmartAutoLeft_V2 extends Command {

    public SmartAutoLeft_V2() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    int stage;
    double startlock;
    
    Command command;
    // Called just before this Command runs the first time
    protected void initialize() {
    	stage = 1;
    	startlock = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(startlock == 0){
    	command = new Wait(0.02);
    	command.start();
    	startlock = 1;
    	}
    String gameData = DriverStation.getInstance().getGameSpecificMessage();
    	SmartDashboard.putNumber("Stage", stage);
    	
    	if(command.isRunning() == false){
    	if(gameData.charAt(0) == 'R' && gameData.charAt(1) == 'R'){
    		switch(stage){
    		case 1:
    			command = new MoveDrive(0.5, 3200, 0);
    			break;
    		case 2:
    			command = new MoveTurn(90, 0.6);
    			break;
    		case 3:
    			command = new MoveDrive(0.5, 1000, 90);
    			break;
    		case 4:
    			command = new MoveLift(0.6, 2.3, 0.2, 1.8);
    			break;
    			
    			case 0:	default:
    		command = new Wait(0.06);
    		break;
    		}
		}
  
    	if(gameData.charAt(0) == 'L' && gameData.charAt(1) == 'R'){
    		switch(stage){
    		case 1:
    		command = new Wait(0.02);
    			break;
    		case 2:
    		command = new MoveDrive(0.5, 2200, 0);
    		new MoveLift(0.6, 2.2, 0.4, 2).start(); // origional 0.4 and 2 on wrist
    			break;
    		case 3:
    		command = new MoveTurn(90, 0.5);
    			break;
    		case 4:
    		command = new EncoderStop(90, 0.3);
    			break;
    		case 5:
    		command = new MoveIntake(-0.4, 2);
    			break;
    			
    			case 0:	default:
    		command = new Wait(0.06);
    		break;
    		}
    	}
    	
	  	if(gameData.charAt(1) == 'L'){
	  		switch(stage){
	  		case 1:
	  		command = new Wait(0.02);
	  			break;
	  		case 2:
	  		command = new MoveDrive(0.6, 2000, 0);
	  		new MoveLift(0.8, 3.3, 0.4, 1.7).start();
	  			break;
	  		case 3:
		  	command = new MoveDrive(0.4, 600, 0);
	  			break;
	  		case 4:
		  	command = new MoveDrive(0.4, 1700, 22);
	  			break;
	  		case 5:
		  	command = new MoveGrabber("release");
	  			break;
	  		case 6:
		  	command = new MoveDrive(-0.2, 300, 18);
	  			break;
	  		case 7:
		  	command = new MoveLift(-0.8, 3, 0.4, 1.4);
	  			break;
	  		case 8:
		  	command = new MoveTurn(160, 0.7);
	  			break;
	  		case 9:
	  		command = new Wait(0.02);
	  			break;
	  		case 10:
		  	command = new EncoderStop(160, 0.3);
		  	new MoveIntake(0.5, 3).start();
	  			break;
	  		case 11:
		  	command = new MoveGrabber("grab");
	  			break;
	  		case 12:
		  	command = new Wait(0.02);
	  			break;
	  		case 13:
		  	command = new MoveDrive(-0.3, 200, 170);
		  	new MoveLift(1, 1.3, -0.4, 2).start();
	  			break;
	  			
	  		case 0:	
	  		default:
    		command = new Wait(0.02);
    			break;
	  		}
	  		
	  		if(gameData.charAt(0) == 'L' && stage >= 13){
  				switch(stage){
  				case 14:
  				command = new EncoderStop(170, 0.4);
  					break;
  				case 15:
  				command = new MoveIntake(-0.4, 2);
  					break;
  					
  					
  				case 16:
  					command = new Wait(0.02);
  					default:
  					break;
  				}
  			}
	  	}
	  	command.start();
		stage += 1;
		SmartDashboard.putNumber("stage", stage);
    	}
    /*if(command.isCompleted() == true){
    	switch(stage){z
    	case 1: System.out.println("Stage 1");
    		command = new MoveTurn(90, 0.7);
    		break;
    	case 2: System.out.println("Stage 2");
    		command = new MoveTurn(270, 0.7);
    		break;
    		
    	case 0:	default:
    		command = new Wait(0.06);
    		break;
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
