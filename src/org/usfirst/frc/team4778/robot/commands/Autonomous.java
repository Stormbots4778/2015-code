package org.usfirst.frc.team4778.robot.commands;

import org.usfirst.frc.team4778.robot.Robot;
import org.usfirst.frc.team4778.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous extends CommandGroup {

    public Autonomous() {
	// Add Commands here:
	// e.g. addSequential(new Command1());
	// addSequential(new Command2());
	// these will run in order.
	// To run multiple commands at the same time,
	// use addParallel()
	// e.g. addParallel(new Command1());
	// addSequential(new Command2());
	// Command1 and Command2 will run in parallel.
	// A command group will require all of the subsystems that each member
	// would require.
	// e.g. if Command1 requires chassis, and Command2 requires arm,
	// a CommandGroup containing them would require both the chassis and the
	// arm.
	double timeout = 15;
	// XXX we assume timeSinceInitialized() is in seconds
	requires(Robot.leftLift);
	requires(Robot.rightLift);
	requires(Robot.drivetrain);
	if (Robot.chooser.getSelected() == "yes") {
	    RobotMap.gyro.reset();
	    RobotMap.leftEncoderPrimary.reset();
	    RobotMap.rightEncoderPrimary.reset();
	    for (int i = 0; i < 2; i++) {
		addSequential(new AutomaticGripUp(), timeout - timeSinceInitialized() - 0.25);
		Timer.delay(0.15);
		// addSequential(new PIDUpLift()); //Bring lifts up
		addSequential(new GoToHeight(9.00, 0.6), timeout - timeSinceInitialized() - 0.3); // TODO Added this
		Timer.delay(0.2);
		addSequential(new TurnToAngle(-20, true), timeout - timeSinceInitialized() - 0.2); // Knock can out of the way
		Timer.delay(0.1);
		addSequential(new TurnToAngle(-1, true), timeout - timeSinceInitialized() - 0.3); // Return to straight path
		Timer.delay(0.2);
		addSequential(new PIDDriveForward(), timeout - timeSinceInitialized() - 0.3); // Drive forward to next tote
		Timer.delay(0.2);
		// addSequential(new PIDDownLift(0.2)); //Bring lifts down for next tote
		addSequential(new GoToHeight(8.00, 0.1, 0.1), timeout - timeSinceInitialized()); // TODO Added this
		addSequential(new ToggleGrips(), timeout - timeSinceInitialized()); // TODO Added this
		addSequential(new GoToHeight(0.16, 0.2, 0.75), timeout - timeSinceInitialized()); // TODO Added this
	    }
	    addSequential(new ToggleGrips(), timeout - timeSinceInitialized());
	    // addSequential(new LiftScore(true)); //Pick up the stack of 3 totes
	    addSequential(new GoToHeight(2.00, 0.6), timeout - timeSinceInitialized() - 0.3); // TODO Added this
	    Timer.delay(0.2);
	    addSequential(new BackUp(), timeout - timeSinceInitialized()); // Back away from last container
	    addSequential(new TurnToAngle(-30, false), timeout - timeSinceInitialized() - 0.2); // Turn in preparation for scoring
	    Timer.delay(0.1);
	    addSequential(new ForwardToScore(), timeout - timeSinceInitialized() - 0.2); // Go forward into auto zone
	    Timer.delay(0.1);
	    // addSequential(new LiftZero()); //Set down the stack
	    addSequential(new GoToHeight(0.16, 0.2, 0.4), timeout - timeSinceInitialized()); // TODO Added this
	    addSequential(new ToggleGrips(), timeout - timeSinceInitialized());
	    addSequential(new BackUp(), timeout - timeSinceInitialized());
	    // addSequential(new PIDDriveForward());
	}
    }

    @Override
    protected void end() {
	Robot.drive.arcadeDrive(0, 0);
    }

    @Override
    public boolean isFinished() {
	return isTimedOut();
    }

    @Override
    public synchronized void cancel() {
	end();
    }

    @Override
    protected void interrupted() {
	end();
    }
}
