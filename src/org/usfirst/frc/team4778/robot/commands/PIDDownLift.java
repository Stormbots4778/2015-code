package org.usfirst.frc.team4778.robot.commands;

import org.usfirst.frc.team4778.robot.Robot;
import org.usfirst.frc.team4778.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PIDDownLift extends Command {
	boolean finished;

    public PIDDownLift() {
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
    	for (int i=0; i<20; i++) {
    		double currenttime = i*0.05;
    		double setpoint = ((24.4495)/((Math.pow(6.2743,currenttime)+0.251242)))-3.58679;
    		Robot.leftLift.getPIDController().setSetpoint(setpoint);
    		Robot.rightLift.getPIDController().setSetpoint(setpoint);
    		if (i == 11) {
    			RobotMap.leftGripIn.set(true);
    			RobotMap.leftGripOut.set(false);
    			RobotMap.rightGripIn.set(true);
    			RobotMap.rightGripOut.set(false);
    		}
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
