/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class AutoIntake extends CommandBase {
  /**
   * Creates a new AutoIntake.
   */

  //Declare instance variables
  private Intake intake;
  private double wheelSpeed;
  private double elevatorSpeed;

  public AutoIntake(Intake intake, double wheelSpeed, double elevatorSpeed) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake);
    //Set instance variables to passed values
    this.intake = intake;
    this.wheelSpeed = wheelSpeed;
    this.elevatorSpeed = elevatorSpeed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    intake.setWheel(wheelSpeed);
    intake.setElevator(elevatorSpeed * -1);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
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
