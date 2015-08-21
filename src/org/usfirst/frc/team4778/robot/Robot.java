package org.usfirst.frc.team4778.robot;

import org.usfirst.frc.team4778.robot.subsystems.Drivetrain;
import org.usfirst.frc.team4778.robot.subsystems.Kicker;
import org.usfirst.frc.team4778.robot.subsystems.LeftLift;
import org.usfirst.frc.team4778.robot.subsystems.RightLift;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to each mode, as described in the IterativeRobot documentation. If you change the name of this class or the package after creating this project, you must also update the manifest file in the resource directory.
 */
public class Robot extends IterativeRobot {

    // public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
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
    public static Encoder leftEncoder = RobotMap.leftEncoderPrimary;
    public static Encoder rightEncoder = RobotMap.rightEncoderPrimary;
    public static boolean moving = false;
    public static OI oi;
    public static Command autonomousCommand;
    public static SendableChooser chooser;

    /**
     * This function is run when the robot is first started up and should be used for any initialization code.
     */
    @Override
    public void robotInit() {
	oi = new OI();
	chooser = new SendableChooser();
	chooser.addObject("yes", "yes");
	chooser.addObject("no", "no");
	SmartDashboard.putData("Run autonomous?", chooser);
	leftGripIn.set(true);
	leftGripOut.set(false);
	rightGripIn.set(true);
	rightGripOut.set(false);
	RobotMap.leftEncoderPrimary.reset();
	RobotMap.rightEncoderPrimary.reset();
	RobotMap.gyro.reset();
	// drive = new RobotDrive(RobotMap.leftDrive, RobotMap.rightDrive);
	drive.setInvertedMotor(MotorType.kRearLeft, true);
	drive.setInvertedMotor(MotorType.kRearRight, true);
	drive.setSafetyEnabled(false);
	kickerOut.set(Relay.Value.kForward);
	kickerIn.set(Relay.Value.kOff);
	RobotMap.leftEncoderPrimary.setDistancePerPulse(0.01309); // Encodon conversion ratio. Should actually be 0.02086214
	RobotMap.rightEncoderPrimary.setDistancePerPulse(0.01309);
	RobotMap.rightDriveEncoder.setDistancePerPulse(0.07363); // Inches per pulse
	RobotMap.rightDriveEncoder.setReverseDirection(true);
	RobotMap.leftEncoderPrimary.setReverseDirection(true);
	RobotMap.leftEncoderPrimary.setSamplesToAverage(4);
	// autonomousCommand = new Autonomous();
    }

    @Override
    public void disabledPeriodic() {
	Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
	// schedule the autonomous command (example)
	if (autonomousCommand != null)
	    autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
	Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
	// This makes sure that the autonomous stops running when
	// teleop starts running. If you want the autonomous to
	// continue until interrupted by another command, remove
	// this line or comment it out.
	if (autonomousCommand != null)
	    autonomousCommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit. You can use it to reset subsystems before shutting down.
     */
    @Override
    public void disabledInit() {
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
	Scheduler.getInstance().run();
	// drive.tankDrive(oi.leftJoystick, oi.rightJoystick);
	if (moving && RobotMap.leftEncoderPrimary.getStopped()) {
	    // leftEncoder = RobotMap.leftEncoderBackup;
	}
	if (moving && RobotMap.rightEncoderPrimary.getStopped())
	    rightEncoder = RobotMap.rightEncoderBackup;
	SmartDashboard.putNumber("Left Encoder", RobotMap.leftEncoderPrimary.getDistance());
	SmartDashboard.putNumber("Right Encoder", RobotMap.rightEncoderPrimary.getDistance());
	SmartDashboard.putNumber("Gyro Rate", RobotMap.gyro.getRate());
	if (RobotMap.leftZeroSwitch.get() == false)
	    RobotMap.leftEncoderPrimary.reset();
    }

    /**
     * This function is called periodically during test mode
     */
    @Override
    public void testPeriodic() {
	LiveWindow.run();
    }
}
