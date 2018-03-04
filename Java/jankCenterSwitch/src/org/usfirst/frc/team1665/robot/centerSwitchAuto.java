package org.usfirst.frc.team1665.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class centerSwitchAuto extends Command{
	// Variables for auto modes
	private Timer driveTimer = new Timer();
	int autoState;
	Boolean goesLeft;
	
	private Drivetrain drivetrain = new Drivetrain();
	
	public centerSwitchAuto(Subsystem requiredSubsystem, Boolean goesLeft) {
		this.goesLeft = goesLeft;
		requires(requiredSubsystem);
	}
	
	public void drive(double throttle, double turn) {
		drivetrain.drive(throttle, turn);
	}
		
	 protected void initialize() {
			// rest timer
			driveTimer.reset();
			// Start timer
			driveTimer.start();
			
			autoState = 0;
	    	
	    }

	    // Called repeatedly when this Command is scheduled to run
	    protected void execute() {
	    	autoState = 0;
			switch (autoState) {
				case 0: // Drive forward a tiny bit
					drive(1, 0);
					if (driveTimer.get() > 1000) {
						driveTimer.reset();
						autoState++;
					} 
					break;
				case 1: // Turn to angle
					// use game data
					double turnSpeed = 0.5;
					if(goesLeft) { turnSpeed = -1*turnSpeed; }
					drive(0, turnSpeed);
					if (driveTimer.get() > 500) { 
						driveTimer.reset();
						autoState++;
					} 
					break;
				case 2: // Drive to Switch
					drive(1, 0);
					if (driveTimer.get() > 2500) {
						drive(0, 0);
						driveTimer.reset();
						autoState++;
					} 
					break;
				case 3: // SCOREEEEEEE
					
					// spit out here
					break;
			}
	    }

	    // Make this return true when this Command no longer needs to run execute()
	    protected boolean isFinished() {
	    	return false;
	    }

	    // Called once after isFinished returns true
	    protected void end() {
	    	drive(0,0);
	    }

	    // Called when another command which requires one or more of the same
	    // subsystems is scheduled to run
	    protected void interrupted() {
	    	end();
	    }

}
