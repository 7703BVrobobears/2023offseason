// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Claw;

public class closeClaw extends CommandBase {
  /** Creates a new closeClaw. */
  private final Claw m_claw;
  private int counter = 0;
  private final int loopCount = 5;
  public closeClaw(Claw claw) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_claw = claw;
    addRequirements(claw);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    counter = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("Closing Claw");
    m_claw.closeClaw();
    counter++;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_claw.neutralClaw();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return counter > loopCount ;
  }
}
