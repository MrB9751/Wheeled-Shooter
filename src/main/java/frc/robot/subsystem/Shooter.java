package frc.robot.subsystem;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
    private final SparkMax m_intakemotor;
    public double distance;

    private Shooter () {
        m_intakemotor = new SparkMax (1, MotorType.kBrushless);
        // Replace 1 with the actual port number of your motor 
    } 

    public double getDistance() {
        return distance;
    }
    
    public static class instanceHolder { 
    private static final Shooter INSTANCE = new Shooter();
    }

    public static Shooter getInstance() {
    return instanceHolder.INSTANCE;
    }

    public void setSpeed (double speed) {
    m_intakemotor.set(speed);
    }
    
    public void stop() {
        m_intakemotor.set(0);
    }
}
