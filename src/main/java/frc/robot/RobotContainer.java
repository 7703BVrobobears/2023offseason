// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.TankDriveCommand;
import frc.robot.commands.autoDropBackUp;
import frc.robot.commands.closeClaw;
import frc.robot.commands.extendArmPercent;
import frc.robot.commands.moveArmPercent;
import frc.robot.commands.openClaw;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
  private final Claw claw = new Claw();
  private final openClaw m_openClaw = new openClaw(claw);
  private final closeClaw m_closeClaw = new closeClaw(claw);
  private final SequentialCommandGroup m_Auto = new SequentialCommandGroup(new DriveCommand(drivetrain, ()->0.4, ()->0));
  private final ArmSubsystem m_arm = new ArmSubsystem();
  private final extendArmPercent m_extendOut = new extendArmPercent(m_arm, Vars.ARM_EXTENSION_MID_PERCENT);
  private final extendArmPercent m_extendIn = new extendArmPercent(m_arm, Vars.ARM_EXTENSION_OPPOSITE_MID_PERCENT);

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    System.out.println(m_driverController.getLeftX());
    drivetrain.setDefaultCommand(
      new DriveCommand(
        drivetrain,
        () -> m_driverController.getLeftY(),
        () -> m_driverController.getRightX()
      )
    
    );
    m_arm.setDefaultCommand(
      new moveArmPercent(m_arm, ()->(m_driverController.getLeftTriggerAxis() - m_driverController.getRightTriggerAxis()) * Vars.ARM_SCALAR)
    );

    


  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));

    m_driverController.a().onTrue(new openClaw(claw));
    m_driverController.b().onTrue(new closeClaw(claw));
    m_driverController.leftBumper().whileTrue(m_extendIn);
    m_driverController.rightBumper().whileTrue(m_extendOut);
    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
     return new autoDropBackUp(drivetrain, claw);
  }

  public Claw getClaw() {
    return this.claw;
  }
}
