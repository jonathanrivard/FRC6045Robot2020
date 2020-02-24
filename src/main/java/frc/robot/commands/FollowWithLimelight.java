/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;

public class FollowWithLimelight extends CommandBase {
  /**
   * Creates a new FollowWithLimelight.
   */

  Limelight limelight;
  Drivetrain drivetrain;

  public FollowWithLimelight(Limelight limelight, Drivetrain drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(limelight);
    addRequirements(drivetrain);
    this.limelight = limelight;
    this.drivetrain = drivetrain;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double turnValue = 0.5 * (Math.abs(limelight.getX())/20);
    double forwardValue = 0;
    //forwardValue = -.09 * area + .9;
    double maxSpeed = 0.9;
    double targetArea = 3.2;
    forwardValue = ((maxSpeed - 0)/(0-targetArea))*limelight.getArea() + maxSpeed;
    if(Math.abs(limelight.getX()) > 1){
      turnValue = turnValue < 0.1 ? 0.1 : turnValue;
      if(limelight.getX() < 0){
        turnValue = Math.abs(turnValue) * -1;
      }else {
        turnValue = Math.abs(turnValue);
      }
    }else {
      turnValue = 0;
    }

    if(forwardValue > maxSpeed) forwardValue = maxSpeed;
    if(forwardValue < 0.2 && forwardValue > -0.2) forwardValue = 0;
    if(limelight.getArea() < 0.001) forwardValue = 0;
    //if(area > 11) forwardValue = -0.4;
    if(forwardValue < -0.4) forwardValue = -0.4;

    System.out.println("Forward: " + forwardValue + "     Turn: " + turnValue);
    //System.out.println(area);
    drivetrain.getDifferentialDrive().arcadeDrive(forwardValue, turnValue);
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
