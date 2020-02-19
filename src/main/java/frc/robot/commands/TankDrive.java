/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

public class TankDrive extends CommandBase {
  /**
   * Creates a new TankDrive.
   */

  private Drivetrain drivetrain;
  private Joystick leftJoy;
  private Joystick rightJoy;
  //private XboxController mainJoy;

  public TankDrive(Drivetrain drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
    this.drivetrain = drivetrain;
    leftJoy = new Joystick(Constants.USB_LEFT_JOYSTICK);
    rightJoy = new Joystick(Constants.USB_RIGHT_JOYSTICK);
    //mainJoy = new XboxController(Constants.USB_XBOX_CONTROLLER);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.getDifferentialDrive().tankDrive(leftJoy.getY() * Constants.SCALER_DRIVE_FORWARD * -1, rightJoy.getY() * Constants.SCALER_DRIVE_FORWARD * -1);
    //drivetrain.getDifferentialDrive().tankDrive(mainJoy.getY(Hand.kLeft) * -1 * Constants.SCALER_DRIVE_SIDE, mainJoy.getY(Hand.kRight) * -1 * Constants.SCALER_DRIVE_SIDE);
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
