package org.usfirst.frc.team4778.robot.subsystems;

import org.usfirst.frc.team4778.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class RightLift extends PIDSubsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	Victor rightLiftMotor = RobotMap.rightLiftMotor;
	Encoder rightEncoder = RobotMap.rightEncoderPrimary;
	
	public RightLift() {
		super ("Right Lift", 0.75, 0.0, 0.005);
		getPIDController().setOutputRange(-1, 1);
	    getPIDController().setAbsoluteTolerance(0.05);
		getPIDController().setSetpoint(0.16);
		getPIDController().enable();
	}
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
    	return rightEncoder.getDistance();
    }
    
    protected void usePIDOutput(double output) {
    	rightLiftMotor.pidWrite(output); //TODO Change back to output, FIXED
    }
    
    public void goUp(long timer) {
    	System.out.println("Right Going Up");
    	System.out.flush();
    	//double timer = System.nanoTime();
    	//double setpoint;
    	//while(((System.nanoTime()-timer)/1.0e9) < 6) {
    		//timer = i*0.05;
    	//	setpoint = getUpSetpoint((System.nanoTime()-ParallelUpLift.timer)/1.0e9);
    	//	getPIDController().setSetpoint(setpoint);
    	//	try {
		//		Thread.sleep(20);
		//	} catch (InterruptedException e) {
				// TODO Auto-generated catch block
		//		Thread.currentThread().interrupt();
		//	}
    	//}
    	//getPIDController().setSetpoint(16.00);
    	double timer2 = 0;
    	double setpoint;
    	for (int i=0; i<40; i++) {
    		timer2 = i*0.05;
    		setpoint = getUpSetpoint(timer2);
    		getPIDController().setSetpoint(setpoint);
    		Timer.delay(0.05);
    	}
    }
    
    public void goDown() {
    	//double timer = 0;
    	//double setpoint;
    	//for(int i=0; i<40; i++) {
    	//	timer = i*0.05;
    	//	setpoint = getDownSetpoint(timer);
    	//	getPIDController().setSetpoint(setpoint);
    	//	getPIDController().enable();
    	//	Timer.delay(0.05);
    	//}
    	getPIDController().setSetpoint(0.16);
    }
    
    public double getUpSetpoint(double time) {
    	double setpoint = 15.9501998-((133.66218)/(Math.pow(15.963983,time)+7.4585183387476));
    	return setpoint;
    }
    
    public double getDownSetpoint(double time) {
    	double setpoint = ((129.02)/((Math.pow(56.1074,time)+7.18604)))+0.15803;
    	return setpoint;
    }
    
}

