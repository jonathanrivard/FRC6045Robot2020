/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ColorWheel;

public class TurnOnce extends CommandBase {
  /**
   * Creates a new TurnOnce.
   */

  private ColorWheel colorWheel;
  String lastColor;
  String currentColor;
  int red, green, yellow, blue;
  String[] lastTen;

  public TurnOnce(ColorWheel colorWheel) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(colorWheel);
    this.colorWheel = colorWheel;
    resetValues();
  }
  
  public void resetValues(){
    red = 0;
    green = 0;
    yellow = 0;
    blue = 0;
    lastColor = "-1";
    lastTen = new String[10];
    for(int i = 0; i < lastTen.length; i++){
      lastTen[i] = "NULL";
    }
  }

  public void printDebug(){
    System.out.println("-----");
    System.out.println("Turn Three Times Command Debug");
    System.out.println("RED: " + red);
    System.out.println("BLUE: " + blue);
    System.out.println("YELLOW: " + yellow);
    System.out.println("GREEN: " + green);
    System.out.println("-----");
  }

  private void addValue(String s){
    for(int i = 0; i < lastTen.length-1; i++){
      lastTen[i] = lastTen[i+1];
    }
    lastTen[9] = s;
  }

  private String averageColor(){
    int r = 0, g = 0, y = 0, b = 0;
    for(int i = 0; i < lastTen.length; i++){
      if(lastTen[i].equals("RED")){
        r++;
      }else if(lastTen[i].equals("GREEN")){
        g++;
      }else if(lastTen[i].equals("YELLOW")){
        y++;
      }else if(lastTen[i].equals("BLUE")){
        b++;
      }else if(lastTen[i].equals("NULL")){
        //Nothing
      }else {
        System.out.println("ERROR: Turn Three Times not getting a valid color in array average!");
      }
    }

    if(r > 5){
      return "RED";
    }else if(g > 5){
      return "GREEN";
    }else if(y > 5){
      return "YELLOW";
    }else if(b > 5){
      return "BLUE";
    }else {
      return "NULL";
    }

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    /*
    red = 0;
    green = 0;
    yellow = 0;
    blue = 0;
    lastColor = "-1"; */
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    addValue(colorWheel.getColor());
    currentColor = averageColor();
    //System.out.println("Current Color: " + currentColor);
    if(!currentColor.equals(lastColor)){
      if(currentColor.equals("RED")){
        red++;
      }else if(currentColor.equals("GREEN")){
        green++;
      }else if(currentColor.equals("YELLOW")){
        yellow++;
      }else if(currentColor.equals("BLUE")){
        blue++;
      }else if(currentColor.equals("NULL")){
        //Nothing
      }else {
        System.out.println("ERROR: Turn Three Times not getting a valid color!");
      }
    }

    if((red + blue + green + yellow) < 8){
      colorWheel.setWheel(Constants.SCALER_COLOR_WHEEL);
    }else {
      colorWheel.setWheel(0.0);
    }

    lastColor = currentColor;
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
