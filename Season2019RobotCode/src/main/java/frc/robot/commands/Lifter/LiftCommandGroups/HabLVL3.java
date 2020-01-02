/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Lifter.LiftCommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import frc.robot.commands.Pneumatics.LiftHab;
import frc.robot.commands.Lights;
import frc.robot.commands.Lifter.LiftPositions.*;
import frc.robot.commands.Lifter.LiftPositions.Hab2Control;
import frc.robot.commands.Misc.*;
import frc.robot.commands.Pneumatics.LiftLock;


public class HabLVL3 extends CommandGroup {
  /**
   * Add your docs here.
   */
  public HabLVL3() {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
SmartDashboard.putBoolean("Running Hab3", true);
    //addSequential(new Reset());
    addParallel(new Lights("Yellow"));
    addParallel(new ShiftLiftToHab("Hab"));
    addParallel(new LiftLock("Unlock"));
    addSequential(new DelayTimer(0.1));
    addParallel(new Hab2Control(1300));
    addSequential(new HabPoint(300)); // 300
    addParallel(new Hab2Control(7500));
    addSequential(new HabPoint(1500)); // 1500
    addParallel(new Lights("Purple"));
    addSequential(new HabStop());
   

  }
}