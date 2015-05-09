package org.usfirst.frc.team4778.robot.commands;

import org.usfirst.frc.team4778.robot.Robot;
import org.usfirst.frc.team4778.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToAngle extends Command {
	
	int angle;
	boolean finished = false;
	boolean pid;
	PIDController turnPIDController = new PIDController(0.25, 0.0, 0.0, RobotMap.rightDriveEncoder, RobotMap.rightDrive);
	//PIDController angularRatePIDController = new PIDController(0.25, 0.0, 0.0, RobotMap.gyro, RobotMap.leftDrive);

    public TurnToAngle(int desiredAngle, boolean pidEnabled) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	angle = desiredAngle;
    	turnPIDController.setOutputRange(-0.9, 0.9);
    	turnPIDController.disable();
    	pid = pidEnabled;
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
    	
    	finished = false;
    	
    	//if ((angle-RobotMap.gyro.getAngle()) < 0) {
    	//	while ((angle-RobotMap.gyro.getAngle()) < 0) {
    	//		Robot.drive.tankDrive(-1.00, 0.8);
    	//	}
    	//}
    	//else if ((angle-RobotMap.gyro.getAngle()) > 0) {
    	//	while ((angle-RobotMap.gyro.getAngle()) > 0) {
    	//		Robot.drive.tankDrive(1.00, -0.9);
    	//	}
    	//}
    	//Robot.drive.tankDrive(0.00, 0.00);
    	
    	turnPIDController.setSetpoint(0.00);
    	RobotMap.rightDriveEncoder.reset();
    	if ((angle-RobotMap.gyro.getAngle()) < 0) {
    		while ((angle-RobotMap.gyro.getAngle()) < 0) {
    			RobotMap.leftDrive.set(1.00);
    			if (pid == true) {
    				turnPIDController.enable();
    			}
    			if (pid == false) {
    				RobotMap.rightDrive.set(1.00);
    			}
    		}
    	}
    	else if ((angle-RobotMap.gyro.getAngle()) > 0) {
    		while ((angle-RobotMap.gyro.getAngle()) > 0) {
    			double currentAngle = RobotMap.gyro.getAngle();
    			double power = 1-Math.pow(0.98471, currentAngle);
    			RobotMap.leftDrive.set(power);
    			turnPIDController.enable();
    		}
    		//RobotMap.leftDrive.set(0.90);
    		//Timer.delay(0.15);
    		RobotMap.leftDrive.set(0.00);
    	}
    	turnPIDController.disable();
    	Robot.drive.tankDrive(0.00, 0.00);
    	
    	
    	finished = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.getPIDController().disable();
    	turnPIDController.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
