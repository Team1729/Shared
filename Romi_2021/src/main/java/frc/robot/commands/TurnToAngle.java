// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class TurnToAngle extends CommandBase {
  /** Creates a new TurnToAngle. */
  double spd, ang;
  double offset = 25;
  public TurnToAngle(double Speed, double Angle) {
    // Use addRequirements() here to declare subsystem dependencies.
    spd = Speed;
    ang = Angle;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.RomiGyro.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(ang > 0){
      Robot.Drivetrain.m_diffDrive.arcadeDrive(0, spd/1.2);
    }
    else if(ang < 0){
      Robot.Drivetrain.m_diffDrive.arcadeDrive(0, -spd/1.2);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.Drivetrain.m_diffDrive.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double cAng = Robot.RomiGyro.getAngleZ();
    if(ang > 0){
      if(cAng >= ang - offset){
        return true;
      }else{
        return false;
      }
    }
    else if(ang < 0){
      if(cAng <= ang + offset){
        return true;
      }else{
        return false;
      }
    }else{
      return false;
    }
  }
}
