package org.usfirst.frc.team1665.robot;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drivetrain{
	
	// Initializing left drivetrain controllers
		public static VictorSP driveControllerLeft1 = new VictorSP(1);
		public static VictorSP driveControllerLeft2 = new VictorSP(2);
		public static SpeedControllerGroup driveControllersLeft = new SpeedControllerGroup(driveControllerLeft1,
				driveControllerLeft2);

		// Initializing right drivetrain controllers
		public static VictorSP driveControllerRight1 = new VictorSP(2);
		public static VictorSP driveControllerRight2 = new VictorSP(3);
		public static SpeedControllerGroup driveControllersRight = new SpeedControllerGroup(driveControllerRight1,
				driveControllerRight2);

		// Initializing drivetrain
		public static DifferentialDrive drivetrain = new DifferentialDrive(driveControllersLeft, driveControllersRight);
	
	
	public Drivetrain() {
	}
	
	//Split stick arcade drive
	public void drive(double Throttle, double Turn) {
		drivetrain.arcadeDrive(Throttle,Turn);
	}
		
}
