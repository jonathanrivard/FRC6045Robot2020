/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.ArrayList;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.ShooterWheel;

public class ShootWithLimelight extends CommandBase {
  /**
   * Creates a new ShootWithLimelight.
   */

  private Limelight limelight;
  private ShooterWheel shooterWheel;
  private Intake intake;
  private Drivetrain drivetrain;
  private ArrayList<Double> lastValues;
  private double startTime;


  public ShootWithLimelight(Limelight limelight, ShooterWheel shooterWheel, Intake intake, Drivetrain drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    //Add all the subsystems that the command requires
    addRequirements(limelight);
    addRequirements(shooterWheel);
    addRequirements(intake);
    addRequirements(drivetrain);
    //Set the subsystems that the command requires
    this.limelight = limelight;
    this.shooterWheel = shooterWheel;
    this.intake = intake;
    this.drivetrain = drivetrain;
    //Init the lastValues ArrayList
    lastValues = new ArrayList<Double>();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //Get the start time
    startTime = System.currentTimeMillis();
    //Set the shooterwheel to full force forward so it's always running if the command is active
    shooterWheel.setWheel(-1.0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Get X Difference from limelight
    double xDiff = limelight.getX();
    //Declare variable to check if we can see the reflective tape
    boolean tape;

    //If our array list is still being fed up to 10
    if(lastValues.size() < 10){
      //Add the current value
      lastValues.add(xDiff);
      //Pretend we can't see the tape so the robot doesn't move
      tape = false;
    }else {
      //There is 10 in the array
      //Cycle a new value into the array
      lastValues.remove(0);
      lastValues.add(xDiff);
      //Declare some variables to keep track of
      int zeros = 0; //How many "0.0"s there are (probably can't see tape)
      double average = 0.0; //The average of the numbers that arn't zero
      //Cycle through array list and find amount of zeros and average of nonzeros
      for(int i = 0; i < lastValues.size(); i++){
        if(lastValues.get(i) == 0.0){
          zeros++;
        }else {
          average += lastValues.get(i);
        }
      }
      //Divide to find average
      average /= lastValues.size() - zeros;
      //Set the xDiff to the average of the nonzeros in the array
      xDiff = average;
      //If more than 7 of the values are zeros, be probably are not getting a valid reading
      if(zeros >= 7){
        tape = false;
      }else {
        tape = true;
      }

    }

    //If the limelight can see the tape
    if(tape){
      //If within the threshhold for precision for x difference
      if(Math.abs(xDiff) < Constants.THRESHHOLD_LIMELIGHT_X){
        //Freeze the drivetrain
        drivetrain.getDifferentialDrive().arcadeDrive(0.0, 0.0);
        //Make sure the wheel has had enough time to spin up
        if(System.currentTimeMillis() - startTime < Constants.LIMELIGHT_DELAY_START){
          //If debug
          if(Constants.LIMELIGHT_DEBUG){
            System.out.println("Spinning Up Still");
          }
          //The wheel hasn't had enough time to spin up so return
          return;
        }
        //Feed the spinning wheel
        intake.setElevator(Constants.SCALER_INTAKE_ELEVATOR * -1);
        //If debug
        if(Constants.LIMELIGHT_DEBUG){
          System.out.println("Shooting");
        }
      }else {
        //If the limelight can see the tape, but the x difference is too great
        //Stop Shooting and Turn
        //Convert the xDiff into a turn value
        double turnValue = Constants.LIMELIGHT_TURN_MAX_SPEED * (xDiff / Constants.LIMELIGHT_TURN_MAX_X);
        
        //Make sure the turn value is not below the minnimum speed either negitive or positive
        if(Math.abs(turnValue) < Constants.LIMELIGHT_TURN_MIN_SPEED){
          //If the turnValue is below the minnimum speed, set it to the minimum speed
          turnValue = turnValue > 0 ? Constants.LIMELIGHT_TURN_MIN_SPEED : Constants.LIMELIGHT_TURN_MIN_SPEED * -1;
        }

        //If debug
        if(Constants.LIMELIGHT_DEBUG){
          System.out.println("Turn Value: " + turnValue);
        }

        //Set the drivetrain to the turnValue and the rest of the subsystems' speed to zero
        drivetrain.getDifferentialDrive().arcadeDrive(0.0, turnValue);
        intake.setElevator(0.0);
      }
    }
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
