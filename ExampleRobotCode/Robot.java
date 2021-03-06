/* Robot.java   ---   Revision notes for entire TinyTestBot package are at OI.java */ 

package org.usfirst.frc.team1729.robot;

import org.usfirst.frc.team1729.robot.commands.*;
import org.usfirst.frc.team1729.robot.subsystems.*;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *    This Class is where the operating system calls our robot code.
 *    This Class connects our robot code to the operating system's Scheduler
 * and Sendable Chooser. 
 */
/** 
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory. [WPI comment]
 */

public class Robot extends IterativeRobot
	{
	
			// PROGRAM INTERNALS start here - please do not disturb	

	
			//    These two variables are defined here so that they are
			// available to all "methods" in this class.
			//    The names "oi" and "driveTrain" were kept short for
			// convenience. It might be clearer that they create new
			// objects for our use if they were named "ourOI" and
			// "ourDriveTrain".
	public static OI oi;
	public static DriveTrain driveTrain;
	
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code. [WPI]
	 */
	@Override
	public void robotInit()
		{
				//    These two statements are in robotInit() because they
				// create new objects for our use, and we want to make sure
				// they only run once.
		oi = new OI();
		driveTrain = new DriveTrain();

		chooser.addDefault("AutoSimple", new AutoSimple());
		// chooser.addObject("My Auto", new MyAutoCommand()); [WPI]
		SmartDashboard.putData("Auto mode", chooser);
		}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled. [WPI comment]
	 */
	@Override
	public void disabledInit()
		{
		}

	
	@Override
	public void disabledPeriodic()
		{
		Scheduler.getInstance().run();
		}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands. [WPI comment]
	 */
	@Override
	public void autonomousInit()
		{
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }  [WPI comment]
		 */

			// schedule the autonomous command (example)  [WPI comment]
		if (autonomousCommand != null)
			autonomousCommand.start();
		}

	/**
	 * This function is called periodically during autonomous [WPI comment]
	 */
	@Override
	public void autonomousPeriodic()
		{
		Scheduler.getInstance().run();
		}

	
	@Override
	public void teleopInit()
		{
			// This makes sure that the autonomous stops running when
			// teleop starts running. If you want the autonomous to
			// continue until interrupted by another command, remove
			// this line or comment it out. [WPI comment]
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		}

	
	/**
	 * This function is called periodically during operator control [WPI comment]
	 */
	@Override
	public void teleopPeriodic()
		{
		Scheduler.getInstance().run();
		}

	
	/**
	 * This function is called periodically during test mode  [WPI comment]
	 */
	@Override
	public void testPeriodic()
		{
		LiveWindow.run();
		}
	}
