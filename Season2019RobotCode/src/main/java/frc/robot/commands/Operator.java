/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Manipulator.Commands.Release;
import frc.robot.commands.Misc.WristToggle;

public class Operator extends Command {
  public Operator() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.pneumatics);
  }

  public String Gamepiece = "Ball";
  Command Wrist = new WristToggle();
  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
      Wrist.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    // OPERATOR BUTTONS ////////////////////////////////////////////////
    double OpX = Robot.oi.OpX();
    double OpY = Robot.oi.OpY();
    int OpHat = Robot.oi.OpHat();

    boolean Obtn1 = Robot.oi.OpBTN(1);
    boolean Obtn2 = Robot.oi.OpBTN(2);
    //boolean Obtn3 = Robot.oi.OpBTN(3);
    //boolean Obtn4 = Robot.oi.OpBTN(4);
    //boolean Obtn5 = Robot.oi.OpBTN(5);
    //boolean Obtn6 = Robot.oi.OpBTN(6);
    boolean Obtn7 = Robot.oi.OpBTN(7);
    boolean Obtn8 = Robot.oi.OpBTN(8);
    boolean Obtn9 = Robot.oi.OpBTN(9);
    boolean Obtn10 = Robot.oi.OpBTN(10);
    boolean Obtn11 = Robot.oi.OpBTN(11);
    boolean Obtn12 = Robot.oi.OpBTN(12);

    //Robot.lift.ManualHab2(OpX);
   // Robot.lift.ManualHab2(OpX);

   //Robot.manipulator.Manual(OpX);

   if(Robot.pneumatics.isHab() == false){
    Robot.lift.Manual(OpY);
   }

    Robot.drivetrain.Amps();

      if(Obtn7 || Obtn9 || Obtn11){
        Constants.GamePiece = "Hatch";
        Robot.manipulator.GamePiece("Hatch");
      }
      if(Obtn8 || Obtn10 || Obtn12){
        Constants.GamePiece = "Ball";
        Robot.manipulator.GamePiece("Ball");
      }
    
    
    
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
  }
}
