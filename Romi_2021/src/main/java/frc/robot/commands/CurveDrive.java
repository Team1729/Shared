// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class CurveDrive extends CommandBase {
  /** Creates a new CurveDrive. */
  double spd, rotate, dist;
  boolean isQuick;
  public CurveDrive(double Speed, double Distance, double Rotation, boolean isQuickTurn) {
    // Use addRequirements() here to declare subsystem dependencies.
      spd = Speed;
      rotate = Rotation;
      dist = Distance;
      isQuick = isQuickTurn;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.Drivetrain.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.Drivetrain.m_diffDrive.curvatureDrive(spd, rotate, isQuick);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.Drivetrain.m_diffDrive.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double L = Robot.Drivetrain.getEncoder("L");
    double R = Robot.Drivetrain.getEncoder("L");

    double average = (L + R)/2;
    if(average >= dist) {
      return true;
    }else{
      return false;
    }
  }
}
