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
import frc.robot.subsystems.ColorWheel;

public class ColorWheelWithJoystick extends CommandBase {
  /**
   * Creates a new ColorWheelWithJoystick.
   */

  //Declare instance variables
  private ColorWheel colorWheel;
  private Joystick leftJoy;

  public ColorWheelWithJoystick(ColorWheel colorWheel) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(colorWheel);
    //Set subsystem to passed value
    this.colorWheel = colorWheel;
    //Init the left joystick
    leftJoy = new Joystick(Constants.USB_LEFT_JOYSTICK);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(leftJoy.getPOV() == 90){
      colorWheel.setWheel(-1.0 * Constants.SCALER_COLOR_WHEEL);
    }else if(leftJoy.getPOV() == 270){
      colorWheel.setWheel(1.0 * Constants.SCALER_COLOR_WHEEL);
    }else {
      colorWheel.setWheel(0.0);
    }
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
