// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class SimpleMove extends CommandBase {
  /** Creates a new SimpleMove. */
  double L, R, dist;
  public SimpleMove(double Lspeed, double Rspeed, double distance) {
    // Use addRequirements() here to declare subsystem dependencies.
    L = Lspeed;
    R = Rspeed;
    dist = distance;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.Drivetrain.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.Drivetrain.m_diffDrive.tankDrive(L, R);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double EncL = Robot.Drivetrain.getLeftDistanceInch();
    double EncR = Robot.Drivetrain.getRightDistanceInch();
    double avg = (EncL + EncR)/2;
    if(avg >= dist){
      return true;
    }else{
      return false;
    }
  }
}
