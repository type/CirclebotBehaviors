package nxt;


import lejos.nxt.*;
import lejos.robotics.subsumption.*;

public class WallBehavior implements Behavior{

	int winflag = 0;

	private TouchSensor touch; 
	public WallBehavior(SensorPort port1) {
		
		touch = new TouchSensor(port1);
		
	}

	private boolean suppressed = false;
	
	public boolean takeControl() {
		if (touch.isPressed())
			return true;
		return false;
		
	}

	
	public void action() {
		// Back up
		// Turn yourself around
		suppressed = false;
		Motor.A.resetTachoCount();
		while (Motor.A.getTachoCount() > -250) {
			Motor.A.backward();
			Motor.C.backward();
		}
		
		Motor.A.resetTachoCount();
		while (Motor.A.getTachoCount() < 1000) {
			Motor.A.forward();
			Motor.C.backward();
		}

//		Motor.A.forward();
//		Motor.C.forward();
	
	}

	
	public void suppress() {
		suppressed = true;
	}


}
