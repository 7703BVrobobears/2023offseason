// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Vars;
import frc.robot.Constants.DriveTrainConstants;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DriveTrainSub;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class autoDropBackUp extends SequentialCommandGroup {
  /** Creates a new autoDropBackUp. */
  public autoDropBackUp(DriveTrainSub drive, Claw claw) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ParallelDeadlineGroup(new WaitCommand(Vars.AUTO_TIME),new DriveCommand(drive, ()->Vars.AUTO_PERCENT, ()->0))
    );
  }
}
