// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Auto3 extends SequentialCommandGroup {
  /** Creates a new Auto3. */
  public Auto3() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    double spdfact = 1.5;
    addCommands(
      new ForwardDistance(1, 0.3),
      new SimpleMove(0.465 * spdfact, 0.63 * spdfact, 20),
      new SimpleMove(0.3 * spdfact, 0.6 * spdfact, 11.5),
      new ForwardDistance(1, 12),
      new SimpleMove(0.62 * spdfact, 0.3 * spdfact, 11),
      new ForwardDistance(1, 0.3),
      new SimpleMove(0.625 * spdfact, 0.465 * spdfact, 19),
      new ForwardDistance(1, 3.6)
    );
  }
}
