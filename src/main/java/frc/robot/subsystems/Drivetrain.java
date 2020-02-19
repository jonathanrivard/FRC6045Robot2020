/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  /**
   * Creates a new Drivetrain.
   */
  //Declare drivetrain motors
  private WPI_TalonSRX leftFrontMotor;
  private WPI_TalonSRX leftBackMotor;
  private WPI_TalonSRX rightFrontMotor;
  private WPI_TalonSRX rightBackMotor;
  //Declare SpeedControllerGroups for sides
  private SpeedControllerGroup leftMotors;
  private SpeedControllerGroup rightMotors;
  //Declare differential drive
  private DifferentialDrive drive;
  //Declare and define getter for differential drive
  public DifferentialDrive getDifferentialDrive(){ return drive; }

  public Drivetrain() {
    //Init motors
    leftFrontMotor = new WPI_TalonSRX(Constants.PORT_MOTOR_FRONT_LEFT);
    leftBackMotor = new WPI_TalonSRX(Constants.PORT_MOTOR_BACK_LEFT);
    rightFrontMotor = new WPI_TalonSRX(Constants.PORT_MOTOR_FRONT_RIGHT);
    rightBackMotor = new WPI_TalonSRX(Constants.PORT_MOTOR_BACK_RIGHT);
    //Init speed controller group sides
    leftMotors = new SpeedControllerGroup(leftFrontMotor, leftBackMotor);
    rightMotors = new SpeedControllerGroup(rightFrontMotor, rightBackMotor);
    //Init differential drive using speed controller groups
    drive = new DifferentialDrive(leftMotors, rightMotors);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
