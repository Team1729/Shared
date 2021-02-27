// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class ForwardDistance extends CommandBase {
  /** Creates a new ForwardDistance. */
  double dist;
  double spd;
  public ForwardDistance(double speed, double distance) {
    // Use addRequirements() here to declare subsystem dependencies.
    dist = distance;
    spd = speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
   Robot.Drivetrain.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.Drivetrain.m_diffDrive.tankDrive(spd, spd + 0.018);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      Robot.Drivetrain.m_diffDrive.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double encoderL = Robot.Drivetrain.getEncoder("L");
    if(encoderL >= dist) {
      return true;
    }else{
      return false;
    }
  }
}
