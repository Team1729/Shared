/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class Lights extends Command {
  public Lights(String color_in) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    color = color_in;
  }

  public Spark Rev = new Spark(1);
  String color;
  boolean red;
  boolean blue;
  boolean green;

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    DriverStation.Alliance colour = DriverStation.getInstance().getAlliance();

    	if(color == "Red"){
			Robot.sensors.LED(1, 0, 0);
		}
		else if(color == "Blue"){
			Robot.sensors.LED(0, 0, 1);
		}
		else if(color == "Green"){
			Robot.sensors.LED(0, 1, 0);
		}
		else if(color == "Purple"){
			Robot.sensors.LED(1, 0, 1);
		}
		else if(color == "Cyan"){
			Robot.sensors.LED(0, 1, 1);
		}
		else if(color == "Yellow"){
			Robot.sensors.LED(1, 1, 0);
		}
		else if(color == "White"){
			Robot.sensors.LED(1, 1, 1);
		}else{
			Robot.sensors.LED(0, 0, 0);
    }
    
    if(color == "Alliance"){
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
    }

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
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
