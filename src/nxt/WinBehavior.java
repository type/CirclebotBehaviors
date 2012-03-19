package nxt;

import lejos.nxt.*;
import lejos.robotics.subsumption.*;



public class WinBehavior implements Behavior{

	
	private boolean suppressed = false;
	private LightSensor leftLight, rightLight;
	private int dark = 35;

	public WinBehavior(SensorPort port, SensorPort port2) {
		leftLight = new LightSensor(port);
		rightLight = new LightSensor(port2);
	}
	
	@Override
	public boolean takeControl() {
		
		return CircleBot.winflag;
	}

	@Override
	public void action() {
		suppressed = false;
	
		while(!Motor.A.isMoving() && !Motor.B.isMoving()) {
			;
		}
		if ((leftLight.readValue() < dark && rightLight.readValue() < dark)) {
			Motor.A.lock(100);
			Motor.B.lock(100);
		}
		else {
			CircleBot.winflag = false;
		}
		
	}

	@Override
	public void suppress() {
		suppressed = true;
		
	}

}
