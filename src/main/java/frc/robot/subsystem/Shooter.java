package frc.robot.subsystem;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
    // Lead & follower motors
    private final SparkMax m_leadmotor;
    private final SparkMax m_follower;

    private Shooter() {
        // CAN IDs are examples – change to your real IDs
        m_leadmotor = new SparkMax(1, MotorType.kBrushless);
        m_follower  = new SparkMax(2, MotorType.kBrushless);

        // Configure the follower
        SparkMaxConfig followerConfig = new SparkMaxConfig();
        followerConfig
            // Make this SPARK follow the leader (false = not inverted)
            .follow(m_leadmotor, false);
            // optional: .idleMode(SparkBaseConfig.IdleMode.kBrake)
            // optional: .smartCurrentLimit(40);

        // Apply config to follower
        m_follower.configure(
            followerConfig,
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters
        );
    }

    // Singleton boilerplate
    public static class instanceHolder {
        private static final Shooter INSTANCE = new Shooter();
    }

    public static Shooter getInstance() {
        return instanceHolder.INSTANCE;
    }

    public void setSpeed(double speed) {
        // Only need to command the leader; follower mirrors it in hardware
        m_leadmotor.set(speed);
    }

    public void stop() {
        m_leadmotor.stopMotor();
    }
}