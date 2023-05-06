// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ArmSubsystem extends SubsystemBase {
  /** Creates a new ArmSubsystem. */
  private final WPI_VictorSPX m_extension;
  private final CANSparkMax m_rotation;
  private final RelativeEncoder m_encoder;
  private final SparkMaxPIDController m_pid;
  public ArmSubsystem() {
    m_extension = new WPI_VictorSPX(RobotMap.ID_ARM_EXTENTION);
    m_extension.setInverted(false);
    m_rotation = new CANSparkMax(RobotMap.ID_ARM_ROTATION, MotorType.kBrushless);
    m_rotation.setInverted(false);
    m_encoder = m_rotation.getEncoder();
    m_pid = m_rotation.getPIDController();
    m_pid.setP(0.05);
   

  }
  public void setArmNative(double nativeUnits){
     m_pid.setReference(nativeUnits, CANSparkMax.ControlType.kPosition);
  }
  public void setRotationPercent(double percent){
    m_rotation.set(percent);
  }
  public void setArmExtensionPercent(double percent){
    m_extension.set(ControlMode.PercentOutput, percent);
  }
  public double getNative(){
    return m_encoder.getPosition();
  }
  public void stop(){
    m_extension.stopMotor();
    m_rotation.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    System.out.println(getNative());
  }

}
