// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystem.Intake; // Import the intake subsystem

public class RobotContainer {
  // Declare the controller
  private final CommandXboxController m_controller1;

  // Access the singleton instance of the intake subsystem
  private final Intake m_intakeSubsystem;

  public RobotContainer() {
    // Initialize the controller
    m_controller1 = new CommandXboxController(1);

    // Get the singleton instance of the intake subsystem
    m_intakeSubsystem = Intake.getInstance();

    configureBindings();
  }

  private void configureBindings() {
    // Bind the X button to set the intake motor speed
    m_controller1.x().onTrue(
      new InstantCommand(() -> m_intakeSubsystem.setSpeed(0.2))
    );

    // Optionally, bind another button to stop the intake motor
    m_controller1.b().onTrue(
      new InstantCommand(() -> m_intakeSubsystem.stop())
    );
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}