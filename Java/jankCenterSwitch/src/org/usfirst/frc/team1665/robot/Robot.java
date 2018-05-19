/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1665.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	private Drivetrain drivetrain = new Drivetrain();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional comparisons to
	 * the switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	// Variables for auto modes
	private Timer driveTimer = new Timer();
	int autoState;
	
	NetworkTable table;
	String gameData;
	Boolean allianceColorRed;
	int allianceNumber;
	Boolean goesLeft = true;
	
	
	public void drive(double throttle, double turn) {
		drivetrain.drive(throttle, turn);
	}
	
	@Override
	public void autonomousInit() {
		// rest timer
		driveTimer.reset();
		// Start timer
		driveTimer.start();
		
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		allianceColorRed = DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Red;
		allianceNumber = DriverStation.getInstance().getLocation();
		
		
		goesLeft = (gameData.charAt(0) == 'L');
	}

	

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
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

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
