/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Misc;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.commands.Manipulator.Commands.*;

public class WristToggle extends Command {
  public WristToggle() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    //requires(Robot.manipulator);
  }
  double topHat;
  boolean swap;
  boolean extend;
  int move;
  int test;
  int cap;

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    move = 0;
    test = 0;
    cap = 3;
    swap = true;
  }
  
  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    topHat = Robot.oi.OpHat();
    extend = Robot.pneumatics.isExtended();
    if(topHat == 0 && swap){
      move -= 1;
      swap = false;
    }

    if(topHat == 180 && swap){
      move += 1;
      swap = false;
    }

    if(move < 0){
      move = 0;
    }

    if(move > cap){
      move = cap;
    }

    SmartDashboard.putNumber("Angle", move);

    switch(move){

      case 0:	default:
      test = 0;
        new WristPositions(0).start();
      break;
      case 1:
        test = 1;
        new WristPositions(45).start();
        break;
      case 2:
        test = 2;
        new WristPositions(90).start();
        break;
      case 3:
        test = 3;
        new WristPositions(135).start();
        break;
      case 4:
        test = 4; 
        new WristPositions(180).start();
        break;
        
      }

      SmartDashboard.putNumber("Manipulator", test);

    if(topHat == -1){
      swap = true;
    }
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
