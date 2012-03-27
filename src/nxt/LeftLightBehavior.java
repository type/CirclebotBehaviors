package nxt;

import lejos.nxt.*;
import lejos.robotics.subsumption.*;

public class LeftLightBehavior implements Behavior{

	private boolean suppressed = false;
	private LightSensor leftLight, rightLight;
	private int dark = 35;
	
	public LeftLightBehavior(SensorPort port, SensorPort port2) {
		leftLight = new LightSensor(port);
		rightLight = new LightSensor(port2);
	}
	public boolean takeControl() {
		//System.out.println("LEFT BEHAVIOR");

		if (leftLight.readValue() < dark && rightLight.readValue() > dark){
			return true;
		}
		return false;
	}

	
	public void action() {
		// turn towards the left a bit
		suppressed = false;
		Motor.A.resetTachoCount();
		while (Motor.A.getTachoCount() < 30) {
			Motor.C.setSpeed(500);
			Motor.A.setSpeed(500);
			Motor.A.forward();
			Motor.C.backward();
		}
//		while ( !suppressed ){
//			Thread.yield();
//		}
//		Motor.A.stop();
//		Motor.C.stop();
		
	}

	public void suppress() {
		suppressed = true;
		
	}
	
}
