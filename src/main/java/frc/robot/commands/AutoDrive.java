/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class AutoDrive extends CommandBase {
  /**
   * Creates a new AutoDrive.
   */

  private Drivetrain drivetrain;
  double leftSpeed;
  double rightSpeed;

  public AutoDrive(Drivetrain drivetrain, double leftSpeed, double rightSpeed) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
    this.leftSpeed = leftSpeed;
    this.rightSpeed = rightSpeed;
    this.drivetrain = drivetrain;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
    System.out.println("Auto Drive Init");
    drivetrain.getDifferentialDrive().tankDrive(leftSpeed, rightSpeed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.getDifferentialDrive().tankDrive(leftSpeed, rightSpeed);
    System.out.println("Auto Drive Execute");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
