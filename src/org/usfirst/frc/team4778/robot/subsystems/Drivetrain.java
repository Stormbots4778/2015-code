package org.usfirst.frc.team4778.robot.subsystems;

import org.usfirst.frc.team4778.robot.Robot;
import org.usfirst.frc.team4778.robot.RobotMap;
import org.usfirst.frc.team4778.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class Drivetrain extends PIDSubsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public Drivetrain() {
	super("Drive", 2.0, 0.0, 0.0);
	getPIDController().setOutputRange(-0.8, 0.8);
	getPIDController().setAbsoluteTolerance(1.00);
	getPIDController().disable();
    }

    @Override
    public void initDefaultCommand() {
	// Set the default command for a subsystem here.
	// setDefaultCommand(new MySpecialCommand());
	setDefaultCommand(new Drive());
    }

    @Override
    protected double returnPIDInput() {
	return RobotMap.gyro.getAngle();
    }

    @Override
    protected void usePIDOutput(double output) {
	if (getPIDController().isEnable())
	    Robot.drive.arcadeDrive(-0.9, output * -1);
    }
}
