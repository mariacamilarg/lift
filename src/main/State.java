/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * LIFT
 *
 * Software and Data Engineering
 * Télécom SudParis 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package main;

import java.util.ArrayList;

import main.Lift.Direction;
import main.Lift.Status;

/**
 * Class that represents a lift controller
 */
public class State
{	
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Lift's current position (floor).
     */
    private int position;
    
    /**
     * Lift's current status
     */
    private Status status;
    
    /**
     * Lift's status before a change
     */
    private Status previousStatus;
    
    /**
     * Lift's stops.
     */
    private ArrayList<Stop> stops;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Builds the lift. <br>
     * <b>post: </b> The lift is created with an empty state <br>
     */
    public State( )
    {
        position = 0;
        previousStatus = Status.STATIONARY;
        status = Status.STATIONARY;
        stops = new ArrayList<Stop>();
    }
    
    public int getPosition() {
    	return position;
    }
    
    public void setPosition(int pPosition) {
    	position = pPosition;
    }

    public Status getStatus() {
    	return status;
    }
    
    public void setStatus(Status pStatus) {
    	previousStatus = status;
    	status = pStatus;
    }
    
    public ArrayList<Stop> getStops() {
    	return stops;
    }
    
    public void insertStop (Stop newStop) {
    	int index, insertIn, actualFloor;
    	Stop nextStop;
    	index = 0;
    	insertIn = stops.size();
    	actualFloor = position;
    	while (index < insertIn) {
    		nextStop = stops.get(index);
			if ((newStop.getDirection() == Direction.UP && actualFloor <= newStop.getFloor() && newStop.getFloor() <= nextStop.getFloor())
					|| (newStop.getDirection() == Direction.DOWN && actualFloor >= newStop.getFloor() && newStop.getFloor() >= nextStop.getFloor())){
				insertIn = index;
			}
			index++;
			actualFloor = nextStop.getFloor();    			
    	} 	
    	stops.add(insertIn, newStop);
    }
    
    public Stop getNextStop() {
		return stops.get(0);
	}
    
    public void removeStop() {
		stops.remove(0);
	}
    
    public boolean isStopsEmpty() {
    	return stops.isEmpty();
    }

	public void switchEmergencyStatus() {
		if (!status.equals(Status.EMERGENCY)) {
			setStatus(Status.EMERGENCY);
		} else {
			setStatus(previousStatus);
		}
	}

	public void switchBlockedDoorsStatus() {
		if (!status.equals(Status.DOORS_BLOCKED)) {
			setStatus(Status.DOORS_BLOCKED);
		} else {
			setStatus(previousStatus);
		}
	}
    
}