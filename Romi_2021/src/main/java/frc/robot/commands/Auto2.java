// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Auto2 extends SequentialCommandGroup {
  /** Creates a new Auto2. */
  public Auto2() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    double spdfact = 1.2;
    addCommands(
      new ForwardDistance(0.5 * spdfact, 1),
      new SimpleMove(0.5 * spdfact, 0.62 * spdfact, 21),
      new SimpleMove(0.3 * spdfact, 0.6 * spdfact, 9.5),
      new ForwardDistance(0.7, 14),
      new SimpleMove(0.62 * spdfact, 0.3 * spdfact, 9),
      new ForwardDistance(0.6, 1),
      new SimpleMove(0.625 * spdfact, 0.522 * spdfact, 21),
      new ForwardDistance(0.6, 4)
    );
  }
}
