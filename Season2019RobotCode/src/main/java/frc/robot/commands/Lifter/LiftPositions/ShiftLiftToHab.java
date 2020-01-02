/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Lifter.LiftPositions;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class ShiftLiftToHab extends Command {
  public ShiftLiftToHab(String gear) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);]
    Gear = gear;
  }
  String Gear;
  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //pidswitch.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Gear == "Lift"){
      Robot.pneumatics.ShiftLift("Ver");
      //SmartDashboard.putString("Lift", "Ver");
    }
    else if(Gear == "Hab"){
      Robot.pneumatics.ShiftLift("Hab");
      Robot.pneumatics.HabLock("Unlock");
      //SmartDashboard.putString("Lift", "Hab");
    }
    SmartDashboard.putString("Gear", Gear);
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
