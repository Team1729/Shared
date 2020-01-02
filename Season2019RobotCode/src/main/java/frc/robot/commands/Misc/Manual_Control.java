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

public class Manual_Control extends Command {
  public Manual_Control() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.lift.Manual(Robot.oi.OpY());
    //Robot.manipulator.Manual(Robot.oi.OpX());
    Robot.lift.ManualHab2(Robot.oi.OpX());

    /*if(Robot.pneumatics.isHab()){
      Robot.lift.ManualHab2(Robot.oi.OpX());
      Robot.manipulator.Manual(0);
    }else{
      Robot.manipulator.Manual(Robot.oi.OpX());
      Robot.lift.ManualHab2(0);
    }*/


    SmartDashboard.putBoolean("Manual", true);
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
    //Robot.manipulator.Manual(0);
    SmartDashboard.putBoolean("Manual", false);
    Robot.lift.ManualHab2(0);

    
  }
}
