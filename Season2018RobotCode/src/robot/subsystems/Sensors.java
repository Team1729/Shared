
package org.usfirst.frc.team1729.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1729.robot.Robot;
import org.usfirst.frc.team1729.robot.commands.Toggles;

import com.kauailabs.navx.frc.AHRS;

/**
 *
 */
public class Sensors extends Subsystem {
	
	private AnalogInput Right = new AnalogInput(1);
	private AnalogInput Left = new AnalogInput(0);

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	AHRS Nav = new AHRS(SPI.Port.kMXP);
	Relay LED1 = new Relay(0);
	Relay LED2 = new Relay(1);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new Toggles());
    }
    
    public void LedState(double state){
    	
    	
    	if(state == 3){ //blue
    		LED2.setDirection(Relay.Direction.kForward);
    		LED2.set(Relay.Value.kOn);
    		LED1.set(Relay.Value.kOff);
    	} 
    	
    	if(state == 2){ // red
    		LED1.setDirection(Relay.Direction.kForward);
    		LED1.set(Relay.Value.kOn);
    		LED2.set(Relay.Value.kOff);
    	} 
    	
    	if(state == 1){ // pink
    		LED1.setDirection(Relay.Direction.kForward);
    		LED2.setDirection(Relay.Direction.kForward);

    		LED1.set(Relay.Value.kOn);
    		LED2.set(Relay.Value.kOn);
    	} 
    	
    	if(state == 0){ // off
    		LED1.set(Relay.Value.kOff);
    		LED2.set(Relay.Value.kOff);
    	}
    
    }
    
    public void Encoder(){
    	double EncR = Right.getValue();
    	double EncL = Left.getValue();    	
    	//////SmartDashboard.putNumber("EncR", EncR);
    	//////SmartDashboard.putNumber("EncL", EncL);
    }
    
    public double NavHeading(){
    	double NavX = Nav.getFusedHeading();
    	//////SmartDashboard.putNumber("NavX Gyro", NavX);
    	return NavX;
    }
    	
	// ENCODER CODE ======================================================
	
	   double LeftOverAll = 0;
	   double LRotations = 0;
	   double LLastDegree = 0;
	   
	   double RightOverAll = 0;
	   double RRotations = 0;
	   double RLastDegree = 0;
	   
	   double ZeroL = 0;
	   double ZeroR = 0;
	   
	   
	  
	   public double GetLeftEncoder(){
		  
		   // 3 rotations of encoder for 1 of wheel  
			  
		   double  RawEncoder = Left.getValue();
		  
		   double Degree = 11.27777777777778;
		   double Encoder = RawEncoder / Degree;
		   //////SmartDashboard.putNumber("One Degree", Degree);
		   
		   if(Robot.ResetLeftEcoder == 0){
			   ZeroL = Encoder;
			   Robot.ResetLeftEcoder = 1;
			   LeftOverAll *= 0;
			   LRotations *= 0;
			   LLastDegree *= 0;
		   }
		  
		   //Right//////////////////////////////////////////////////////////////
		   if(Encoder < 180 && LLastDegree > 180 ){
			   LRotations = LRotations + 1;
		   } else {
			   LRotations = LRotations;
		   }
		   
		   LeftOverAll = (Encoder + (LRotations * 360) - ZeroL);
		   
		   LeftOverAll = (int)(LeftOverAll/3 * 15/22); // divide by 3 = encoder is 3 times as fast as the wheel  multiply 15 and divide by 22 for sprocket ratios
		   
		   LLastDegree = Encoder;
		   
		   //SmartDashboard.putNumber("LOA", LeftOverAll);
		   //SmartDashboard.putNumber("ZeroL", ZeroL);
		   //SmartDashboard.putNumber("Raw Encoder L", RawEncoder);
		   //SmartDashboard.putNumber("Rotations of Encoder L", LRotations);
		   //SmartDashboard.putNumber("LastDegree L", LLastDegree);
		   //SmartDashboard.putNumber("EncoderDegree L", Encoder);
		   return LeftOverAll;
	   }
	   
	   
	   
	   int  REncoderDegrees;
	   public double GetRightEncoder(){
		   
		   // 3 rotations of encoder for 1 of wheel  
		  
		   double  RawEncoder = Right.getValue();
		   double Degree = 11.27777777777778;
		   double Encoder = RawEncoder / Degree;
		   ////SmartDashboard.putNumber("One Degree", Degree);
		   
		   if(Robot.ResetRightEcoder == 0){
			   ZeroR = Encoder;
			   Robot.ResetRightEcoder = 1;
			   RightOverAll *= 0;
			   RRotations *= 0;
			   RLastDegree *= 0;
		   }
		  
		   //Right//////////////////////////////////////////////////////////////
		   if(Encoder < 180 && RLastDegree > 180 ){
			   RRotations = RRotations + 1;
		   } else {
			   RRotations = RRotations;
		   }
		   
		   RightOverAll = (Encoder + (RRotations * 360) - ZeroR);
		   
		   RightOverAll = (int)(RightOverAll/3 * 15/22);
		   
		   RLastDegree = Encoder;
		   
		   //SmartDashboard.putNumber("ROA", RightOverAll);
		   ////SmartDashboard.putNumber("ZeroR", ZeroR);
		   ////SmartDashboard.putNumber("Raw Encoder R", RawEncoder);
		   ////SmartDashboard.putNumber("Rotations of Encoder R", RRotations);
		   ////SmartDashboard.putNumber("LastDegree R", RLastDegree);
		   ////SmartDashboard.putNumber("EncoderDegree R", Encoder);
		   
		   return RightOverAll;
	   }
	    double LError;
	   	double RError;
	   	
	 public void EncoderDriveStraight(double speed, double distance){
		double R;
		double L;
		double RightEncoder = Robot.sensors.GetRightEncoder();
		double LeftEncoder = Robot.sensors.GetLeftEncoder();
		
		R = speed;
		L = speed;
	   	
	    RError = (RightEncoder - LeftEncoder);
	    LError = (LeftEncoder - RightEncoder);
	    
	    R = R - RError;
	    L = L - LError;
	    
	     if(distance - RightEncoder < 0){
	    	R = 0;
	    	L = 0;
	     } 
	    
	     
	     ////SmartDashboard.putNumber("Right", R);
	     ////SmartDashboard.putNumber("Left", L);
	     ////SmartDashboard.putNumber("RError", RError);
	     ////SmartDashboard.putNumber("LError", LError);
	     
	     
	    Robot.drivetrain.Move(L, R);
	   	
	   
	   }
}

