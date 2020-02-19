/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Lift extends SubsystemBase {
  /**
   * Creates a new Lift.
   */

  //Declare lift motor
  private VictorSP liftMotor;

  //Declare and define a fucntion to set the speed of the lift motor
  public void setLift(double d){
    liftMotor.set(d);
  }

  public Lift() {
    //Init lift motor
    liftMotor = new VictorSP(Constants.PORT_MOTOR_LIFT);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
