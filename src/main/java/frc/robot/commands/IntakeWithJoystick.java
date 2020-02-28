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
import frc.robot.subsystems.Intake;

public class IntakeWithJoystick extends CommandBase {
  /**
   * Creates a new IntakeWithJoystick.
   */

  //Declare joystick for intake control
  private Joystick leftJoy;
  private Joystick rightJoy;
  private Joystick mainJoy;
  //private XboxController mainJoy;
  //Declare global subsystem intake
  private Intake intake;

  public IntakeWithJoystick(Intake intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake);
    this.intake = intake;
    leftJoy = new Joystick(Constants.USB_LEFT_JOYSTICK);
    rightJoy = new Joystick(Constants.USB_RIGHT_JOYSTICK);
    mainJoy = new Joystick(Constants.USB_MAIN_JOYSTICK);
    //mainJoy = new XboxController(Constants.USB_XBOX_CONTROLLER);
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
      intake.setElevator(Constants.SCALER_INTAKE_ELEVATOR * -1);
    }else if((rightJoy.getRawButton(Constants.BUTTON_R_INTAKE_ELEVATOR_DOWN)) || mainJoy.getRawButton(Constants.BUTTON_M_ELEVATOR_DOWN)){
      intake.setElevator(Constants.SCALER_INTAKE_ELEVATOR);
    }else {
      intake.setElevator(0.0);
    }
    //Check for wheel
    if(leftJoy.getRawButton(Constants.BUTTON_L_INTAKE_WHEEL_IN) || mainJoy.getRawButton(Constants.BUTTON_M_INTAKE_WHEEL_IN)){
      intake.setWheel(Constants.SCALER_INTAKE_WHEEL);
    }else if(leftJoy.getRawButton(Constants.BUTTON_L_INTAKE_WHEEL_OUT) || mainJoy.getRawButton(Constants.BUTTOn_M_INTAKE_WHEEL_OUT)){
      intake.setWheel(Constants.SCALER_INTAKE_WHEEL * -1);
    }else {
      intake.setWheel(0.0);
    }
    //Check for winch
    if(leftJoy.getPOV() == 0 || mainJoy.getPOV() == 0){
      intake.setWinch(Constants.SCALER_INTAKE_WINCH);
    }else if(leftJoy.getPOV() == 180 || mainJoy.getPOV() == 180){
      intake.setWinch(Constants.SCALER_INTAKE_WINCH * -1);
    }else {
      intake.setWinch(0.0);
    }
  }

  private void executeArcadeDrive(){
    //If the intake button is pressed, activate motors
    //Check for elevator
    if(mainJoy.getRawButton(Constants.BUTTON_M_SHOOT) || mainJoy.getRawButton(Constants.BUTTON_M_INTAKE_AND_ELEVATOR) || mainJoy.getRawButton(Constants.BUTTON_M_ELEVATOR_UP)){
      intake.setElevator(Constants.SCALER_INTAKE_ELEVATOR * -1); //Elevator Up
    }else if(mainJoy.getRawButton(Constants.BUTTON_M_ELEVATOR_DOWN)){
      intake.setElevator(Constants.SCALER_INTAKE_ELEVATOR); //Elevator Down
    }else {
      intake.setElevator(0.0);
    }
    //Check for wheel
    if(mainJoy.getRawButton(Constants.BUTTON_M_SHOOT) || mainJoy.getRawButton(Constants.BUTTON_M_INTAKE_AND_ELEVATOR) || mainJoy.getRawButton(Constants.BUTTON_M_INTAKE_WHEEL_IN)){
      intake.setWheel(Constants.SCALER_INTAKE_WHEEL); //Wheel In
    }else if(mainJoy.getRawButton(Constants.BUTTOn_M_INTAKE_WHEEL_OUT)){
      intake.setWheel(Constants.SCALER_INTAKE_WHEEL * -1); //Wheel Out
    }else {
      intake.setWheel(0.0);
    }
    //Check for winch
    if(leftJoy.getPOV() == 0 || mainJoy.getPOV() == 0){
      intake.setWinch(Constants.SCALER_INTAKE_WINCH);
    }else if(leftJoy.getPOV() == 180 || mainJoy.getPOV() == 180){
      intake.setWinch(Constants.SCALER_INTAKE_WINCH * -1);
    }else {
      intake.setWinch(0.0);
    }
  }

  /*
  private void executeXboxDrive(){
    if(mainJoy.getBumper(Hand.kLeft) || mainJoy.getBumper(Hand.kRight)){
      intake.setElevator(Constants.SCALER_INTAKE_ELEVATOR * -1);
    }else if(mainJoy.getYButton()){
      intake.setElevator(Constants.SCALER_INTAKE_ELEVATOR);
    }else {
      intake.setElevator(0.0);
    }
    //Check for wheel
    if(mainJoy.getTriggerAxis(Hand.kLeft) > 0.0){
      intake.setWheel(Constants.SCALER_INTAKE_WHEEL);
    }else if(mainJoy.getAButton()){
      intake.setWheel(Constants.SCALER_INTAKE_WHEEL * -1);
    }else {
      intake.setWheel(0.0);
    }
    //Check for winch
    if(mainJoy.getXButton()){
      intake.setWinch(Constants.SCALER_INTAKE_WINCH);
    }else if(mainJoy.getBButton()){
      intake.setWinch(Constants.SCALER_INTAKE_WINCH * -1);
    }else {
      intake.setWinch(0.0);
    }
  }
  */

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
