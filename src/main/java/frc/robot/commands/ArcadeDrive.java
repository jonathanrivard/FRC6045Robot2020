/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.Constants;

public class ArcadeDrive extends CommandBase {
  /**
   * Creates a new ArcadeDrive.
   */

  //Declare instance variables
  private Drivetrain drivetrain;
  private Joystick mainJoy;

  public ArcadeDrive(Drivetrain drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
    //Set the drivetrain to passed value
    this.drivetrain = drivetrain;
    //Init joystick
    mainJoy = new Joystick(Constants.USB_MAIN_JOYSTICK);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //Init with zeroed arcade drive
    drivetrain.getDifferentialDrive().arcadeDrive(0.0, 0.0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Get values from main joystick
    double forwardValue = mainJoy.getY() * -1;
    double sideValue = mainJoy.getX();
    double scaler = mainJoy.getRawAxis(3);
    scaler = -0.5 * scaler + 0.5;
    //Threshhold the values
    forwardValue = (Math.abs(forwardValue) > Constants.THRESHHOLD_JOYSTICKS) ? forwardValue : 0.0;
    sideValue = (Math.abs(sideValue) > Constants.THRESHHOLD_JOYSTICKS) ? sideValue : 0.0;
    //Pass into differntial drive
    drivetrain.getDifferentialDrive().arcadeDrive(forwardValue * Constants.SCALER_DRIVE_FORWARD * scaler, sideValue * Constants.SCALER_DRIVE_SIDE * scaler);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //End or interrupt with zeroed arcade drive
    drivetrain.getDifferentialDrive().arcadeDrive(0.0, 0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
