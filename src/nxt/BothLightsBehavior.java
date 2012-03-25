package nxt;

import lejos.nxt.*;
import lejos.robotics.subsumption.*;

/**
 * Both Light Sensors Behavior. If the light values of both sensors are correct
 * or the win flag is set, this behavior asserts control.
 * 
 * @author Shea
 * @author Toby
 * @author Ario
 * @author Jeff
 * @author Chan
 * 
 */
public class BothLightsBehavior extends AbstractLightBehavior {

	/**
	 * Constructor that takes two sensor ports, one for each light sensor
	 * 
	 * @param port
	 *            The port that the left light sensor is connected to
	 * @param port2
	 *            The port that the right light sensor is connected to
	 */
	public BothLightsBehavior(SensorPort port, SensorPort port2) {
		super(port,port2);
		
		//Motors don't matter for this behavior
	}

	/**
	 * Returns true if this behavior should take control
	 */
	@Override
	public boolean takeControl() {
		//Check to see if we won already
		if(CircleBot.winflag) return true;
		
		//Check to see if both light sensors are  dark
		return leftLight.readValue() < dark && rightLight.readValue() < dark;
	}

	/**
	 * The action that this behavior should perform when it takes control
	 * 
	 * In this case the action is to set the win flag
	 */
	@Override
	public void action() {
		// Change suppressed flag
		suppressed = false;

		//TODO Should we move forward, or is this handled in another behavior?
		// Motor.B.forward();
		CircleBot.winflag = true;
	}

}
