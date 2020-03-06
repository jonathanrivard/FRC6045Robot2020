/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.ShooterWheel;
import frc.robot.Constants;

public class ShootWithJoystick extends CommandBase {
  /**
   * Creates a new ShootWithJoystick.
   */

  ShooterWheel shooterWheel;
  Limelight limelight;
  Joystick mainJoy;
  Joystick rightJoy;

  public ShootWithJoystick(ShooterWheel shooterWheel) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooterWheel);
    this.shooterWheel = shooterWheel;
    mainJoy = new Joystick(Constants.USB_MAIN_JOYSTICK);
    rightJoy = new Joystick(Constants.USB_RIGHT_JOYSTICK);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //Set wheel speed to zero on start
    shooterWheel.setWheel(0.0);
  }


  private void executeTankDrive(){
    //If our shoot button is pressed
    if(rightJoy.getRawButton(Constants.BUTTON_R_SHOOT)){
        //Get scaler from slider on joystick
        double scaler = rightJoy.getRawAxis(3);
        //Adjust scaler to go from 0 to 1
        scaler = -0.5 * scaler + 0.5;
        //Set the shooter wheel with the slider scaler
        shooterWheel.setWheel(-1 * scaler);
        System.out.println("Shooter Percent: " + scaler);
        //shooterWheel.setWheel(mainJoy.getTriggerAxis(Hand.kRight) * -1);
      }else {
        //If the button is not pressed, stop wheel
        shooterWheel.setWheel(0.0);
      }
  }

  private void executeArcadeDrive(){
    //If our shoot button is pressed
    if(mainJoy.getRawButton(Constants.BUTTON_M_SHOOT)){
      //if(mainJoy.getTriggerAxis(Hand.kRight) > 0){
        //Get scaler from slider on joystick
        //double scaler = rightJoy.getRawAxis(3);
        //Adjust scaler to go from 0 to 1
        //scaler = -0.5 * scaler + 0.5;
        //Set the shooter wheel with the slider scaler
        //shooterWheel.setWheel(-1.0 * scaler);
        //System.out.println("Shooter Percent: " + scaler);
        shooterWheel.setWheel(-1.0);
        //shooterWheel.setWheel(mainJoy.getTriggerAxis(Hand.kRight) * -1);
    }else {
        //If the button is not pressed, stop wheel
        shooterWheel.setWheel(0.0);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Constants.SETTING_DRIVE_TYPE == 0){ //Tank Drive
      executeTankDrive();
    }else { //Arcade Drive
      executeArcadeDrive();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //If the command ends or is interrupted, set wheel speed to 0
    shooterWheel.setWheel(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
