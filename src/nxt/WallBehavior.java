package nxt;

import lejos.nxt.*;
import lejos.robotics.subsumption.*;

/**
 * Wall Behavior Class. If a touch sensor detects a wall this class will assert
 * control and change the direction of the robot.
 * 
 * @author Shea
 * @author Toby
 * @author Ario
 * @author Jeff
 * @author Chan
 * 
 */
public class WallBehavior implements Behavior {

	/**
	 * Flag that indicates whether or not our behavior should be suppressed
	 */
	private boolean suppressed = false;
	/**
	 * Touch sensor for this behavior
	 */
	private TouchSensor touch;

	/**
	 * Constructor that takes a sensor port that the touch sensor is plugged in
	 * to
	 * 
	 * @param port1
	 *            The port that the touch sensor(s) are plugged in to
	 */
	public WallBehavior(SensorPort port1) {
		touch = new TouchSensor(port1);
	}

	/**
	 * Returns true if this behavior should take control
	 */
	@Override
	public boolean takeControl() {
		return touch.isPressed();
	}

	/**
	 * The action that this behavior should perform when it takes control
	 * 
	 * In this case the action is to move backwards and then turn around
	 */
	@Override
	public void action() {
		// Change suppressed flag
		suppressed = false;

		// Reset motor A tachometer
		Motor.A.resetTachoCount();

		// Loop while the tachometer is greater than -250
		while (Motor.A.getTachoCount() > -250) {
			Motor.A.backward();
			Motor.C.backward();
		}

		// Reset motor A tachometer
		Motor.A.resetTachoCount();

		// Loop while the tachometer is less than 750
		while (Motor.A.getTachoCount() < 750) {
			Motor.A.forward();
			Motor.C.backward();
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
