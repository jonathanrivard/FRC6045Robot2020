/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

public class TankDrive extends CommandBase {
  /**
   * Creates a new TankDrive.
   */

  //Declare instance variables
  private Drivetrain drivetrain;
  private Joystick leftJoy;
  private Joystick rightJoy;

  public TankDrive(Drivetrain drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
    //Set the drivetrain to the passed subsystem
    this.drivetrain = drivetrain;
    //Init the tank drive joysticks
    leftJoy = new Joystick(Constants.USB_LEFT_JOYSTICK);
    rightJoy = new Joystick(Constants.USB_RIGHT_JOYSTICK);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Set the drivetrain to use the scaled values from the tank drive joysticks every frame
    drivetrain.getDifferentialDrive().tankDrive(leftJoy.getY() * Constants.SCALER_DRIVE_FORWARD * -1, rightJoy.getY() * Constants.SCALER_DRIVE_FORWARD * -1);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
