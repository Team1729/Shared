
package org.usfirst.frc.team1729.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1729.robot.commands.*;

import org.usfirst.frc.team1729.robot.subsystems.*;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final int ThisRobot = 1; // 1 = Prototype, 2 = Fezzik 
	public static int ResetLeftEcoder = 0; 
	public static int ResetRightEcoder = 0; 
	public static char OurSwitch = 'N';
	
	public char GameData;
	public char GameData1;
	double LED;
	double color;

	
    public static OI oi;
	public static DriveTrain drivetrain;
	public static Pneumatics pneumatics;
	public static Sensors sensors;
	public static BoxHandler boxhandler;
	public static Servos servos;
	public static AutoMove automove;
	public static Wrist wrist;
	public static Lifter lifter;

	double Timer;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		drivetrain = new DriveTrain();
		sensors = new Sensors();
		automove = new AutoMove();
		
		GameData = 'Z';
		GameData1 = 'Z';
		switch(Robot.ThisRobot){
			case 1:
				boxhandler = new BoxHandler();
				pneumatics = new Pneumatics();
				wrist = new Wrist();
				lifter = new Lifter();
				Robot.pneumatics.Grab();
				Compressor c = new Compressor(0);
				c.setClosedLoopControl(true);
				break;
			case 2:
				servos = new Servos();
				break;
		}
		
		//chooser.addDefault("Auto Demo", new Auto());
		chooser.addDefault("AutoForwards", new AutoForward());
		chooser.addObject("AutoCenter", new AutoCenter());
		chooser.addObject("AutoRight", new AutoRight());
		chooser.addObject("AutoLeft", new AutoLeft());
		chooser.addObject("TestCommand", new TestAnythingHere());
		chooser.addObject("AutoLeft V2", new SmartAutoLeft_V2());
		chooser.addObject("AutoRight V2", new SmartAutoRight_V2());


		

		SmartDashboard.putData("Auto mode", chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		Robot.sensors.LedState(1);
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
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		Timer = 0;
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
		
		
		
				
		
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		GameData = DriverStation.getInstance().getGameSpecificMessage().charAt(0);
		GameData1 = DriverStation.getInstance().getGameSpecificMessage().charAt(1);

		Scheduler.getInstance().run();
		
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
