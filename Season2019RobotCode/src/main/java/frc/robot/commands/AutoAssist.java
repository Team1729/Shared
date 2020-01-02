/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AutoAssist extends Command {
  public AutoAssist() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    DrY = Robot.oi.ArcadeY();
    timer = 0;
  }
  Command green = new Lights("Green");
  Command alliance = new Lights("Alliance");
  // Variables //////////////////////////
  double X;
  double offset;
  double smooth = 70;
  double speed;
  float timer;
  float targettime;
  double DrY;

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

      X = Robot.sensors.getLimeLight("PosX");
      double width = Robot.sensors.getLimeLight("Size");
      green.start();
      Robot.sensors.putLimeLight("ledmode", 3);

      if(Robot.sensors.getLimeLight("target") > 0){
        if(width < 20){
         //speed = (100 - (width/2.37)) / 180;
        }else{
          speed = 0;
        }  
        speed = Robot.oi.ArcadeY();
        if(X < 0){
          offset = (X * -1) / smooth;
          Robot.drivetrain.Move(speed - offset, speed + offset);
        }
        else if(X > 0){
          offset = X / smooth;
          Robot.drivetrain.Move(speed + offset, speed - offset);
        }
        else if(X == 0){
          Robot.drivetrain.Move(speed, speed);
        }
      }
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(Robot.sensors.getLimeLight("target") == 0){
      //return true;
    }
    else if(Robot.sensors.getLimeLight("Width") >= 230){
      //return true;
    }
    //else{
      return false;
    //}
   
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.sensors.putLimeLight("ledmode", 0);
    alliance.start();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.sensors.putLimeLight("ledmode", 0);
    alliance.start();
  }
}
