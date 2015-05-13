package org.usfirst.frc.team4778.robot.commands;

import org.usfirst.frc.team4778.robot.Robot;
import org.usfirst.frc.team4778.robot.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;

public class ToggleKicker extends Command {

    boolean finished = false;

    public ToggleKicker() {
	// Use requires() here to declare subsystem dependencies
	// eg. requires(chassis);
	requires(Robot.kicker);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
	finished = false;
	if (RobotMap.kickerOut.get() == Relay.Value.kForward)
	    RobotMap.kickerOut.set(Relay.Value.kOff);
	else if (RobotMap.kickerOut.get() == Relay.Value.kOff)
	    RobotMap.kickerOut.set(Relay.Value.kForward);
	if (RobotMap.kickerIn.get() == Relay.Value.kForward)
	    RobotMap.kickerIn.set(Relay.Value.kOff);
	else if (RobotMap.kickerIn.get() == Relay.Value.kOff)
	    RobotMap.kickerIn.set(Relay.Value.kForward);
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
