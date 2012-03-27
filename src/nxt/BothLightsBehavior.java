package nxt;

import lejos.nxt.*;
import lejos.robotics.subsumption.*;

public class BothLightsBehavior implements Behavior{

	private boolean suppressed = false;
	private LightSensor leftLight, rightLight;
	private int dark = 35;
	
	public BothLightsBehavior(SensorPort port, SensorPort port2) {
		leftLight = new LightSensor(port);
		rightLight = new LightSensor(port2);
	}
	
	public boolean takeControl() {
		//System.out.println("BOTH LIGHTS BEHAVIOR");
		if ((leftLight.readValue() < dark && rightLight.readValue() < dark) || CircleBot.winflag == true) {
			return true;
		}
		return false;
	}

	public void action() {
		suppressed = false;
		//move forward a bit
		Motor.A.resetTachoCount();
		while (Motor.A.getTachoCount() < 10) {
			Motor.A.forward();
			Motor.C.forward();
		}
		CircleBot.winflag = true;
	}

	public void suppress() {
		suppressed = true;
	}

}
