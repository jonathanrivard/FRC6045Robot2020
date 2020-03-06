/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.ShooterWheel;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutonomousCommand extends SequentialCommandGroup {
  /**
   * Creates a new AutonomousCommand.
   */
  public AutonomousCommand(ShooterWheel m_shooterWheel, Intake m_intake, Drivetrain m_drivetrain) {
    addCommands(
      new AutoShoot(m_shooterWheel, 1.0),
      new WaitCommand(2),
      new AutoIntake(m_intake, 0.0, 0.3),
      new WaitCommand(4),
      new AutoShoot(m_shooterWheel, 0.0),
      new AutoIntake(m_intake, 0.0, 0.0),
      new AutoDrive(m_drivetrain, -0.8, -0.8),
      new WaitCommand(1),
      new AutoDrive(m_drivetrain, -0.8, -0.8),
      new WaitCommand(1),
      new AutoDrive(m_drivetrain, -0.8, -0.8),
      new WaitCommand(1),
      new AutoDrive(m_drivetrain, -0.8, -0.8),
      new WaitCommand(1),
      new AutoDrive(m_drivetrain, 0.0, 0.0));
  }
}
