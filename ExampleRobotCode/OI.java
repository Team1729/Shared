/*  OI.java   ---   TinyTestBot package  */

package org.usfirst.frc.team1729.robot;

/*
 *     REVISION NOTES for entire TinyTestBot package start here.
 *  TinyTestBot - abridged for learning, from 2017 KiwiTestBot and other code
 *							(most originally written by RW)
 *
 *  1 jan 2018 jm  - TTB 1.0 as released
 */

/*
 *     The TinyTestBot package runs under the Command Based Programming
 *  option for FRC Java Programming. 
 *     The TinyTestBot package runs on our 3-wheel, 3-motor test robot.
 *     It is designed to be adaptable to standard 4-wheel competition robots
 *  by replacing this 3-wheel DriveTrain.java file ("Class") with a similar
 *  4-wheel version.  
 */

/*
 *     THE FOLLOWING WARNINGS APPLY TO ALL FILES.
 *     WARNING. The statement "package ..." is at the top of the file, separated
 *  by many lines of comments from the "import ..." statements below, because
 *  Eclipse COLLAPSES multiple lines of comments at tops of files and HIDES all
 *  but the first line.
 *     WARNING. Similarly, Eclipse COLLAPSES multiple lines of "import"
 *  statements, HIDING all but the first line. The "import" statement on the
 *  next line IS FOLLOWED BY SEVERAL MORE that Eclipse hides UNTIL YOU OPEN
 *  THEM. AT LEAST ONCE, EXPAND ALL COMMENTS that Eclipse has collapsed and
 *  make sure you're not missing anything important.
 *     [This WARNING appears only in this file. It applies to all files.]
 */

import org.usfirst.frc.team1729.robot.commands.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *     This Class includes all access to Joysticks and their buttons.
 */
/**
 *      This class is the glue that binds the controls on the physical operator
 *  interface to the commands and command groups that allow control of the
 *  robot. [WPI comment]
 */     

public class OI
	{
			// <---------------- CHANGE AND CALIBRATE HERE
			// to print to SmartDashboard, change this to nonzero
			// to STOP printing to SmartDashboard, change this to zero
	static final int DATA_TO_SMART_DASHBOARD = 1;
		
	
			// PROGRAM INTERNALS start here - please do not disturb	

	
			// [Long set of WPI comments for BUTTONS]
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


	
			// The word "new" means that a new object is to be created.
	
			// WARNING. The first Joystick is numbered 0, not 1.
			// [Starting at 0 is the usual Java convention.]
	Joystick driver = new Joystick(0);
	// Joystick operator = new Joystick(1);      // for a second joystick
	
	
	public OI()
		{
			// WARNING. First Button on each Joystick is numbered 1, not 0.
			// [The joystick manufacturer has labeled the buttons,
			// and doesn't follow the usual Java convention.]
		Button DBTN1 = new JoystickButton(driver, 1);
		Button DBTN11 = new JoystickButton(driver, 11);
		// Button DBTN12 = new JoystickButton(driver, 12);
		// Button OBTN1 = new JoystickButton(operator, 1);  // for 1st button of 2nd joystick

		DBTN11.whileHeld(new AutoSimple());
		}
	
	
			//    Here, these are called getDriverX(), etc. because later
			// one may want to add getOperatorX(), etc. using a 2nd joystick 
	public double getDriverX()
		{
		double x = driver.getX();
			//    Unlike some other computer languages, Java allows variables
			// to be declared in middle of a program when first used, like this,
			// and to be given an initial value at the same time
		
		if (DATA_TO_SMART_DASHBOARD != 0)
			{  SmartDashboard.putNumber("get driver x", x);  }
		return x;
		}
	
	
	public double getDriverY()
		{
			// WARNING. EXPLANATION OF THE -1 factor in the next line of code:
			//    The logical definition of y for robots is:
			//       - plus is forward, minus is back
			//    All software in this package is written for this definition,
			// except the next line of code.
			//    The -1 in the next line of code appears because the makers
			// of joysticks have used the (from our point of view) backwards
			// definition of +1 toward user, -1 away - which is backward from
			// what we want for robotics
			//    So change y to -y here and never think about it anywhere else.
		double y = driver.getY() * -1;
		
		if (DATA_TO_SMART_DASHBOARD != 0)
			{  SmartDashboard.putNumber("get driver y", y);  }
		return y;
		}


	public double getDriverZ()
		{
		double z = driver.getZ();
		
		if (DATA_TO_SMART_DASHBOARD != 0)
			{  SmartDashboard.putNumber("get driver z", z);  }
		return z;
		}
	}
