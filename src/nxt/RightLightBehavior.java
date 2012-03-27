package nxt;

import lejos.nxt.*;
import lejos.robotics.subsumption.*;

public class RightLightBehavior implements Behavior{

	private boolean suppressed = false;
	private LightSensor leftLight, rightLight;
	private int dark = 35;
	
	public RightLightBehavior(SensorPort port, SensorPort port2) {
		leftLight = new LightSensor(port);
		rightLight = new LightSensor(port2);
	}
	public boolean takeControl() {
		//take control if leftLight < dark
		//System.out.println("RIGHT BEHAVIOR");
		if (rightLight.readValue() < dark && leftLight.readValue() > dark){
			return true;
		}
		return false;
	}

	
	public void action() {
		// turn towards the left a bit
		suppressed = false;
		Motor.C.resetTachoCount();
		while (Motor.C.getTachoCount() < 30) {
			Motor.C.setSpeed(500);
			Motor.A.setSpeed(500);
			Motor.C.forward();
			Motor.A.backward();
		}		
	}

	public void suppress() {
		suppressed = true;
		
	}
	
}
