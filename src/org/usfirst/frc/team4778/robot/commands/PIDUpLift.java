package org.usfirst.frc.team4778.robot.commands;

import org.usfirst.frc.team4778.robot.OI;
import org.usfirst.frc.team4778.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class PIDUpLift extends Command {

    boolean finished = false;

    public PIDUpLift() {
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
	Timer.delay(0.15); // TODO Remove
	long startTime = System.nanoTime();
	for (int i = 0; i < 40; i++) {
	    double currenttime = i * 0.05;
	    double setpoint = -0.5878803001357 * Math.pow(currenttime, 4) - 0.4897807434328 * Math.pow(currenttime, 3) + 4.3823203142584 * Math.pow(currenttime, 2) + 5.7909797472838 * currenttime + 0.1623349065568;
	    Robot.leftLift.getPIDController().setSetpoint(setpoint);
	    Robot.rightLift.getPIDController().setSetpoint(setpoint);
	    Robot.drive.tankDrive(OI.leftJoystick, OI.rightJoystick);
	    Timer.delay(0.05);
	}
	// while ((System.nanoTime() - startTime) / 1.0e9 < 2) {
	// double elapsedTime = (System.nanoTime() - startTime) / 1.0e9;
	// double setpoint = 17.06 - 62.42 / (Math.pow(7.28, elapsedTime) + 2.69);
	// Robot.leftLift.getPIDController().setSetpoint(setpoint);
	// Robot.rightLift.getPIDController().setSetpoint(setpoint);
	// }
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
