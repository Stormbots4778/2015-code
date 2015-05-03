package org.usfirst.frc.team4778.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	public static Victor leftDrive = new Victor(0);
	public static Victor rightDrive = new Victor(1);
	public static Victor leftLiftMotor = new Victor(6);
	public static Victor rightLiftMotor = new Victor(7);
	
	public static Encoder leftEncoder = new Encoder(0,1,true,Encoder.EncodingType.k4X);
	public static Encoder rightEncoder = new Encoder(2,3,false,Encoder.EncodingType.k4X);
	
	public static Solenoid leftGripOut = new Solenoid(0);
	public static Solenoid leftGripIn = new Solenoid(1);
	public static Solenoid rightGripOut = new Solenoid(2);
	public static Solenoid rightGripIn = new Solenoid(3);
	
	public static Relay kickerOut = new Relay(0);
	public static Relay kickerIn = new Relay(1);
	
	public static DigitalInput toteStop = new DigitalInput(9);
	
	public RobotMap() {

	}
}
