/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Drive.DriveCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Move extends Command {
  /**
   * 
   * @param L Left motor speed
   * @param R Right motor speed
   * @param time time
   */
  public Move(double L, double R, double time) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    left = L;
    right = R;
    timer = time;
  }
  double timer, left, right, time;

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    time = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    time += 0.02;
    Robot.drivetrain.Move(left, right);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(time >= timer){
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
