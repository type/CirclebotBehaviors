package nxt;


import lejos.nxt.*;
import lejos.robotics.subsumption.*;

public class ForwardBehavior implements Behavior{

	private boolean suppressed = false;
	
	
	public boolean takeControl() {
		return true;
	}

	
	public void action() {
		suppressed = false;
		
		Motor.C.setSpeed(800);
		Motor.A.setSpeed(800);
		Motor.A.forward();
		Motor.C.forward();
		
		
		while ( !suppressed ){
			Thread.yield();
		}
		Motor.A.stop();
		Motor.C.stop();
	}

	
	public void suppress() {
		suppressed = true;
	}
	
}