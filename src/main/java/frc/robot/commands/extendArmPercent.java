// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;

public class extendArmPercent extends CommandBase {
  /** Creates a new moveArm. */
  ArmSubsystem m_arm;
  double m_nativeUnits;
  public extendArmPercent(ArmSubsystem arm, double nativeUnits) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_arm = arm;
    m_nativeUnits = nativeUnits;
    addRequirements(m_arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_arm.setArmExtensionPercent(m_nativeUnits);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_arm.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
