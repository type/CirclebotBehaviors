package nxt;

import lejos.nxt.LightSensor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.robotics.subsumption.Behavior;

/**
 * Abstract base class for Light Sensor Related Behaviors
 * 
 * @author Shea
 * @author Toby
 * @author Ario
 * @author Jeff
 * @author Chan
 *
 */
public abstract class AbstractLightBehavior implements Behavior {

	/**
	 * Flag that indicates whether or not our behavior should be suppressed
	 */
	protected boolean suppressed;
	/**
	 * The light sensors we use in our behavior
	 */
	protected LightSensor leftLight, rightLight;
	/**
	 * The motors for this class
	 */
	protected NXTRegulatedMotor motor1, motor2;
	/**
	 * Darkness threshold
	 */
	protected static final int dark = 35;

	/**
	 * Constructor that takes two sensor ports, one for each light sensor
	 * 
	 * @param leftLightPort
	 *            The port that the left light sensor is connected to
	 * @param rightLightPort
	 *            The port that the right light sensor is connected to
	 */
	public AbstractLightBehavior(SensorPort leftLightPort, SensorPort rightLightPort) {
		leftLight = new LightSensor(leftLightPort);
		rightLight = new LightSensor(rightLightPort);
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

		// Reset motor C tachometer
		motor2.resetTachoCount();

		// Loop while the tachometer is less than 30
		while (motor2.getTachoCount() < 30) {
			//Set the motor speed
			motor2.setSpeed(500);
			motor1.setSpeed(500);
			
			//Turn
			motor2.forward();
			motor1.backward();
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
