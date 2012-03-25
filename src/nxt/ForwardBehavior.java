package nxt;

import lejos.nxt.*;
import lejos.robotics.subsumption.*;

/**
 * Move Forward Behavior class. Moves the robot forward while it has control.
 * 
 * @author Shea
 * @author Toby
 * @author Ario
 * @author Jeff
 * @author Chan
 */
public class ForwardBehavior implements Behavior {

	/**
	 * Flag that indicates whether or not our behavior should be suppressed
	 */
	private boolean suppressed = false;

	/**
	 * Default Constructor
	 */
	public ForwardBehavior() {
		// Stub
	}

	/**
	 * Returns true if this behavior should take control
	 */
	@Override
	public boolean takeControl() {
		// maybe should be !winFlag??
		//return true;
		return !CircleBot.winflag;
	}

	/**
	 * The action that this behavior should perform when it takes control.
	 * 
	 * In this case the action is to move forward
	 */
	@Override
	public void action() {
		// Change suppressed flag
		suppressed = false;

		// Set motor speed
		Motor.C.setSpeed(800);
		Motor.A.setSpeed(800);

		// Move forward until stop() is called
		Motor.A.forward();
		Motor.C.forward();

		// Keep looping while our behavior is active
		while (!suppressed) {
			Thread.yield();
		}

		// Stop moving
		Motor.A.stop();
		Motor.C.stop();
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