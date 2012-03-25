package nxt;

import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

/**
 * Driver class for the CircleBot Behavior Bot
 * 
 * @author Shea
 * @author Toby
 * @author Ario
 * @author Jeff
 * @author Chan
 * 
 */
public class CircleBot {
	public static boolean winflag = false;
	
	/**
	 * Main Entry Point.  Creates the behaviors and starts the 
	 * Arbitrator.
	 * 
	 * @param args
	 */
	public static void main(String [] args) {
		//Wall sensing behavior
		Behavior b1 = new WallBehavior(SensorPort.S3);
		//Move forward behavior
		Behavior b2 = new ForwardBehavior();
		//Left Light sensor sensing behavior
		Behavior b3 = new LeftLightBehavior(SensorPort.S2, SensorPort.S1);
		//Right Light sensor sensing behavior
		Behavior b4 = new RightLightBehavior(SensorPort.S2, SensorPort.S1);
		//Both Light sensors sensing behavior
		Behavior b5 = new BothLightsBehavior(SensorPort.S2, SensorPort.S1);
		//Win Behavior
		Behavior b6 = new WinBehavior(SensorPort.S2, SensorPort.S1);
		
		//Put the behaviors into the array based on priority
		Behavior [] behaviorArray = {b2, b1, b3, b4, b5, b6};
		
		//Create the arbitrator
		Arbitrator arby = new Arbitrator(behaviorArray);
		
		//Set the motor speeds
		Motor.A.setSpeed(800);
		Motor.C.setSpeed(800);
		
		//Start
		arby.start();
	}
}
