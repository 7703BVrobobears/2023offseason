package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSub;

public class DriveCommand extends CommandBase {
    DriveTrainSub drivetrain;
    DoubleSupplier left;
    DoubleSupplier right;

    public DriveCommand(DriveTrainSub drivetrain, DoubleSupplier left, DoubleSupplier right) {
        this.drivetrain = drivetrain;
        this.left = left;
        this.right = right;

        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        drivetrain.drive(left.getAsDouble(), right.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.drive(0, 0);
    }
}
