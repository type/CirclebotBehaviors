package nxt;

import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class CircleBot {
	public static boolean winflag = false;
	public static void main(String [] args) {
		Behavior b1 = new WallBehavior(SensorPort.S3);
		Behavior b2 = new ForwardBehavior();
		Behavior b3 = new LeftLightBehavior(SensorPort.S2, SensorPort.S1);
		Behavior b4 = new RightLightBehavior(SensorPort.S2, SensorPort.S1);
		Behavior b5 = new BothLightsBehavior(SensorPort.S2, SensorPort.S1);
		Behavior b6 = new WinBehavior(SensorPort.S2, SensorPort.S1);
		Behavior [] behaviorArray = {b2, b1, b3, b4, b5, b6};
		Arbitrator arby = new Arbitrator(behaviorArray);
		Motor.A.setSpeed(800);
		Motor.C.setSpeed(800);
		
		arby.start();
	}
}
