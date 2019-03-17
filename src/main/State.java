/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * LIFT
 *
 * Software and Data Engineering
 * Télécom SudParis 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package main;

import java.util.ArrayList;

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
    
    public void insertStop (Stop pStop) {
    	// TODO MC: KEY method, it has to insert it according to the logic
    }
    
    public void removeStop() {
		stops.remove(0);
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