package org.usfirst.frc.team4778.robot.commands;

import org.usfirst.frc.team4778.robot.OI;
import org.usfirst.frc.team4778.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IncrementalLift extends Command {

    boolean up;

    public IncrementalLift(boolean increment) {
	// Use requires() here to declare subsystem dependencies
	// eg. requires(chassis);
	requires(Robot.leftLift);
	requires(Robot.rightLift);
	up = increment;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
	if (up == true && OI.leftJoystick.getRawButton(5)) {
	    Robot.leftLift.getPIDController().setSetpoint(Robot.leftLift.getPIDController().getSetpoint() + 0.25);
	    Robot.rightLift.getPIDController().setSetpoint(Robot.rightLift.getPIDController().getSetpoint() + 0.25);
	} else if (up == false && OI.leftJoystick.getRawButton(4)) {
	    Robot.leftLift.getPIDController().setSetpoint(Robot.leftLift.getPIDController().getSetpoint() - 0.25);
	    Robot.rightLift.getPIDController().setSetpoint(Robot.rightLift.getPIDController().getSetpoint() - 0.25);
	}
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
	return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
	end();
    }
}
