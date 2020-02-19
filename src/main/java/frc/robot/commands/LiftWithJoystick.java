/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Lift;
import frc.robot.Constants;

public class LiftWithJoystick extends CommandBase {
  /**
   * Creates a new LiftWithJoystick.
   */

  //Declare joystick and global subsystem
  private Joystick rightJoy;
  private Lift lift;

  public LiftWithJoystick(Lift lift) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(lift);
    this.lift = lift;
    rightJoy = new Joystick(Constants.USB_RIGHT_JOYSTICK);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //Make sure the motor speed is zeroed at init
    lift.setLift(0.0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Check POV of rightJoy
    if(rightJoy.getPOV() == 0){
      lift.setLift(Constants.SCALER_LIFT);
    }else if(rightJoy.getPOV() == 4){
      lift.setLift(Constants.SCALER_LIFT * -1);
    }else {
      lift.setLift(0.0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //If the command ends or is interrupted, stop the motor
    lift.setLift(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
