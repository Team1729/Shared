/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Manipulator.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class WristPositions extends Command {
  public WristPositions(int in){
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.manipulator);
    degree = in;
  }

  int degree;

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(degree == 0){
      Robot.manipulator.Wrist(250);
    }
    else if(degree == 45){
      Robot.manipulator.Wrist(600);
    }
    else if(degree == 90){
      Robot.manipulator.Wrist(1170);
    }
    else if(degree == 135){
      Robot.manipulator.Wrist(1356);
    }
    else if(degree == 180){
      Robot.manipulator.Wrist(1600);
    }else{
      Robot.manipulator.Wrist(250);
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
