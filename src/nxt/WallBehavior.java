package nxt;


import lejos.nxt.*;
import lejos.robotics.subsumption.*;

public class WallBehavior implements Behavior{

	boolean lastMoveTiny = false;
	boolean turn = false;

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
		suppressed = false;
		CircleBot.moved = Motor.A.getTachoCount();
		if (CircleBot.moved < 3000 && CircleBot.moved > 10){
			lastMoveTiny = true;
		}
		else {
			lastMoveTiny = false;
		}

		// Back up
		Motor.A.resetTachoCount();
		while (Motor.A.getTachoCount() > -250) {
			Motor.A.backward();
			Motor.C.backward();
		}

		// Turn yourself around
		if (turn){
			Motor.A.resetTachoCount();
			while (Motor.A.getTachoCount() < 975) {
				Motor.A.forward();
				Motor.C.backward();
			}
		}
		else {
			Motor.C.resetTachoCount();
			while (Motor.C.getTachoCount() < 900) {
				Motor.C.forward();
				Motor.A.backward();
			}
		}
		if (!lastMoveTiny){
			turn = !turn;
		}

	}


	public void suppress() {
		suppressed = true;
	}


}
