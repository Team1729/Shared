/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Misc;

import edu.wpi.first.wpilibj.command.Command;

public class DelayTimer extends Command {
  public DelayTimer(double time) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    Time = time;
  }
  double Time;
  double Timer;
  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Timer = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Timer += 0.02;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(Timer >= Time){
      return true;
    }else{
      return false;
    }
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
