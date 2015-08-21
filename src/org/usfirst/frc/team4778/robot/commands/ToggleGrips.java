package org.usfirst.frc.team4778.robot.commands;

import org.usfirst.frc.team4778.robot.Robot;
import org.usfirst.frc.team4778.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleGrips extends Command {

    boolean finished = false;

    public ToggleGrips() {
	// Use requires() here to declare subsystem dependencies
	// eg. requires(chassis);
	requires(Robot.leftLift);
	requires(Robot.rightLift);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
	finished = false;
	boolean leftOut = RobotMap.leftGripOut.get();
	boolean leftIn = RobotMap.leftGripIn.get();
	boolean rightOut = RobotMap.rightGripOut.get();
	boolean rightIn = RobotMap.rightGripIn.get();
	RobotMap.leftGripOut.set(!leftOut);
	RobotMap.leftGripIn.set(!leftIn);
	RobotMap.rightGripOut.set(!rightOut);
	RobotMap.rightGripIn.set(!rightIn);
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
	end();
    }
}
