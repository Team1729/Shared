// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;
import frc.robot.*;

public class Driver extends CommandBase {
  /** Creates a new Driver. */
  public Joystick Joy0 = new Joystick(0);
  public Driver() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double X = -Joy0.getX();
    double Y = -Joy0.getY();

      if(Y < 0.07 && Y > -0.07) {
        Y = 0;
      }

      Robot.Drivetrain.getEncoder("L");
      Robot.Drivetrain.getEncoder("R");

      if(Joy0.getRawButton(3)){
        Robot.Drivetrain.resetEncoders();
        Robot.RomiGyro.reset();
      }
      Robot.Drivetrain.Arcade(X/1.2, Y); 
      Robot.RomiGyro.getAngleX();
      Robot.RomiGyro.getAngleY();
      Robot.RomiGyro.getAngleZ();
      //SmartDashboard.putNumber("EncoderL", Robot.Drivetrain.getEncoder("L"));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
