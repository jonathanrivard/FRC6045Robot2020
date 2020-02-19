/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {
  /**
   * Creates a new Limelight.
   */

  //Declare private globals, check constuctor for info
  private NetworkTable table;
  private NetworkTableEntry tx;
  private NetworkTableEntry ty;
  private NetworkTableEntry ta;

  //Function getters for table entrys
  //  Get X Difference
  public double getX(){ return tx.getDouble(0.0); }
  //  Get Y Difference
  public double getY(){ return ty.getDouble(0.0); }
  //  Get Area
  public double getArea(){ return ta.getDouble(0.0); }

  //Functions for controlling the limelight
  public void setDriver(){
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(1);
  }

  public void setVision(){
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").forceSetNumber(3);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").forceSetNumber(0);
  }

  public Limelight() {
    //Init the network table from our current network table's limelight section
    table = NetworkTableInstance.getDefault().getTable("limelight");
    //Get each entry we want from the limelight table
    tx = table.getEntry("tx"); //X Difference
    ty = table.getEntry("ty"); //Y Difference
    ta = table.getEntry("ta"); //Area
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
