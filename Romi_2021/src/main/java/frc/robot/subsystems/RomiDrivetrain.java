// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;


public class RomiDrivetrain extends SubsystemBase {
  private static final double kCountsPerRevolution = 1440.0;
  private static final double kWheelDiameterInch = 2.75591; // 70 mm

  public Joystick DriveJoy = new Joystick(0);

  // The Romi has the left and right motors set to
  // PWM channels 0 and 1 respectively
  private Spark m_leftMotor = new Spark(0);
  private Spark m_rightMotor = new Spark(1);

  // The Romi has onboard encoders that are hardcoded
  // to use DIO pins 4/5 and 6/7 for the left and right
  private final Encoder m_leftEncoder = new Encoder(4, 5);
  private final Encoder m_rightEncoder = new Encoder(6, 7);

  // Set up the differential drive controller
  public DifferentialDrive m_diffDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);
  
  /** Creates a new RomiDrivetrain. */
  public RomiDrivetrain() {
    // Use inches as unit for encoder distances
    m_leftEncoder.setDistancePerPulse((Math.PI * kWheelDiameterInch) / kCountsPerRevolution);
    m_rightEncoder.setDistancePerPulse((Math.PI * kWheelDiameterInch) / kCountsPerRevolution);
    //resetEncoders();
  }

  public double getEncoder(String side){

    if(side == "L"){
      SmartDashboard.putNumber("Left Encoder", m_leftEncoder.getDistance());
      return Math.abs(m_leftEncoder.getDistance());
    }
    else if(side == "R"){      
      SmartDashboard.putNumber("Right Encoder", m_rightEncoder.getDistance());
      return Math.abs(m_rightEncoder.getDistance());
    }else{
      return 9999;
    }
  }
  public void SetMotors(double L, double R){
    m_leftMotor.set(L);
    m_rightMotor.set(-R);
  }
  public void Arcade(double ArcadeX, double ArcadeY){
		double V;
		double W;
		double L;
		double R;
		double RV = 1;
		double LV = 1;
		ArcadeY *= 100;
		ArcadeX *= 100;
		
		V = (100 - Math.abs(ArcadeX)) * (ArcadeY/100) + ArcadeY;
		W = (100 - Math.abs(ArcadeY)) * (ArcadeX/100) + ArcadeX;
		
		R = ((V + W) / 2) / 100;
		L = ((V - W) / 2) / 100;

		R *= RV;
		L *= LV;
    m_diffDrive.tankDrive(L, R);
  }

  public void resetEncoders() {
    m_leftEncoder.reset();
    m_rightEncoder.reset();
  }

  public double getLeftDistanceInch() {
    return m_leftEncoder.getDistance();
  }

  public double getRightDistanceInch() {
    return m_rightEncoder.getDistance();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
