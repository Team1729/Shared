/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.*;

/**
 * Add your docs here.
 */
public class Pneumatics extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  DoubleSolenoid DriveShift = new DoubleSolenoid(2, 3);
  DoubleSolenoid LiftShift = new DoubleSolenoid(0, 1);
  DoubleSolenoid ManipulatorZ = new DoubleSolenoid(6, 7);
  DoubleSolenoid HabLock = new DoubleSolenoid(4, 5);
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new Operator());
  }
boolean habState = false;
boolean extendState = false;

  public boolean isHab(){
    return habState;
  }

  public boolean isExtended(){
    return extendState;
  }
  /**
   * 
   * @param Gear , sets what gear to go to (High or Low)
   * High = HighGear
   * Low = LowGear
   */
  public void ShiftDrive(String Gear){
    if(Gear == "High"){
      DriveShift.set(DoubleSolenoid.Value.kForward);
    } else {
      DriveShift.set(DoubleSolenoid.Value.kReverse);
    }
  }

  /**
   * 
   * @param Pos, "Out" or "In"
   */
  public void ManipulatorZ(String Pos){
    if(Pos == "Out"){
      ManipulatorZ.set(DoubleSolenoid.Value.kForward);
      extendState = true;
    } else {
      ManipulatorZ.set(DoubleSolenoid.Value.kReverse);
      extendState = false;
    }
  }

  /**
   * @param why, "Lock" or "Unlock"
   */
  public void HabLock(String why){
    if(why == "Lock"){
      HabLock.set(DoubleSolenoid.Value.kForward);
    }
    else if(why == "Unlock"){
      HabLock.set(DoubleSolenoid.Value.kReverse);
    }
  }
  /**
   * 
   * @param Lift sets what lifter to use (Hab is hablift, Ver is verticle lift)
   * Hab = HabLift
   * Ver = VerticalLift
   */
  public void ShiftLift(String Lift){
    if(Lift == "Hab"){
      LiftShift.set(DoubleSolenoid.Value.kForward);
      habState = true;
    } else {
      LiftShift.set(DoubleSolenoid.Value.kReverse);
      habState = false;
    }
  }
  /**
   * 
   * @param Lift = extend or retract
   * Extend = extend
   * Retract = retract
   */

}
