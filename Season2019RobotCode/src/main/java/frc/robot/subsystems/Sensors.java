
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.*;

import com.kauailabs.navx.frc.AHRS;

/**
 *
 */
public class Sensors extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	AHRS Nav = new AHRS(SPI.Port.kMXP);
	boolean red;
	boolean blue;
	boolean green;
	Relay Blue = new Relay(0);
	Relay Red = new Relay(1);
	Relay Green = new Relay(2);
	Spark Blinkin = new Spark(1);
	NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new Toggles());
		// setDefaultCommand(new Operator());
	}

	public Alliance allianceColor() {
		 return DriverStation.getInstance().getAlliance();
	}

    public void Encoder(){
    	//double EncR = Right.getValue();
    	//double EncL = Left.getValue();    	
    	//////SmartDashboard.putNumber("EncR", EncR);
		//////SmartDashboard.putNumber("EncL", EncL);
		
	}
	
	public void Blinkin(int val){
		Blinkin.set(val);
	}
    
    public double NavHeading(){
    	double NavX = Nav.getFusedHeading();
    	SmartDashboard.putNumber("NavX Gyro", NavX);
    	return NavX;
	}

	public void LED(int red, int green, int blue){
		if(red == 1){
			Red.set(Value.kOn);
		}
		else if(red == 0){
			Red.set(Value.kOff);
		}

		if(blue == 1){
			Blue.set(Value.kOn);
		}
		else if(blue == 0){
			Blue.set(Value.kOff);
		}

		if(green == 1){
			Green.set(Value.kOn);
		}
		else if(green == 0){
			Green.set(Value.kOff);
		}
	  }

	/**
	 * puts a value to the limelight table
	 * @param ledmode sets state of led(0 = off, 1 = on, 3 = force on)
	 * @param pipeline sets the pipeline for the camera to use
	 * 
	 */
	public void putLimeLight(String value, double num) {
		if(value == "ledmode"){
			table.getEntry("ledMode").setNumber(num);
		}
		else if(value == "pipeline"){
			table.getEntry("pipeline").setNumber(num);
			
		}
	}
	
	/**
	 * @param PosX gets the X position of tracked object
	 * @param PosY gets the Y position of tracked object
	 * @param Size gets the % of how much the object takes up in screen space
	 * @param Target returns 1 or 0, 1 means it has a valid target, 0 means it dosn't
	 */
	public double getLimeLight(String Value) {

		if(Value == "PosY"){
			return table.getEntry("ty").getDouble(0);
		}
		else if(Value == "PosX"){
			return table.getEntry("tx").getDouble(0);
		}
		else if(Value == "Size"){
			return 	table.getEntry("ta").getDouble(0);
		}
		else if(Value == "Width"){
			return table.getEntry("thor").getDouble(0);
		}
		else{
			return 	table.getEntry("tv").getDouble(0);
		}
	}
		
	
	public void test(){

NetworkTableEntry tx = table.getEntry("tx");
NetworkTableEntry ty = table.getEntry("ty");
NetworkTableEntry ta = table.getEntry("ta");

//read values periodically
double x = tx.getDouble(0.0);
double y = ty.getDouble(0.0);
double area = ta.getDouble(0.0);

//post to smart dashboard periodically
SmartDashboard.putNumber("LimelightX", x);
SmartDashboard.putNumber("LimelightY", y);
SmartDashboard.putNumber("LimelightArea", area);
	}
	// ENCODER CODE ======================================================
	
	   
	   	
	   
	   
}

