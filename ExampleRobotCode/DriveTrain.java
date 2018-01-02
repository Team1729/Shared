/* DriveTrain.java   ---   Revision notes for entire TinyTestBot package are at OI.java */ 

package org.usfirst.frc.team1729.robot.subsystems;

import org.usfirst.frc.team1729.robot.Robot;
import org.usfirst.frc.team1729.robot.commands.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.CANTalon;

/**
 *     This Subsystem should contain all drive-specific code.
 */

public class DriveTrain extends Subsystem
	{
			//    Put methods for controlling this subsystem
			// here. Call these from Commands. [WPI comment]

	
			// <---------------- CHANGE AND CALIBRATE HERE
			// to print to smart dashboard, change this to nonzero
			// to not print to smart dashboard, change this to zero
	static final int DATA_TO_SMART_DASHBOARD = 1;

			//  <--------------- CANTalons are numbered here
			//    See definition of motor directions below
	private CANTalon FL = new CANTalon(1);    // Front Left motor
	private CANTalon FR = new CANTalon(3);    // Front Right
	private CANTalon BMid = new CANTalon(2);  // Back Middle

			//	<--------------------------- calibrate teleopDrive here
	
			// joystick adjustment factor
			// our test robot turns much too fast (hair trigger) without this
	static final double SET_BOOST_DRIVER_Z = 0.5;
	
			// motor balance adjustment factors
			//    All motors are at least slightly different in "strength"
			//    These factors allow the software to adjust for the
			// differences in "strength" between motors
			//    We have usually set all SET_DRIVE_..._FACTOR's to 1.0
			// for all motors
			//    After a spring 2017 motor balance adjustment of the test
			// robot, users thought the test robot drove better with these
			// FACTOR's than with (1.00, 1.00, 1.00), so these values are
			// used here
	static final double SET_DRIVE_FL_FACTOR = 1.00;
	static final double SET_DRIVE_FR_FACTOR = 0.85;
	static final double SET_DRIVE_BMid_FACTOR = 0.72;
	
			//	<--------------------------- calibrate autonMove here

			//    Set the motor magnitude value that results in the robot
			// quickly reaching a constant speed of about 1 foot per second
			// (or any other desired speed). 
			//    Of course, there is no motor magnitude value that always
			// results in the robot moving at an exact constant speed. Motor
			// magnitude is a force, and force divided by mass equals
			// acceleration, not velocity.
			//    However, when a constant motor force is applied to our
			// test robot, the test robot quickly (in about 1/4 to 3/4 second) 
			// reaches a close-to-constant speed at which the motor force
 			// is balanced by all forces slowing down the robot. 
			// 
			//    The number 0.30 appears to be good for NONCARPET.
			//    The number 0.35 appears to be good for CARPET.
	static final double SET_MAG_FOR_1FPS   = 0.30;
	
	
			// PROGRAM INTERNALS start here - do not disturb	

	
			// constants
	static final double DEGREES_TO_RADIANS = Math.PI/180;
	static final double RADIANS_TO_DEGREES = 180/Math.PI;
	
	
	public void initDefaultCommand()
		{
			// Set the default command for a subsystem here. [WPI comment]
			//setDefaultCommand(new MySpecialCommand());
		
			//    This sets the default Command DriverDrive() that
			// reads the joystick and moves the robot accordingly
		setDefaultCommand(new DriverDrive());
		}
	
	
	/*
	 * ANGLES for robot ARE DEFINED AS
	 * 			COMPASS ANGLES in DEGREES
	 * DIRECTIONS of x and y ARE DEFINED AS 
	 * 			positive x is to the right
	 *			positive y is forward
	 * AND COMPASS ANGLES ARE DEFINED AS 
	 * 			  0 is forward
	 * 			 90 is to the right
	 * 			180 is backward
	 * 			270 is to the left 
	 *   
	 *  
	 *		.	.	.	.	+y 
	 *		.	.	y>0	.	|	.	y>0 
	 *		.	.	x<0	.	0	.	x>0 
	 *		.	.	.	.	| 
	 *		-x	---	270	---	.	---	 90	---	+x 
	 *		.	.	.	.	| 
	 *		.	.	y<0	.	180	.	y<0
	 *		.	.	x<0	.	|	.	x>0
	 *		.	.	.	.	-y
	 *
	 * OUR THREE WHEEL TEST ROBOT HAS 3 MOTORS
	 * THEY POINT IN COMPASS DIRECTIONS
	 * 			 30 [Front Left]
	 * 			150 [Front Right]
	 * 			AND 270 [Back Middle]
	 * MOTOR VALUES ARE DEFINED AS
	 * 			positive motor values are clockwise
	 * 				[Front Left:   + is forward and right]
	 * 				[Front Right:  + is backward and right]
	 * 				[Back Middle:  + is to left]
	 *
	 */
	
			//    This "method" driverDrive() is called by the Command DriverDrive().
			// Above, the "method" initDefaultCommand() made the Command DriverDrive()
			// the default command of the DriveTrain Subsystem.
			//
	public void driverDrive()
		{
			// read the current joystick values
		double x = Robot.oi.getDriverX();
		double y = Robot.oi.getDriverY();
		double z = Robot.oi.getDriverZ();
		
			// adjust joystick values for safety, could do more adjustments
		z *=  SET_BOOST_DRIVER_Z;
		
		if (DATA_TO_SMART_DASHBOARD != 0)
			{
			SmartDashboard.putNumber("adj driver x", x);
			SmartDashboard.putNumber("adj driver y", y);
			SmartDashboard.putNumber("adj driver z", z);
			}
		
			// calculate magnitude of x and y combined
		double mag = Math.sqrt((x * x) + (y * y));
		
			// calculate compass angle of x and y combined, in degrees
		double angle = Math.atan2(x, y) * RADIANS_TO_DEGREES;
		
		calcMotors(mag, angle, z);
			// you could combine the last 3 statements
			// into the single statement
			//       teleopDrive(
			//             Math.sqrt((x * x) + (y * y)),
			//             Math.atan2(x, y) * RADIANS_TO_DEGREES,
			//             z);
			// but it would likely not be as clear what was happening
		}


/**
* @param mag0     = magnitude of robot motion, in motor units
* @param theta0   = angle of robot motion, in degrees [0 is forward]
* @param turnMag0 = magnitude of robot turn motion, in motor units [plus is clockwise]
*/
	private void calcMotors(double mag0, double theta0, double turnMag0)
		{		
		if (DATA_TO_SMART_DASHBOARD != 0)
			{
			SmartDashboard.putNumber("mag", mag0);
			SmartDashboard.putNumber("theta", theta0);
			SmartDashboard.putNumber("turn mag", turnMag0);
			}		
		
		while (theta0 < 0)
			{  theta0 += 360;  }    // shouldn't be necessary
		while (theta0 > 360)
			{  theta0 -= 360;  }    // shouldn't be necessary

			//    Our motors are pointed at compass angles 30, 150, 270
			//    For all 3 motors, positive (+) is clockwise (increasing compass angle)
			//
			//    Please don't be scared by the trigonometry:
			//    We want the robot to move in the direction "theta0" (in compass angle
			// degrees)
			//    When theta0 is a different direction than the direction a particular
			// motor points, we need to calculate what fraction of the robot's total
			// motion should be in that motor's direction (and whether positive or
			// negative)
			//    So we calculate factorA, ..., one factor for each motor direction
			//    When theta0 is exactly in a particular motor's direction,
			// (theta0 - motorAngle) is 0 degrees, and the "factor" should be 1.00.
			//    When theta0 is exactly opposite to a particular motor's direction,
			// (theta0 - motorAngle) is 180 degrees, and the "factor" should be  -1.00.
		 	//    When theta0 is exactly perpendicular to a particular motor's direction,
			// (theta0 - motorAngle) is 90 or 270 degrees, and the "factor" should be 0.00.
			//    "Math.cos" [short for "cosine"] is a function that comes up often in
			// mathematics that gives these values for 0, 90, 180, and 270 degrees. Its
			// values for other angles will result in "factors" factorA, ... that
			// mathematically add up to the desired motor direction and magnitude, for 
			// these three motor directions that are equally spaced around a circle.
			//    A few values of angle and cosine:
			//		Angle(degrees)	Cosine
			//		  0				 1.00
			//		 15				 0.966
			//		 30				 0.866
			//		 45				 0.71
			//		 60				 0.50
			//		 75				 0.26
			//		 90				 0.00
			//		180				-1.00    
			//
		double factorA = Math.cos((theta0 -  30) * DEGREES_TO_RADIANS);
		double factorB = Math.cos((theta0 - 150) * DEGREES_TO_RADIANS);
		double factorC = Math.cos((theta0 - 270) * DEGREES_TO_RADIANS);
		
			//    Calculate each motor value by:
			//          - Multiplying that motor's "factor" by the
			//                total desired motor magnitude "mag0"
			//          - then Adding to all 3 motors the desired motor
			//                magnitude for turn motion
		double FrontLeft  = (factorA * mag0) + turnMag0;
		double FrontRight = (factorB * mag0) + turnMag0;
		double BackMid    = (factorC * mag0) + turnMag0;

			//    Adjust each motor value for that motor's strength compared
			// to other motors (all motors are at least slightly different)
		FrontLeft   *=  SET_DRIVE_FL_FACTOR;
		FrontRight  *=  SET_DRIVE_FR_FACTOR;
		BackMid     *=  SET_DRIVE_BMid_FACTOR;
			//    So far, balancing the motors by multiplying each motor by
			// its own constant gives enough improvement to be worth doing.
			//    When more thorough motor balance adjustments have been tested
			// on more robots, it will likely be found that a more complicated
			// mathematical adjustment gives better results.
		
		set3Motors(FrontLeft, FrontRight, BackMid);
		}


	public void driveStop()
		{
		set3Motors(0, 0, 0);
		}


/**
* @param FL0 = motor number for Front Left motor  (compass angle 30deg, + is clockwise) (forward is +)
* @param FR0 = motor number for Front Right motor (compass angle 150deg, + is clockwise) (backwd is +)
* @param BMid0 = motor number for Back Mid motor  (compass angle 270deg, + is clockwise) (left is +)
*/
	private void set3Motors(double FL0, double FR0, double BMid0)
		{
			//    This is the only method that sets the motor values
			// so can test here in one place all values to be sent to the motors
			// and limit them to the range (-1, +1)
			//    In theory, these motors do this limiting themselves.
			// But it's usually a good idea to check things like this
			// yourself and not rely on someone else to protect the
			// motors from malfunction or serious harm
			//
			//    The next 2 lines could be replaced with
			//   		FL0 = Math.min(1.0, Math.max(FL0, -1.0));
			// but it would probably not be as clear what was happening
		if (FL0   >  1.0)
			{  FL0 =   1.0;  }
		if (FL0   < -1.0)
			{  FL0 =  -1.0;  }
		FL.set(FL0);

		if (FR0   >  1.0)
			{  FR0 =   1.0;  }
		if (FR0   < -1.0)
			{  FR0 =  -1.0;  }
		FR.set(FR0);

		if (BMid0 >  1.0)
			{  BMid0 =   1.0;  }
		if (BMid0 < -1.0)
			{  BMid0 =  -1.0;  }
		BMid.set(BMid0);

		if (DATA_TO_SMART_DASHBOARD != 0)
			{
			SmartDashboard.putNumber("FL", FL0);
			SmartDashboard.putNumber("FR", FR0);
			SmartDashboard.putNumber("BMid", BMid0);
			}		
		}	
	
	
	/// *** AUTONOMOUS DRIVE TRAIN methods start here ***
	
	/**
	 * @param moveAngle0 =  direction to move the robot, in degrees [0 is where robot points]
	 */
	public void moveSimple(double moveAngle0)
		{
		if (DATA_TO_SMART_DASHBOARD != 0)
			{
			SmartDashboard.putNumber("auton move angle", moveAngle0);
			}
	
		double mag  = SET_MAG_FOR_1FPS;  	// mag is in motor units
		double theta = moveAngle0;       	// angle to move robot
		double turnMag = 0;              	// turnMag is in motor units
		
		calcMotors(mag, theta, turnMag);
		}
	}
