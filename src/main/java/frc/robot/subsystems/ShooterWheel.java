/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterWheel extends SubsystemBase {
  /**
   * Creates a new ShooterWheel.
   */
  //Declare motors for shooter wheel
  public WPI_VictorSPX wheelMotorOne;
  public WPI_VictorSPX wheelMotorTwo;
  //Declare speed controller group for wheel motors
  SpeedControllerGroup wheelMotors;

  //Function for setting wheel(s) speed
  public void setWheel(double d){
    //Invert motor one
    wheelMotorOne.set(d * -1);
    wheelMotorTwo.set(d);
  }

  public ShooterWheel() {
    //Init motors on CAN VictorSPX controllers
    wheelMotorOne = new WPI_VictorSPX(Constants.PORT_MOTOR_SHOOTER_WHEEL_ONE);
    wheelMotorTwo = new WPI_VictorSPX(Constants.PORT_MOTOR_SHOOTER_WHEEL_TWO);
    //Init speed controller gorup for wheel motors
    wheelMotors = new SpeedControllerGroup(wheelMotorOne, wheelMotorTwo);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
