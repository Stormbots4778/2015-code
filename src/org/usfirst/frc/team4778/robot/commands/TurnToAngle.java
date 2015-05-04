package org.usfirst.frc.team4778.robot.commands;

import org.usfirst.frc.team4778.robot.Robot;
import org.usfirst.frc.team4778.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToAngle extends Command {
	
	int angle;

    public TurnToAngle(int desiredAngle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	angle = desiredAngle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.drivetrain.getPIDController().setSetpoint(angle);
    	//if (angle < -15) {
    	//	Robot.drivetrain.getPIDController().setAbsoluteTolerance(3.00);
    	//}
    	//else {
    	//	Robot.drivetrain.getPIDController().setAbsoluteTolerance(1.00);
    	//}
    	//Robot.drivetrain.getPIDController().enable();
    	if ((angle-RobotMap.gyro.getAngle()) < 0) {
    		while ((angle-RobotMap.gyro.getAngle()) < 0) {
    			Robot.drive.tankDrive(-1.00, 0.9);
    		}
    	}
    	else if ((angle-RobotMap.gyro.getAngle()) > 0) {
    		while ((angle-RobotMap.gyro.getAngle()) > 0) {
    			Robot.drive.tankDrive(1.00, -0.9);
    		}
    	}
    	Robot.drive.tankDrive(0.00, 0.00);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.drivetrain.getPIDController().onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.getPIDController().disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
