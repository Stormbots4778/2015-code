package org.usfirst.frc.team4778.robot.commands;

import org.usfirst.frc.team4778.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ResetEncoder extends Command {

    boolean finished = false;

    public ResetEncoder() {
	// Use requires() here to declare subsystem dependencies
	// eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
	finished = false;
	RobotMap.leftEncoderPrimary.reset();
	RobotMap.rightEncoderPrimary.reset();
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
	finished = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
