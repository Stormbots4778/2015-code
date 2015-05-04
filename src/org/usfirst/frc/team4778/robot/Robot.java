
package org.usfirst.frc.team4778.robot;

import org.usfirst.frc.team4778.robot.commands.Autonomous;
import org.usfirst.frc.team4778.robot.subsystems.Drivetrain;
import org.usfirst.frc.team4778.robot.subsystems.Kicker;
import org.usfirst.frc.team4778.robot.subsystems.LeftLift;
import org.usfirst.frc.team4778.robot.subsystems.RightLift;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	//public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static final LeftLift leftLift = new LeftLift();
	public static final RightLift rightLift = new RightLift();
	public static final Kicker kicker = new Kicker();
	public static final Drivetrain drivetrain = new Drivetrain();
	
	Solenoid leftGripOut = RobotMap.leftGripOut;
	Solenoid leftGripIn = RobotMap.leftGripIn;
	Solenoid rightGripOut = RobotMap.rightGripOut;
	Solenoid rightGripIn = RobotMap.rightGripIn;
	public static RobotDrive drive = new RobotDrive(RobotMap.leftDrive, RobotMap.rightDrive);
	Relay kickerOut = RobotMap.kickerOut;
	Relay kickerIn = RobotMap.kickerIn;
	
	public static OI oi;

    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
        // instantiate the command used for the autonomous period
		
        //autonomousCommand = new ExampleCommand();
        leftGripIn.set(true);
        leftGripOut.set(false);
        rightGripIn.set(true);
        rightGripOut.set(false);
        RobotMap.leftEncoder.reset();
        RobotMap.rightEncoder.reset();
        RobotMap.gyro.reset();
        
        //drive = new RobotDrive(RobotMap.leftDrive, RobotMap.rightDrive);
        
        drive.setInvertedMotor(MotorType.kRearLeft, true);
        drive.setInvertedMotor(MotorType.kRearRight, true);
        drive.setSafetyEnabled(false);
        
        kickerOut.set(Relay.Value.kForward);
        kickerIn.set(Relay.Value.kOff);
        
		RobotMap.leftEncoder.setDistancePerPulse(0.01309); //Encodon conversion ratio. Should actually be 0.02086214
		RobotMap.rightEncoder.setDistancePerPulse(0.01309);
		RobotMap.leftEncoder.setReverseDirection(true);
		RobotMap.leftEncoder.setSamplesToAverage(4);
		
		autonomousCommand = new Autonomous();
        
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        //drive.tankDrive(oi.leftJoystick, oi.rightJoystick);
        Robot.drivetrain.getPIDController().disable();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
