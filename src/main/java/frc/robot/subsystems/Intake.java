/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  /**
   * Creates a new Intake.
   */

  //Declare motors for intake
  private WPI_VictorSPX wheelMotor;
  private WPI_VictorSPX elevatorMotor;
  private Spark winchMotor;
  //Declare analog port and ultrasonic sensor
  private AnalogInput topSensor;
  private AnalogInput bottomSensor;

  //Declare and define functions for setting motor values
  public void setWheel(double d){
    wheelMotor.set(d);
  }

  public void setElevator(double d){
    elevatorMotor.set(d);
  }

  public void setWinch(double d){
    winchMotor.set(d);
  }

  //Declare and define getters
  public double getTopDistance(){
    return topSensor.getValue();
  }

  public boolean getTopBlocked(){
    if(getTopDistance() > 10.0){
      return false;
    }else {
      return true;
    }
  }

  public boolean getBottomBlocked(){
    if(getBottomDistance() > 10.0){
      return false;
    }else {
      return true;
    }
  }

  public double getBottomDistance(){
    return bottomSensor.getValue();
  }

  public Intake() {
    //Init motors for intake
    wheelMotor = new WPI_VictorSPX(Constants.PORT_MOTOR_INTAKE_WHEEL);
    elevatorMotor = new WPI_VictorSPX(Constants.PORT_MOTOR_INTAKE_ELEVATOR);
    winchMotor = new Spark(Constants.PORT_MOTOR_INTAKE_WINCH);
    //Init ultrasonic sensor
    topSensor = new AnalogInput(Constants.PORT_ANALOG_ULTRASONIC_TOP);
    bottomSensor = new AnalogInput(Constants.PORT_ANALOG_ULTRASONIC_BOTTOM);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
