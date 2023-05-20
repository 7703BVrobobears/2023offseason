// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class DriveTrainSub extends SubsystemBase {
  /** Creates a new DrivetrainSubsystem. */

  private final WPI_VictorSPX m_frontLeft, m_frontRight, m_rearLeft, m_rearRight;
  private final MotorControllerGroup m_left, m_right;
  private final DifferentialDrive m_drive;
  private final SlewRateLimiter m_rateLimiter;

  public DriveTrainSub() {
    m_frontLeft = new WPI_VictorSPX(RobotMap.ID_FRONT_LEFT);
    m_frontRight = new WPI_VictorSPX(RobotMap.ID_FRONT_RIGHT);
    m_rearLeft = new WPI_VictorSPX(RobotMap.ID_REAR_LEFT);
    m_rearRight = new WPI_VictorSPX(RobotMap.ID_REAR_RIGHT);

    m_left = new MotorControllerGroup(m_frontLeft, m_rearLeft);
    m_right = new MotorControllerGroup(m_frontRight, m_rearRight);
    m_left.setInverted(true);
    m_right.setInverted(false);

    m_drive = new DifferentialDrive(m_left, m_right);
    m_drive.setDeadband(0.02);
    m_drive.setMaxOutput(1.0
    
    );

    m_rateLimiter = new SlewRateLimiter(0.9, -0.9, 1);
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void arcadeDrive(double left, double right) {
    m_drive.arcadeDrive(left, right);
  }


}
