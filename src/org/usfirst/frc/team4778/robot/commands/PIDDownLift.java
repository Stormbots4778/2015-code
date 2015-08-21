package org.usfirst.frc.team4778.robot.commands;

import org.usfirst.frc.team4778.robot.OI;
import org.usfirst.frc.team4778.robot.Robot;
import org.usfirst.frc.team4778.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class PIDDownLift extends Command {

    boolean finished;
    double wait;

    public PIDDownLift() {
	// Use requires() here to declare subsystem dependencies
	// eg. requires(chassis);
	requires(Robot.leftLift);
	requires(Robot.rightLift);
	wait = 0;
    }

    public PIDDownLift(double waittime) {
	requires(Robot.leftLift);
	requires(Robot.rightLift);
	wait = waittime;
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
	for (int i = 0; i < 20; i++) {
	    double currenttime = i * 0.05;
	    double setpoint = 37.88 / (Math.pow(25.84, currenttime) + 1.20) - 1.25;
	    Robot.rightLift.getPIDController().setSetpoint(setpoint);
	    Robot.leftLift.getPIDController().setSetpoint(setpoint);
	    if (i == 10) {
		RobotMap.leftGripIn.set(true);
		RobotMap.leftGripOut.set(false);
		RobotMap.rightGripIn.set(true);
		RobotMap.rightGripOut.set(false);
	    }
	    Robot.drive.tankDrive(OI.leftJoystick, OI.rightJoystick);
	    Timer.delay(0.05);
	}
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
