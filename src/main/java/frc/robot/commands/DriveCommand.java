package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DriveCommand extends CommandBase {
    DrivetrainSubsystem drivetrain;
    DoubleSupplier left;
    DoubleSupplier right;

    public DriveCommand(DrivetrainSubsystem drivetrain, DoubleSupplier left, DoubleSupplier right) {
        this.drivetrain = drivetrain;
        this.left = left;
        this.right = right;

        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        drivetrain.arcadeDrive(left.getAsDouble(), right.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.arcadeDrive(0, 0);
    }
}
