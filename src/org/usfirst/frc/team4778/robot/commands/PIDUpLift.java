package org.usfirst.frc.team4778.robot.commands;

import org.usfirst.frc.team4778.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PIDUpLift extends Command {
	
	boolean finished = false;

    public PIDUpLift() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.leftLift);
    	requires(Robot.rightLift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	finished = false;
    	Timer.delay(0.15); //TODO Remove
    	for (int i=0; i<35; i++) {
    		double currenttime = i*0.05;
    		double setpoint = 16.2619-((85.7281)/(Math.pow(24.289,currenttime)+4.32489));
    		Robot.leftLift.getPIDController().setSetpoint(setpoint);
    		Robot.rightLift.getPIDController().setSetpoint(setpoint);
    		Timer.delay(0.05);
    	}
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
    	end();
    }
}
