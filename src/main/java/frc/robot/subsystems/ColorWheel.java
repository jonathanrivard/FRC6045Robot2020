/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ColorWheel extends SubsystemBase {
  /**
   * Creates a new ColorWheel.
   */

  private Spark wheelMotor;
  private I2C.Port i2cPort; //Reference to RIO port that sensor is plugged into
  private ColorSensorV3 colorSensor; //Color Sensor object from api using rio port

  //Get which color the sensor is looking at
  public String getColor(){
    //Get the raw total color ticks
    double total = colorSensor.getRawColor().red + colorSensor.getRawColor().green + colorSensor.getRawColor().blue;
    //Get the percent of ticks each color is out of the total
    double red = ((double)colorSensor.getRawColor().red / total);
    double green = ((double)colorSensor.getRawColor().green / total);
    double blue = ((double)colorSensor.getRawColor().blue / total);

    //Init return string
    String output = "";
    //Figure out which color using percents
    if(red > 0.5){
      output += "RED";
    }else if (green > 0.5){
      if(red > 0.3){
        output += "YELLOW";
      }else {
        output += "GREEN";
      }
    }else if (blue > 0.4){
      output += "BLUE";
    }else {
      output += "NULL";
    }
    //Return the string
    return output;
  }

  //Get debug info
  public String getDebugString(){
    //Init return string
    String output = "";

    //Get the raw total color ticks
    double total = colorSensor.getRawColor().red + colorSensor.getRawColor().green + colorSensor.getRawColor().blue;
    double red = ((double)colorSensor.getRawColor().red / total);
    double green = ((double)colorSensor.getRawColor().green / total);
    double blue = ((double)colorSensor.getRawColor().blue / total);

    //Add percents to debug string
    output += "--------------------\n";
    output += "Red: " + red + "\n";
    output += "\tGreen: " + green + "\n";
    output += "\tBlue: " + blue + "\n";

    //Figure out the color using percents and add to debug string
    output += "Color: ";
    if(red > 0.5){
      output += "RED";
    }else if (green > 0.5){
      if(red > 0.3){
        output += "YELLOW";
      }else {
        output += "GREEN";
      }
    }else if (blue > 0.4){
      output += "BLUE";
    }else {
      output += "NULL";
    }
    output += "\n--------------------";

    //Return the debug info
    return output;    
  }

  public void setWheel(double d){ wheelMotor.set(d); }

  public ColorWheel() {
    wheelMotor = new Spark(Constants.PORT_MOTOR_COLOR_WHEEL);
    i2cPort = I2C.Port.kOnboard;
    colorSensor = new ColorSensorV3(i2cPort);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
