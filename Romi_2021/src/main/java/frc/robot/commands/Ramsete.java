// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.List;

import edu.wpi.first.wpilibj.Ultrasonic.Unit;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;

public class Ramsete extends CommandBase {
  /** Creates a new Ramsete. */
  Trajectory exampleTrajectory = new Trajectory();
  Drivetrain m_drivetrain = RobotContainer.m_drivetrain;
  int Tnum;
  RamseteCommand ramseteCommand;

  public Ramsete(int TrajectoryNumber) {
    // Use addRequirements() here to declare subsystem dependencies.
    Tnum = TrajectoryNumber;
    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    generateRamseteCommand(Tnum).schedule();
    m_drivetrain.setServo(85);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Tnum == 2 || Tnum == 4 || Tnum == 6){
      m_drivetrain.setServo(167);
    }
  }

  public Command generateRamseteCommand(int trajectoryNum) {

    // This trajectory can be modified to suit your purposes
    // Note that all coordinates are in meters, and follow NWU conventions.
    // If you would like to specify coordinates in inches (which might be easier
    // to deal with for the Romi), you can use the Units.inchesToMeters() method
    var autoVoltageConstraint = new DifferentialDriveVoltageConstraint(new SimpleMotorFeedforward(Constants.ksVolts,
          Constants.kvVoltSecondsPerMeter, Constants.kaVoltSecondsSquaredPerMeter), Constants.kDriveKinematics, 10);
      TrajectoryConfig config = new TrajectoryConfig(Constants.kMaxSpeedMetersPerSecond,
          Constants.kMaxAccelerationMetersPerSecondSquared).setKinematics(Constants.kDriveKinematics)
              .addConstraint(autoVoltageConstraint);
  
      if (trajectoryNum == 0) {
        exampleTrajectory = TrajectoryGenerator.generateTrajectory(
      // Start at the origin facing the +X direction
      new Pose2d(0, 0, new Rotation2d(0)),
      // Pass through these two interior waypoints, making an 's' curve path
      List.of(
          new Translation2d(Units.inchesToMeters(18), Units.inchesToMeters(15)),
          //new Translation2d(Units.inchesToMeters(33), Units.inchesToMeters(12)),
          new Translation2d(Units.inchesToMeters(48), Units.inchesToMeters(15)),
          new Translation2d(Units.inchesToMeters(64), Units.inchesToMeters(-1)),
          new Translation2d(Units.inchesToMeters(77), Units.inchesToMeters(10)),
          new Translation2d(Units.inchesToMeters(67), Units.inchesToMeters(15)),
          new Translation2d(Units.inchesToMeters(62), Units.inchesToMeters(8)),
          new Translation2d(Units.inchesToMeters(30), Units.inchesToMeters(9)),
          new Translation2d(Units.inchesToMeters(22), Units.inchesToMeters(23))
  
      ),
      new Pose2d(0, Units.inchesToMeters(15), new Rotation2d(60)),
      // Pass config
      config
  );
  }
  else if(trajectoryNum == 1){
    exampleTrajectory = TrajectoryGenerator.generateTrajectory(
      // Start at the origin facing the +X direction
      new Pose2d(Units.inchesToMeters(7.5), Units.inchesToMeters(22.5), new Rotation2d(0)),
      // Pass through these two interior waypoints, making an 's' curve path
      List.of(
         // new Translation2d(Units.inchesToMeters(22), Units.inchesToMeters(23))
      ),
      new Pose2d(Units.inchesToMeters(20.5), Units.inchesToMeters(35.5), new Rotation2d(Units.degreesToRadians(100))),
      // Pass config
      config
  );
  }
  else if(trajectoryNum == 2){
    exampleTrajectory = TrajectoryGenerator.generateTrajectory(
      // Start at the origin facing the +X direction
      new Pose2d(Units.inchesToMeters(22), Units.inchesToMeters(31), new Rotation2d(Units.degreesToRadians(88))),
      // Pass through these two interior waypoints, making an 's' curve path
      List.of(
          new Translation2d(Units.inchesToMeters(33), Units.inchesToMeters(8))
      ),
      new Pose2d(Units.inchesToMeters(44), Units.inchesToMeters(7.5), new Rotation2d(Units.degreesToRadians(70))),
      // Pass config
      config.setReversed(true)
  );
  }
  else if(trajectoryNum == 3){
    exampleTrajectory = TrajectoryGenerator.generateTrajectory(
      // Start at the origin facing the +X direction
      new Pose2d(Units.inchesToMeters(46), Units.inchesToMeters(7.5), new Rotation2d(Units.degreesToRadians(100))),
      // Pass through these two interior waypoints, making an 's' curve path
      List.of(
         // new Translation2d(Units.inchesToMeters(45), Units.inchesToMeters(15))
      ),
      new Pose2d(Units.inchesToMeters(48.2), Units.inchesToMeters(34.5), new Rotation2d(Units.degreesToRadians(60))),
      // Pass config
      config
  );
  }
  else if(trajectoryNum == 4){
    exampleTrajectory = TrajectoryGenerator.generateTrajectory(
      // Start at the origin facing the +X direction
      new Pose2d(Units.inchesToMeters(45), Units.inchesToMeters(32), new Rotation2d(Units.degreesToRadians(90))),
      // Pass through these two interior waypoints, making an 's' curve path
      List.of(
         new Translation2d(Units.inchesToMeters(45), Units.inchesToMeters(4.5))
      ),
      new Pose2d(Units.inchesToMeters(70), Units.inchesToMeters(2.5), new Rotation2d(Units.degreesToRadians(80))),
      // Pass config
      config.setReversed(true)
  );
  }
  else if(trajectoryNum == 5){
    exampleTrajectory = TrajectoryGenerator.generateTrajectory(
      // Start at the origin facing the +X direction
      new Pose2d(Units.inchesToMeters(66.5), Units.inchesToMeters(7.5), new Rotation2d(Units.degreesToRadians(100))),
      // Pass through these two interior waypoints, making an 's' curve path
      List.of(
        
      ),
      new Pose2d(Units.inchesToMeters(67.5), Units.inchesToMeters(38), new Rotation2d(Units.degreesToRadians(90))),
      // Pass config
      config.setReversed(false)
  );
  }
  else if(trajectoryNum == 6){
    exampleTrajectory = TrajectoryGenerator.generateTrajectory(
      // Start at the origin facing the +X direction
      new Pose2d(Units.inchesToMeters(63), Units.inchesToMeters(33), new Rotation2d(Units.degreesToRadians(88))),
      // Pass through these two interior waypoints, making an 's' curve path
      List.of(
        
      ),
      new Pose2d(Units.inchesToMeters(81), Units.inchesToMeters(20), new Rotation2d(Units.degreesToRadians(180))),
      // Pass config
      config.setReversed(true)
  );
  }
    
  ramseteCommand = new RamseteCommand(
        exampleTrajectory,
        m_drivetrain::getPose,
        new RamseteController(Constants.kRamseteB, Constants.kRamseteZeta),
        new SimpleMotorFeedforward(Constants.ksVolts, Constants.kvVoltSecondsPerMeter, Constants.kaVoltSecondsSquaredPerMeter),
        Constants.kDriveKinematics,
        m_drivetrain::getWheelSpeeds,
        new PIDController(Constants.kPDriveVel, 0, 0),
        new PIDController(Constants.kPDriveVel, 0, 0),
        m_drivetrain::tankDriveVolts,
        m_drivetrain);
  
    m_drivetrain.resetOdometry(exampleTrajectory.getInitialPose());

    // Set up a sequence of commands
    // First, we want to reset the drivetrain odometry
    return new InstantCommand(() -> m_drivetrain.resetOdometry(exampleTrajectory.getInitialPose()), m_drivetrain)
        // next, we run the actual ramsete command
        .andThen(ramseteCommand)
  
        // Finally, we make sure that the robot stops
        .andThen(new InstantCommand(() -> m_drivetrain.tankDriveVolts(0, 0), m_drivetrain));   
        
       
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(ramseteCommand.isFinished()){
      generateRamseteCommand(1).end(false);
      ramseteCommand.end(false);
      return true;
    }else{
      return false;
    }
  }
}
