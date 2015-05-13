package org.usfirst.frc.team4778.robot.commands;

import org.usfirst.frc.team4778.robot.Robot;
import org.usfirst.frc.team4778.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDDriveForward extends Command {

    boolean finished = false;

    public PIDDriveForward() {
	// Use requires() here to declare subsystem dependencies
	// eg. requires(chassis);
	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
	finished = false;
	// double i=0;
	// while (RobotMap.toteStop.get() == true && i < 3) {
	// Robot.drivetrain.getPIDController().setSetpoint(2.00);
	// Robot.drivetrain.enable();
	// Timer.delay(0.05);
	// i=i+0.05;
	// }
	// Robot.drivetrain.getPIDController().disable();
	// Timer.delay(1);
	// for (int i=0; i<40; i++) {
	// Robot.drive.arcadeDrive(-0.8, 0);
	// Timer.delay(0.05);
	// }
	// Robot.drive.arcadeDrive(0.0, 0.0);
	RobotMap.rightDriveEncoder.reset();
	Timer.delay(0.1);
	while (RobotMap.rightDriveEncoder.getDistance() > -65.50) {
	    Robot.drivetrain.getPIDController().setSetpoint(-0.25);
	    Robot.drivetrain.getPIDController().enable();
	    SmartDashboard.putNumber("Drive Encoder", RobotMap.rightDriveEncoder.getDistance());
	}
	Robot.drivetrain.getPIDController().disable();
	Robot.drive.arcadeDrive(0.00, 0.00);
	finished = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
	return finished;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
