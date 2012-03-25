package nxt;

import lejos.nxt.*;
import lejos.robotics.subsumption.*;

/**
 * Right Light Sensor Behavior Class. If the left sensor detects one part of the
 * circle it asserts control and turns the robot
 * 
 * @author Shea
 * @author Toby
 * @author Ario
 * @author Jeff
 * @author Chan
 * 
 */
public class RightLightBehavior implements Behavior {

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
	public RightLightBehavior(SensorPort port, SensorPort port2) {
		leftLight = new LightSensor(port);
		rightLight = new LightSensor(port2);
	}

	/**
	 * Returns true if this behavior should take control
	 */
	@Override
	public boolean takeControl() {
		// take control if leftLight < dark
		if (rightLight.readValue() < dark && leftLight.readValue() > dark) {
			return true;
		}
		return false;
	}

	/**
	 * The action that this behavior should perform when it takes control
	 * 
	 * In this case the action is to turn towards the right a bit
	 */
	@Override
	public void action() {
		// Change suppressed flag
		suppressed = false;

		// Reset motor A tachometer
		Motor.C.resetTachoCount();

		// Loop while the tachometer is less than 30
		while (Motor.C.getTachoCount() < 30) {
			Motor.C.setSpeed(500);
			Motor.A.setSpeed(500);
			Motor.C.forward();
			Motor.A.backward();
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
