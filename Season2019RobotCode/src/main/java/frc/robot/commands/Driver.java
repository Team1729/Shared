/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;


public class Driver extends Command {
  public Driver() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drivetrain);
  }

  String a;

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    DriverStation.Alliance colour = DriverStation.getInstance().getAlliance();
    
    if(colour == Alliance.Red){
      SmartDashboard.putString("Alliance Color", "Red");
      new Lights("Red").start();;
    }
    else if(colour == Alliance.Blue){
      SmartDashboard.putString("Alliance Color", "Blue");
      new Lights("Blue").start();;
      }else{
        new Lights("White").start();
    }
    a = "null";
  }

  // Called repeatedly when this Command is scheduled to run
  

  // Variables/////////////////////////////////////////

  double ArcadeX;
  double ArcadeY;
  

  @Override
  protected void execute() {

    RevTest.start();

    // Driver Axis ===============================================
    ArcadeX = Robot.oi.ArcadeX();
    ArcadeY = Robot.oi.ArcadeY();
    boolean Btn11 = Robot.oi.ArcadeBTN(11);
    boolean Btn12 = Robot.oi.ArcadeBTN(12);
    boolean Btn9 = Robot.oi.ArcadeBTN(9);
    boolean Btn10 = Robot.oi.ArcadeBTN(10);
    boolean Btn3 = Robot.oi.ArcadeBTN(3);
    boolean Btn4 = Robot.oi.ArcadeBTN(4);
    boolean Btn1 = Robot.oi.ArcadeBTN(1);
    boolean Btn7 = Robot.oi.ArcadeBTN(7);
    boolean Btn8 = Robot.oi.ArcadeBTN(8);
    boolean Btn6 = Robot.oi.ArcadeBTN(6);

    Robot.drivetrain.Arcade(ArcadeY, ArcadeX);
    Robot.sensors.putLimeLight("pipeline", 3);
    /*if(Btn9 == true){
      Robot.sensors.putLimeLight("pipeline", 1);
    }
    if(Btn10 == true){
      Robot.sensors.putLimeLight("pipeline", 0);
    }*/
  

   /* if(Btn3 == true){
      Robot.pneumatics.ShiftDrive("Low");
    }
    else if(Btn4 == true){
      Robot.pneumatics.ShiftDrive("High");
    }*/
   
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
