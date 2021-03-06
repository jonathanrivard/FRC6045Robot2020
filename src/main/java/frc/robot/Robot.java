/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private Joystick mainJoy;
  private Joystick rightJoy;
  private Joystick leftJoy;
  CameraServer server;
  private boolean turnThreeClicked;
  private boolean turnOnceClicked;
  


  private RobotContainer m_robotContainer;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    server = CameraServer.getInstance();
    server.startAutomaticCapture();
    m_robotContainer.getLimelight().setDriver();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
    m_robotContainer.getLimelight().setDriver();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
    CommandScheduler.getInstance().run();
    
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    //Init joysticks
    mainJoy = new Joystick(Constants.USB_MAIN_JOYSTICK);
    rightJoy = new Joystick(Constants.USB_RIGHT_JOYSTICK);
    leftJoy = new Joystick(Constants.USB_LEFT_JOYSTICK);
    //Make sure the limelight is in driver mode
    m_robotContainer.getLimelight().setDriver();
    //Start setting up Smart Dashboard
    SmartDashboard.putNumber("DriveType", Constants.SETTING_DRIVE_TYPE);
    //Init the booleans for activating the color wheel spinning
    turnThreeClicked = false;
    turnOnceClicked = false;
  }

  /**
   * This function is called periodically during operator control.
   */

  private void teleopPeriodicGenericCommands(){
    m_robotContainer.getShootWithJoystick().schedule();
    m_robotContainer.getIntakeWithJoystick().schedule();
    m_robotContainer.getLiftWithJoystick().schedule();
    m_robotContainer.getColorWheelWithJoystick().schedule();

    if(leftJoy.getRawButton(Constants.BUTTON_L_TURN_THREE_TIMES)){
      if(!turnThreeClicked){
        m_robotContainer.getTurnThreeTimes().resetValues();
        turnThreeClicked = true;
      }
      m_robotContainer.getTurnThreeTimes().schedule();
      //m_robotContainer.getTurnThreeTimes().printDebug();
    }else {
      turnThreeClicked = false;
    }

    if(leftJoy.getRawButton(Constants.BUTTON_L_TURN_ONCE)){
      if(!turnOnceClicked){
        m_robotContainer.getTurnOnce().resetValues();
        turnOnceClicked = true;
      }
      m_robotContainer.getTurnOnce().schedule();
      m_robotContainer.getTurnOnce().printDebug();
    }else {
      turnOnceClicked = false;
    }
  }

  @Override
  public void teleopPeriodic() {
    //Smart Dashboard
    SmartDashboard.putString("Color", m_robotContainer.getColorWheel().getColor());
    //System.out.println(NetworkTableInstance.getDefault().getTable("Smartdashboard").getEntry("DriveType").getDouble(-1.0));
    /*
    System.out.println("-----");
    System.out.println("Top: " + m_robotContainer.getIntake().getTopDistance());
    System.out.println("Bottom: " + m_robotContainer.getIntake().getBottomDistance());
    System.out.println("-----");
    */
    //Run all nessasary commands during teleop periodic
    if(Constants.SETTING_DRIVE_TYPE == 0){ //Tank Drive
      if(!rightJoy.getRawButton(Constants.BUTTON_R_LIMELIGHT)){ //Not limelight
        m_robotContainer.getLimelight().setDriver();
        m_robotContainer.getTankDrive().schedule();
        teleopPeriodicGenericCommands();
      }else { //Run limelight
        m_robotContainer.getLimelight().setVision();
        m_robotContainer.getShootWithLimelight().schedule();
      }
    }else { //Arcade Drive
      if(!mainJoy.getRawButton(Constants.BUTTON_M_LIMELIGHT)){ //Not limelight
        m_robotContainer.getLimelight().setDriver();
        m_robotContainer.getArcadeDrive().schedule();
        teleopPeriodicGenericCommands();
      }else { //Run limelight
        m_robotContainer.getLimelight().setVision();
        m_robotContainer.getShootWithLimelight().schedule();
      }
    }
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    m_robotContainer.getLimelight().setVision();
    m_robotContainer.getFollowWithLimelight().schedule();
  }
}
