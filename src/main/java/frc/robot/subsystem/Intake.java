package frc.robot.subsystem;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    private final AnalogInput m_intakeSensor;
    private final SparkMax m_intakemotor;
    public double distance;

    private Intake () {
        m_intakemotor = new SparkMax (1, MotorType.kBrushless);
        // Replace 1 with the actual port number of your motor 

        m_intakeSensor = new AnalogInput(0); // Replace 0 with the actual port number of your sensor
    } 

    public void controlMotorBasedOnDistance() {
        // Calculate distance in cm
        double voltag = m_intakeSensor.getVoltage();
        distance = voltage / 0.00977; // Convert voltage to distance in cm

        // Turn motor on if distance is less than 50cm, otherwise turn it off
        if (distance < 50) {
            m_intakemotor.set(0.5); // Set motor speed to 50% power
        } else {
            m_intakemotor.set(0); // Turn motor off
        }
    }

    public double getDistance() {
        return distance;
    }
    
    public static class instanceHolder { 
    private static final Intake INSTANCE = new Intake();
    }

    public static Intake getInstance() {
    return instanceHolder.INSTANCE;
    }

    public void setSpeed (double speed) {
    m_intakemotor.set(speed);
    }
    
    public void stop() {
        m_intakemotor.set(0);
    }
}
