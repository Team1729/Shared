
package org.usfirst.frc.team1729.robot.subsystems;

import org.usfirst.frc.team1729.robot.Robot;
import org.usfirst.frc.team1729.robot.commands.AutoCheck;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class AutoMove extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    
    public char OurSwitch(){
    	new AutoCheck();
    	return Robot.OurSwitch;
    }
    
    
    
    
    
    public void VisionMove(double Lspeed, double Rspeed, double Distance){
    	Lspeed *= -1;
    	Rspeed *= -1;
    	double vs = 0.35;
    	
    	if(Distance > 46){ // cube chase dist = 50 ||| vest = 8.5
    		Robot.drivetrain.Move(0, 0);
    		Robot.pneumatics.Grab();  
    		
    	}else{ // cube chase dist = 25 ||| vest = 7
        	Robot.drivetrain.Move(Lspeed - vs, Rspeed + vs);
    	}
    	
    	
    	// BOX CHASE =====================================================
    	/* 
    	Lspeed *= -1;
    	Rspeed *= -1;
    	double vs = 0.35;
    	if(Distance < 25){ // cube chase dist = 25 ||| vest = 7
        	Robot.drivetrain.Move(Lspeed - vs, Rspeed + vs);
    	}
    	else if(Distance > 46){ // cube chase dist = 50 ||| vest = 8.5
            Robot.drivetrain.Move(Lspeed + vs, Rspeed - vs);
    	}else{
    		Robot.drivetrain.Move(Lspeed, Rspeed);
    	}
    	 */
    }


    
    public void GyroDrive(double WantedAngle, double Speed){
    	double R = 0;
    	double L = 0;
    	
    	double gyro = Robot.sensors.NavHeading();
    	
 	  
 	   
 	  double i = (WantedAngle - gyro) + 3600;
 	   
 	  i = i % 360;

		if(i <  0.5 || i > 360 - 0.5){
			R = Speed;
			L = Speed;
		} else if(i < 180){
			L = Speed;
			R = Speed - (i/20.0);
		} else {
			L = Speed + ((i-360)/20.0);
			R = Speed;
		}
		//SmartDashboard.putNumber("RGyro", R);
		//SmartDashboard.putNumber("LGyro", L);
		
		SmartDashboard.putNumber("I", i);
		Robot.drivetrain.Move(L, R);

    }
    
    	
    public void RampUp(){
    	double distance = 100;
    	double speed = 0;
    	double MaxSpeed = 0.6;
    	double encoder = 100;
    	double StartSpeed = 0.1;
    	
    	if(encoder < (distance/10)){
    		if(speed < MaxSpeed){
    			speed = StartSpeed + (encoder/100); 
    		}else {
    			speed = MaxSpeed;	
    		}
    	} 
    	
    	if(encoder > (distance/10)){
    		
    	}
    	
    }
}

