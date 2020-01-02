
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;

import frc.robot.commands.Lifter.LiftCommandGroups.*;
import frc.robot.commands.Lifter.LiftPositions.*;

import frc.robot.commands.*;
import frc.robot.commands.Misc.*;
import frc.robot.commands.Pneumatics.*;

import frc.robot.commands.Manipulator.CommandGroups.*;
import frc.robot.commands.Manipulator.Commands.*;

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

	Button Abtn1 = new JoystickButton(Arcade, 1);
	Button Abtn2 = new JoystickButton(Arcade, 2);
	Button Abtn3 = new JoystickButton(Arcade, 3);
	Button Abtn4 = new JoystickButton(Arcade, 4);
	Button Abtn5 = new JoystickButton(Arcade, 5);
	Button Abtn6 = new JoystickButton(Arcade, 6);
	Button Abtn7 = new JoystickButton(Arcade, 7);
	Button Abtn8 = new JoystickButton(Arcade, 8);
	Button Abtn9 = new JoystickButton(Arcade, 9);
	Button Abtn10 = new JoystickButton(Arcade, 10);
	Button Abtn11 = new JoystickButton(Arcade, 11);
	Button Abtn12 = new JoystickButton(Arcade, 12);

	Button Obtn1 = new JoystickButton(Operator, 1);
	Button Obtn2 = new JoystickButton(Operator, 2);
	Button Obtn3 = new JoystickButton(Operator, 3);
	Button Obtn4 = new JoystickButton(Operator, 4);
	Button Obtn5 = new JoystickButton(Operator, 5);
	Button Obtn6 = new JoystickButton(Operator, 6);
	Button Obtn7 = new JoystickButton(Operator, 7);
	Button Obtn8 = new JoystickButton(Operator, 8);
	Button Obtn9 = new JoystickButton(Operator, 9);
	Button Obtn10 = new JoystickButton(Operator, 10);
	Button Obtn11 = new JoystickButton(Operator, 11);
	Button Obtn12 = new JoystickButton(Operator, 12);



	public OI(){
		
		
		//Abtn1.whileHeld(new Camera_Lights());
		Abtn1.whileHeld(new AutoAssist());
		Abtn3.toggleWhenPressed(new ShiftDrive());
		Abtn6.toggleWhenPressed(new HabLVL3());
		

		Obtn1.whileHeld(new Release());
		//Obtn1.whileHeld(new HatchRelease());

		Obtn2.whileHeld(new Intake());
		Obtn3.toggleWhenPressed(new ManipulatorZ());
		Obtn4.whenPressed(new ShiftLiftToHab("Hab"));
		Obtn5.toggleWhenPressed(new Manual_Control());

	}
	
	// Button Links ============================================================
	
	
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
	
	public int OpHat(){
		return Operator.getPOV();
	}
	
	public boolean ArcadeBTN(int num){
		return Arcade.getRawButton(num);
	}
	
	public boolean OpBTN(int num){
		return Operator.getRawButton(num);	
	}
	
	public double OpY(){
		double Y = Operator.getY(); // stupid sticks are backwards
		if(Y > -0.1 && Y < 0.1){
			Y = 0;
		}
		return Y;
	}
	
	public double OpX(){
		double X = Operator.getX();
		if(X > -0.07 && X < 0.07){
			X = 0;
		}
		return X;
	}
	
	public double OpZ(){
		return Operator.getZ();
	}
	
	public double OpTh(){
		return Operator.getThrottle();
	} 
	
	public double ArcadeTh(){
		return Arcade.getThrottle();
	} 
}
