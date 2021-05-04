// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import javax.naming.directory.DirContext;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;

public class PickUp extends CommandBase {
  /** Creates a new MoveServo. */
  int angle;
  private final Drivetrain m_drive = RobotContainer.m_drivetrain;
  Timer Etime = new Timer();
  double time;

  public PickUp(double time) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.time = time;
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Etime.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Etime.start();
    //m_drive.m_diffDrive.tankDrive(0.5, 0.5);
    m_drive.setServo(167);
    SmartDashboard.putNumber("Timer", Etime.get());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Etime.stop();
    m_drive.setServo(85);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(Etime.get() >= time){
      return true;
    }else{
    return false;
    }
  }
}
