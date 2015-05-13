package org.usfirst.frc.team4778.robot.commands;

import org.usfirst.frc.team4778.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class GoToHeight extends Command {

    double setpoint;
    boolean finished = false;
    double wait;
    double waitAfter;

    public GoToHeight(double height, double delay) {
	// Use requires() here to declare subsystem dependencies
	// eg. requires(chassis);
	requires(Robot.leftLift);
	requires(Robot.rightLift);
	setpoint = height;
	wait = delay;
	waitAfter = 0.00;
    }

    public GoToHeight(double height) {
	requires(Robot.leftLift);
	requires(Robot.rightLift);
	setpoint = height;
	wait = 0.00;
	waitAfter = 0.00;
    }

    public GoToHeight(double height, double delayBefore, double delayAfter) {
	requires(Robot.leftLift);
	requires(Robot.rightLift);
	setpoint = height;
	wait = delayBefore;
	waitAfter = delayAfter;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
	finished = false;
	Timer.delay(wait);
	Robot.leftLift.getPIDController().setSetpoint(setpoint);
	Robot.rightLift.getPIDController().setSetpoint(setpoint);
	Timer.delay(waitAfter);
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
