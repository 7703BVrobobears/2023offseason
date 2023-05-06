// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveTrainConstants;

public class DriveTrainSub extends SubsystemBase {
  /** Creates a new DriveTrainSub. */
  private final WPI_VictorSPX m_frontLeft, m_frontRight, m_backLeft, m_backRight;
  private final MotorControllerGroup m_motorLeft, m_motorRight;
  private final DifferentialDrive m_difDrive;
  public DriveTrainSub() {
    m_frontLeft = new WPI_VictorSPX(DriveTrainConstants.kFrontLeft);
    m_frontRight = new WPI_VictorSPX(DriveTrainConstants.kFrontRight);
    m_backLeft = new WPI_VictorSPX(DriveTrainConstants.kBackLeft);
    m_backRight = new WPI_VictorSPX(DriveTrainConstants.kBackRight);
    
    m_motorLeft = new MotorControllerGroup(m_backLeft, m_frontLeft);
    m_motorRight = new MotorControllerGroup(m_frontRight, m_backRight);
    m_motorLeft.setInverted(true);
    m_motorRight.setInverted(false);
    m_difDrive = new DifferentialDrive(m_motorLeft, m_motorRight);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void drive(double xDrive, double zRot){
    m_difDrive.arcadeDrive(xDrive, zRot);
  }
}
