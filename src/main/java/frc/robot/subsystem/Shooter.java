package frc.robot.subsystem;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
    // Lead & follower motors
    private final SparkMax m_leadmotor;
    private final SparkMax m_follower;

    private final ColorSensorV3 colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
    private final ColorMatch colorMatcher = new ColorMatch();

    // Reference colors (adjust if needed)
    private final Color kRedTarget  = new Color(0.407, 0.408, 0.186);
    private final Color kBlueTarget = new Color(0.145, 0.384, 0.471);

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

        colorMatcher.addColorMatch(kRedTarget);
        colorMatcher.addColorMatch(kBlueTarget);
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

        @Override
public void periodic() {

    ColorMatchResult match = colorMatcher.matchClosestColor(colorSensor.getColor());
    if (match.confidence < 0.90) { m_leadmotor.set(0); return; }

    boolean piecePresent = colorSensor.getProximity() > 1000;
        if (!piecePresent) {
        m_leadmotor.set(0);
        return;
        }

        if (match.color == kRedTarget) {
            m_leadmotor.set(1.0);          // Full speed
            }
        else if (match.color == kBlueTarget) {
            m_leadmotor.set(0.20);         // 20% speed
            }
        else {
            m_leadmotor.set(0.0);          // Default OFF
            }
        }
}
