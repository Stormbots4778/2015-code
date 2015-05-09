package org.usfirst.frc.team4778.robot.commands;

import org.usfirst.frc.team4778.robot.Robot;
import org.usfirst.frc.team4778.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ForwardToScore extends Command {
	
	boolean finished = false;

    public ForwardToScore() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	finished = false;
    	//for(int i=0; i<28; i++) {
    	//	double output = 2.786158e-6*Math.pow(i, 4)-2.28079e-4*Math.pow(i,3)+0.0040474829*Math.pow(i, 2)-0.0190876811*i+1.004480523;
    	//	Robot.drive.arcadeDrive(output, 0);
    	//}
    	
    	RobotMap.rightDriveEncoder.reset();
    	while (RobotMap.rightDriveEncoder.getDistance() > -120) {
    		Robot.drive.arcadeDrive(-0.9, 0);
    	}
    	Robot.drive.arcadeDrive(0, 0);
    	finished = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
