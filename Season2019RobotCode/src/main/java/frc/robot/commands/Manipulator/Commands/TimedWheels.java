/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Manipulator.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TimedWheels extends Command {
  /**
   * 
   * @param wantedtime time to go for
   * @param direction direction of wheels, (In or Out) in = intake, out = release
   */
  public TimedWheels(double wantedtime, String direction) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    time = wantedtime;
    dir = direction;
  }

  double timer, time;
  String dir;
  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    timer = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    timer += 0.02;
    if(dir == "In"){
    Robot.manipulator.Wheels(-0.6);
    }
    else if(dir == "Out"){
      Robot.manipulator.Wheels(0.6);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(timer >= time){
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
