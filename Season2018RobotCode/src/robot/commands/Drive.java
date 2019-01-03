package org.usfirst.frc.team1729.robot.commands;

import org.usfirst.frc.team1729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive extends Command {

    public Drive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }
    
    // Called just before this Command runs the first time
    double Right;
    double Left;
    double ArcadeY;
    double ArcadeX;
    boolean ABTN3;
    boolean ABTN4;
    boolean ABTN11;
    boolean ABTN12;
    boolean ABTN9;
    boolean ABTN7;
 
    protected void initialize() {
    	Right = 0;
    	Left = 0;
    	ArcadeY = 0;
    	ArcadeX = 0;
    	
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Right = Robot.oi.RightY(); <- tank sticks
    	//Left = Robot.oi.LeftY();
    	//Robot.ResetLeftEcoder = 0;
    	//Robot.ResetRightEcoder = 0;
    	ArcadeY = Robot.oi.ArcadeY();
    	ArcadeX = Robot.oi.ArcadeX();
    	boolean Dbtn1 = Robot.oi.ArcadeBTN(1);
    	SmartDashboard.putNumber("EncR",Robot.sensors.GetRightEncoder());    	
    	SmartDashboard.putNumber("EncL",Robot.sensors.GetLeftEncoder());
    	Robot.drivetrain.Arcade(ArcadeY, ArcadeX);
    	SmartDashboard.putNumber("NavX", Robot.sensors.NavHeading());
    	SmartDashboard.putNumber("ResetRightEncoder", Robot.ResetRightEcoder);
    	SmartDashboard.putNumber("ResetLeftEncoder", Robot.ResetLeftEcoder);
    	
    	ABTN3 = Robot.oi.ArcadeBTN(3);
    	ABTN4 = Robot.oi.ArcadeBTN(4);
    	ABTN11 = Robot.oi.ArcadeBTN(11);
    	ABTN12 = Robot.oi.ArcadeBTN(12);
    	ABTN9 = Robot.oi.ArcadeBTN(9);
    	ABTN7 = Robot.oi.ArcadeBTN(7);

    	if(ABTN3 == true){
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
    	if(ABTN4 == true){
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
    	
    	if(ABTN7 == true){
    		Robot.sensors.LedState(3);
    	}
    	if(ABTN9 == true){
    		Robot.sensors.LedState(2);
    	}
    	if(ABTN11 == true){
    		Robot.sensors.LedState(1);
    	}
    	if(ABTN12 == true){
    		Robot.sensors.LedState(0);
    	}
    	
    	if(Dbtn1 == true){
    		new Auto();
    	}
    	
    	//Robot.automove.GyroDrive(0);
    	
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
