package nxt;

import lejos.nxt.*;
import lejos.robotics.subsumption.*;

/**
 * Win behavior class. If the win flag is set in the main class this class will
 * assert control.
 * 
 * @author Shea
 * @author Toby
 * @author Ario
 * @author Jeff
 * @author Chan
 * 
 */
public class WinBehavior implements Behavior {

	/**
	 * Flag that indicates whether or not our behavior should be suppressed
	 */
	private boolean suppressed = false;
	/**
	 * The light sensors we use in our behavior
	 */
	private LightSensor leftLight, rightLight;
	/**
	 * Darkness threshold
	 */
	private static final int dark = 35;

	/**
	 * Constructor that takes two sensor ports, one for each light sensor
	 * 
	 * @param port
	 *            The port that the left light sensor is connected to
	 * @param port2
	 *            The port that the right light sensor is connected to
	 */
	public WinBehavior(SensorPort port, SensorPort port2) {
		leftLight = new LightSensor(port);
		rightLight = new LightSensor(port2);
	}

	/**
	 * Returns true if this behavior should take control
	 */
	@Override
	public boolean takeControl() {

		return CircleBot.winflag;
	}

	/**
	 * The action that this behavior should perform when it takes control
	 * 
	 * In this case the current action is to lock
	 */
	@Override
	public void action() {
		// Change suppressed flag
		suppressed = false;

		// Loop while both motors are not moving
		while (!Motor.A.isMoving() && !Motor.B.isMoving()) {
			;
		}

		// If we are still on the circle, lock the motors
		if ((leftLight.readValue() < dark && rightLight.readValue() < dark)) {
			Motor.A.lock(100);
			Motor.B.lock(100);
		} else {
			// We got pushed off
			CircleBot.winflag = false;
		}
	}

	/**
	 * Temporarily disable the behavior because another behavior has taken
	 * control
	 */
	@Override
	public void suppress() {
		suppressed = true;

	}

}
