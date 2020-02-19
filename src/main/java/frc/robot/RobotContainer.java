/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ArcadeDrive;
//import frc.robot.commands.AutoDrive;
import frc.robot.commands.AutoIntake;
import frc.robot.commands.AutoShoot;
import frc.robot.commands.AutonomousCommand;
import frc.robot.commands.IntakeWithJoystick;
import frc.robot.commands.LiftWithJoystick;
import frc.robot.commands.ShootWithJoystick;
import frc.robot.commands.ShootWithLimelight;
import frc.robot.commands.TankDrive;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.ShooterWheel;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //Subsystems
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final ShooterWheel m_shooterWheel = new ShooterWheel();
  private final Intake m_intake = new Intake();
  private final Limelight m_limelight = new Limelight();
  private final Lift m_lift = new Lift();
  //Commands
  private final ArcadeDrive m_arcadeDrive = new ArcadeDrive(m_drivetrain);
  private final TankDrive m_tankDrive = new TankDrive(m_drivetrain);
  private final ShootWithJoystick m_shootWithJoystick = new ShootWithJoystick(m_shooterWheel);
  private final IntakeWithJoystick m_intakeWithJoystick = new IntakeWithJoystick(m_intake);
  private final ShootWithLimelight m_shootWithLimelight = new ShootWithLimelight(m_limelight, m_shooterWheel, m_intake, m_drivetrain);
  private final LiftWithJoystick m_liftWithJoystick = new LiftWithJoystick(m_lift);
  //Command Getters
  public ArcadeDrive getArcadeDrive(){ return m_arcadeDrive; }
  public TankDrive getTankDrive(){ return m_tankDrive; }
  public ShootWithJoystick getShootWithJoystick(){ return m_shootWithJoystick; }
  public IntakeWithJoystick getIntakeWithJoystick(){ return m_intakeWithJoystick; }
  public ShootWithLimelight getShootWithLimelight() { return m_shootWithLimelight; }
  public LiftWithJoystick getLiftWithJoystick() { return m_liftWithJoystick; }
  //Subsystem Getters
  public Limelight getLimelight(){ return m_limelight; }


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    AutonomousCommand command = new AutonomousCommand(m_shooterWheel, m_intake);
    /*
    command.addCommands(new AutoDrive(m_drivetrain, 0.5, 0.5), new WaitCommand(1)); //Drive forward for 1 second
    command.addCommands(new AutoDrive(m_drivetrain, 0.0, 0.0));
    command.addCommands(new AutoDrive(m_drivetrain, -0.4, 0.4), new WaitCommand(1));
    command.addCommands(new ShootWithLimelight(m_limelight, m_shooterWheel, m_intake, m_drivetrain));
    */

    return command;
  }
}
