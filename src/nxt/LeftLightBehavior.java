package nxt;

import lejos.nxt.*;
import lejos.robotics.subsumption.*;

/**
 * Left Light Sensor Behavior Class. If the left sensor detects one part of the
 * circle it asserts control and turns the robot
 * 
 * @author Shea
 * @author Toby
 * @author Ario
 * @author Jeff
 * @author Chan
 * 
 */
public class LeftLightBehavior extends AbstractLightBehavior {

	
	/**
	 * Constructor that takes two sensor ports, one for each light sensor
	 * 
	 * @param port
	 *            The port that the left light sensor is connected to
	 * @param port2
	 *            The port that the right light sensor is connected to
	 */
	public LeftLightBehavior(SensorPort port, SensorPort port2) {
		super(port,port2);
		
		//We have to set the motors so that the turn will execute in the correct direction
		this.motor1 = Motor.A;
		this.motor2 = Motor.C;
	}

	/**
	 * Returns true if this behavior should take control
	 */
	@Override
	public boolean takeControl() {
		// Take control if leftLight < dark and rightLight > dark
		return leftLight.readValue() < dark && rightLight.readValue() > dark;
	}

}
