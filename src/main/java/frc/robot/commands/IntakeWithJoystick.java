/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class IntakeWithJoystick extends CommandBase {
  /**
   * Creates a new IntakeWithJoystick.
   */

  //Declare joysticks for intake control
  private Joystick leftJoy;
  private Joystick rightJoy;
  private Joystick mainJoy;
  //Declare global subsystem intake
  private Intake intake;

  public IntakeWithJoystick(Intake intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake);
    //Set intake to passed subsystem
    this.intake = intake;
    //Init the joysticks
    leftJoy = new Joystick(Constants.USB_LEFT_JOYSTICK);
    rightJoy = new Joystick(Constants.USB_RIGHT_JOYSTICK);
    mainJoy = new Joystick(Constants.USB_MAIN_JOYSTICK);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //Make sure the motors are not running on command init
    intake.setWheel(0.0);
    intake.setElevator(0.0);
  }

  private void executeTankDrive(){
    //If the intake button is pressed, activate motors
    //Check for elevator
    if((leftJoy.getRawButton(Constants.BUTTON_L_INTAKE_ELEVATOR_UP) || rightJoy.getRawButton(Constants.BUTTON_R_INTAKE_ElEVATOR_UP_R)) || mainJoy.getRawButton(Constants.BUTTON_M_ELEVATOR_UP)){
      intake.setElevator(Constants.SCALER_INTAKE_ELEVATOR * -1); //Set elevator to up
    }else if((rightJoy.getRawButton(Constants.BUTTON_R_INTAKE_ELEVATOR_DOWN)) || mainJoy.getRawButton(Constants.BUTTON_M_ELEVATOR_DOWN)){
      intake.setElevator(Constants.SCALER_INTAKE_ELEVATOR); //Set elevator to down
    }else {
      intake.setElevator(0.0); //Otherwise, stop the elevator
    }
    //Check for wheel
    if(leftJoy.getRawButton(Constants.BUTTON_L_INTAKE_WHEEL_IN) || mainJoy.getRawButton(Constants.BUTTON_M_INTAKE_WHEEL_IN)){
      intake.setWheel(Constants.SCALER_INTAKE_WHEEL); //Set the wheel to up
    }else if(leftJoy.getRawButton(Constants.BUTTON_L_INTAKE_WHEEL_OUT) || mainJoy.getRawButton(Constants.BUTTOn_M_INTAKE_WHEEL_OUT)){
      intake.setWheel(Constants.SCALER_INTAKE_WHEEL * -1); //Set the wheel to down
    }else {
      intake.setWheel(0.0); //Otherwise, stop the wheel
    }
    //Check for winch
    if(leftJoy.getPOV() == 0 || mainJoy.getPOV() == 0){
      intake.setWinch(Constants.SCALER_INTAKE_WINCH); //Set the winch to up
    }else if(leftJoy.getPOV() == 180 || mainJoy.getPOV() == 180){
      intake.setWinch(Constants.SCALER_INTAKE_WINCH * -1); //Set the winch to down
    }else {
      intake.setWinch(0.0); //Otherwise, stop the winch
    }
  }

  boolean firstClick = true;
  double startTime;
  private void executeArcadeDrive(){
    if(!mainJoy.getRawButton(Constants.BUTTON_M_SHOOT)){
      firstClick = true;
    }
    //Timer for spin up
    if(firstClick == true){
      startTime = Timer.getFPGATimestamp();
      firstClick = false;
    }
    //If the intake button is pressed, activate motors
    //Check for elevator
    if(mainJoy.getRawButton(Constants.BUTTON_M_ELEVATOR_UP)){
      intake.setElevator(Constants.SCALER_INTAKE_ELEVATOR * -1); //Elevator Up
    }else if(mainJoy.getRawButton(Constants.BUTTON_M_SHOOT) && Timer.getFPGATimestamp()-startTime > Constants.SETTING_SPIN_UP){
      intake.setElevator(Constants.SCALER_INTAKE_ELEVATOR * -1); //Elevator Up
    }else if(mainJoy.getRawButton(Constants.BUTTON_M_ELEVATOR_DOWN)){
      intake.setElevator(Constants.SCALER_INTAKE_ELEVATOR); //Elevator Down
    }else {
      intake.setElevator(0.0); //Otherwise, stop the elevator
    }
    //Check for wheel
    if(mainJoy.getRawButton(Constants.BUTTON_M_INTAKE_AND_ELEVATOR) || mainJoy.getRawButton(Constants.BUTTON_M_INTAKE_AND_ELEVATOR_TWO) || mainJoy.getRawButton(Constants.BUTTON_M_INTAKE_WHEEL_IN)){
      intake.setWheel(Constants.SCALER_INTAKE_WHEEL); //Wheel In
    }else if(mainJoy.getRawButton(Constants.BUTTOn_M_INTAKE_WHEEL_OUT)){
      intake.setWheel(Constants.SCALER_INTAKE_WHEEL * -1); //Wheel Out
    }else {
      intake.setWheel(0.0); //Otherwise, stop the wheel
    }
    //Check for winch
    if(leftJoy.getPOV() == 0 || mainJoy.getPOV() == 0){
      intake.setWinch(Constants.SCALER_INTAKE_WINCH); //Set the winch to up
    }else if(leftJoy.getPOV() == 180 || mainJoy.getPOV() == 180){
      intake.setWinch(Constants.SCALER_INTAKE_WINCH * -1); //Set the winch to down
    }else {
      intake.setWinch(0.0); //Otherwise, stop the winch
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Constants.SETTING_DRIVE_TYPE == 0){ //Tank Drive
      executeTankDrive(); //If want tank, execute using tank controls
      //Check if the sensors are sensing a ball
      //If the top is clear, pull in the ball using the elevator
      if(intake.getBottomBlocked() && !intake.getTopBlocked() && !(rightJoy.getRawButton(Constants.BUTTON_R_INTAKE_ELEVATOR_DOWN))){ //Double check the operator isn't trying to outtake the balls
        intake.setElevator(Constants.SCALER_INTAKE_ELEVATOR * -1); //Set elevator to up
      }
    }else { //Arcade Drive
      executeArcadeDrive(); //If want arcade, execute using arcade controls
      //Check if the sensors are sensing a ball
      //If the top is clear, pull in the ball using the elevator
      if(intake.getBottomBlocked() && !intake.getTopBlocked() && !(mainJoy.getRawButton(Constants.BUTTON_M_ELEVATOR_DOWN))){ //Double check the operator isn't trying to outtake the balls
        intake.setElevator(Constants.SCALER_INTAKE_ELEVATOR * -1); //Set elevator to up
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //If the command ends or is interrupted, stop the motors
    intake.setWheel(0.0);
    intake.setElevator(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
