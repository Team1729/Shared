/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Lifter.LiftPositions;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Hab2Control extends Command {
  public Hab2Control(int setpoint) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    setPoint = setpoint;
  }
  int setPoint;
  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }
  int Encoder;
  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Encoder = Robot.lift.Hab2Encoder();

    Robot.lift.Hab2Lift(setPoint);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
  if((setPoint > (Encoder - 50)) && (setPoint < (Encoder + 50))){
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
