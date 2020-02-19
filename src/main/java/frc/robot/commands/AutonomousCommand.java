/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
//import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.ShooterWheel;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutonomousCommand extends SequentialCommandGroup {
  /**
   * Creates a new AutonomousCommand.
   */
  public AutonomousCommand(ShooterWheel m_shooterWheel, Intake m_intake) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new WaitCommand(2.0), new AutoShoot(m_shooterWheel, 0.0));
    //super(/*new AutoShoot(m_shooterWheel, -1.0), /*new WaitCommand(1.0),*/ new AutoIntake(m_intake, 0.0, Constants.SCALER_INTAKE_ELEVATOR)/*, new WaitCommand(5), new AutoIntake(m_intake, 0.0, 0.0), new AutoShoot(m_shooterWheel, 0.0)*/);
    /*List<Command> commands = new ArrayList<Command>();
    commands.add(new AutoShoot(m_shooterWheel, -1.0));
    commands.add(new WaitCommand(2.0));
    commands.add(new AutoShoot(m_shooterWheel, 0.0));*/
  }
}
