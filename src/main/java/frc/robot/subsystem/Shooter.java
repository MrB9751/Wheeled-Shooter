package frc.robot.subsystem;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
    // This declares a motor controller for the shooter
    private final SparkMax m_leadmotor;

    private Shooter () {
        // This initializes the lead motor controller for the shooter
        m_leadmotor = new SparkMax (1, MotorType.kBrushless);  // Replace 1 with the actual port number of your motor 
    } 

  //  This is the Singleton design pattern: It creates the single instance of the Shooter class to be used by other classes
    public static class instanceHolder { 
    private static final Shooter INSTANCE = new Shooter();
    }

    // This is the "getter" method that other classes use to obtain the single instance of the Shooter class
    public static Shooter getInstance() {
    return instanceHolder.INSTANCE;
    }

    public void setSpeed (double speed) {
    m_leadmotor.set(speed);
    }
    
    public void stop() {
        m_leadmotor.set(0);
    }
}
