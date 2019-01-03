package org.usfirst.frc.team1729.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1729.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	Joystick Arcade  = new Joystick(0);
	Joystick Operator = new Joystick(1);
	
	Joystick Right = new Joystick(2);
	Joystick Left = new Joystick(3);

	public OI(){
		
		//Right Buttons ==================================================================
		
			Button R1 = new JoystickButton(Right, 1);
			Button R2 = new JoystickButton(Right, 2);
			Button R3 = new JoystickButton(Right, 3);
			Button R4 = new JoystickButton(Right, 4);
			Button R5 = new JoystickButton(Right, 5);
			Button R6 = new JoystickButton(Right, 6);
			Button R7 = new JoystickButton(Right, 7);
			Button R8 = new JoystickButton(Right, 8);
			Button R9 = new JoystickButton(Right, 9);
			Button R10 = new JoystickButton(Right, 10);
			Button R11 = new JoystickButton(Right, 11);
			Button R12 = new JoystickButton(Right, 12);
		
		
		//Driver2 Buttons ==================================================================
		
			Button L1 = new JoystickButton(Left, 1);
			Button L2 = new JoystickButton(Left, 2);
			Button L3 = new JoystickButton(Left, 3);
			Button L4 = new JoystickButton(Left, 4);
			Button L5 = new JoystickButton(Left, 5);
			Button L6 = new JoystickButton(Left, 6);
			Button L7 = new JoystickButton(Left, 7);
			Button L8 = new JoystickButton(Left, 8);
			Button L9 = new JoystickButton(Left, 9);
			Button L10 = new JoystickButton(Left, 10);
			Button L11 = new JoystickButton(Left, 11);
			Button L12 = new JoystickButton(Left, 12);
		
		// Arcade Buttons ===================================================================
		
			Button A1 = new JoystickButton(Arcade, 1);
			Button A2 = new JoystickButton(Arcade, 2);
			Button A3 = new JoystickButton(Arcade, 3);
			Button A4 = new JoystickButton(Arcade, 4);
			Button A5 = new JoystickButton(Arcade, 5);
			Button A6 = new JoystickButton(Arcade, 6);
			Button A7 = new JoystickButton(Arcade, 7);
			Button A8 = new JoystickButton(Arcade, 8);
			Button A9 = new JoystickButton(Arcade, 9);
			Button A10 = new JoystickButton(Arcade, 10);
			Button A11 = new JoystickButton(Arcade, 11);
			Button A12 = new JoystickButton(Arcade, 12);
			
		// Operator Buttons ==============================================================
			
			Button O1 = new JoystickButton(Operator, 1);
			Button O2 = new JoystickButton(Operator, 2);
			Button O3 = new JoystickButton(Operator, 3);
			Button O4 = new JoystickButton(Operator, 4);
			Button O5 = new JoystickButton(Operator, 5);
			Button O6 = new JoystickButton(Operator, 6);
			Button O7 = new JoystickButton(Operator, 7);
			Button O8 = new JoystickButton(Operator, 8);
			Button O9 = new JoystickButton(Operator, 9);
			Button O10 = new JoystickButton(Operator, 10);
			Button O11 = new JoystickButton(Operator, 11);
			Button O12 = new JoystickButton(Operator, 12);
			
			O11.whileHeld(new DownOverride());
			O12.whileHeld(new UpOverride());
	}
	
	// Button Links ============================================================
	
	/*
	
	   Driver Buttons =================
	   
	   Y axis = forward/backward
	   X axis = turn Right/Left
	   Button 2 = toogle gears high/low
	   
	   Operator Buttons ================
	   
	   Y axis = Wrist (forward is down)
	  TopHat = Lifter
	  Trigger = Grab
	  Button 4 = Release
	  Button 2(Thumb) = Intake
	  Button 3 = Eject
	  Hold button 7 and Throttle = climber
	   
	 */
	public double RightY(){
		return Right.getY();
	}
	public double RightX(){
		return Right.getX();
	}
	
	public double LeftY(){
		return Left.getY();
	}
	public double LeftX(){
		return Left.getX();
	}
	
	public double ArcadeX(){
		double X = Arcade.getX();
		if(X > -0.07 && X < 0.07){
			X = 0;
		}
		return X;
	}
	
	/**
	 * 
	 * @return returning arcade Y ( + is forward)
	 */
	public double ArcadeY(){
		double Y = Arcade.getY() * -1; // Hardware is backwards
		if(Y > -0.07 && Y < 0.07){
			Y = 0;
		}
		return Y;
	}
	
	public double OpHat(){
		return Operator.getPOV();
	}
	
	public boolean RightBTN(int num){
		return Right.getRawButton(num);
	}
	
	public boolean LeftBTN(int num){
		return Left.getRawButton(num);
	}
	
	public boolean ArcadeBTN(int num){
		return Arcade.getRawButton(num);
	}
	
	public boolean OpBTN(int num){
		return Operator.getRawButton(num);	
	}
	
	public double OpY(){
		double Y = Operator.getY() * -1; // stupid sticks are backwards
		if(Y > -0.07 && Y < 0.07){
			Y = 0;
		}
		return Y;
	}
	
	public double OpX(){
		return Operator.getX();
	}
	
	public double OpZ(){
		return Operator.getZ();
	}
	
	public double OpTh(){
		return Operator.getThrottle();
	} 
}
