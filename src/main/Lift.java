/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * LIFT
 *
 * Software and Data Engineering
 * Télécom SudParis 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package main;

import javax.swing.JLabel;

import view.ViewLift;

/**
 * Class that represents a lift controller
 */
public class Lift
{
	// -----------------------------------------------------------------
    // Enumerations
    // -----------------------------------------------------------------
	enum Status{
	       GOING_UP, GOING_DOWN, STATIONARY, EMERGENCY, DOORS_BLOCKED;
	   }
	
	enum Direction{
	       UP, DOWN;
	   }
	
	enum DoorType{
	       INNER, OUTER;
	   }
	
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

	/**
     * Lift's view connection.
     */
    private ViewLift view;
	
    /**
     * Lift state.
     */
    private State state;
    
    /**
     * Lift's inner door
     */
    private Door innerDoor;

    /**
     * Floors' outer doors
     */
    private Door[] outerDoors;
    
    /**
     * Top Floor
     */
    private int topFloor;
    
    /**
     * Bottom Floor
     */
    private int bottomFloor;
    
    /**
     * Number of floors
     */
    private int nFloors;
    
    /**
     * Floors
     */
    private int[] floors;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Builds the lift. <br>
     * <b>post: </b> The lift is created with an empty state <br>
     */
    public Lift(ViewLift pView) {
    	
    	view = pView;
    	
    	state = new State();
    	
    	topFloor = 6;
        bottomFloor = 0;
        nFloors = topFloor - bottomFloor + 1;
        floors = new int[nFloors];
        
        innerDoor = new Door(false, DoorType.INNER, bottomFloor);
    	outerDoors = new Door[nFloors];
        
        int counter = 0;
        for (int f=bottomFloor; f<=topFloor; f++) {
        	floors[counter] = f;
        	
        	Door outerDoor = new Door(false, DoorType.OUTER, f);
        	outerDoors[counter] = outerDoor;
        	
        	counter++;
        }
    }
    
    /**
     * Returns the lift's top floor number. <br>
     */
    public int getTopFloor() {
    	return topFloor;
    }
    
    /**
     * Returns the lift's bottom floor number. <br>
     */
    public int getBottomFloor() {
    	return bottomFloor;
    }
    
    /**
     * Returns the lift's number of floors. <br>
     */
    public int getnFloors() {
    	return nFloors;
    }
    
    /**
     * Returns the lift's floors. <br>
     */
    public int[] getFloors() {
    	return floors;
    }
    
    public int getCurrentFloor() {
		return state.getPosition();
	}
    
    public String getStatus() {
		return state.getStatus().toString();
	}
    
    public void moveToNextStop() {
    	// TODO MC
    	
    	// check status:
    	// disable everything if there is an emergency and dont move
    	// dont move if doors are blocked

    	// process next stop from state
    	
    	// stop
    	
    	// update view
    }
    
    public void stopAtFloor(int floor) {
    	// TODO MC
    	// ...
    	
    	view.enableInsideButtonFloor(floor);
    }
    
    /**
     * Inserts a stop in the state. <br>
     * @param floor Floor to stop at
     * @param direction Direction to be going when it stops
     */
    public void insertStop(int floor, Direction direction) {
    	Stop newStop = new Stop(floor, direction);
    	state.insertStop(newStop);
    }

    /**
     * Handles a floor button press inside the lift. <br>
     * @param floor Floor to which the person inside the lift wants to go
     */
	public void insertInsideStop(int floor) {
		
		int currentFloor = state.getPosition();
		
		if (floor > currentFloor) {
			insertStop(floor, Direction.UP);
		} else if (floor < currentFloor) {
			insertStop(floor, Direction.DOWN);
		} else {
			view.enableInsideButtonFloor(floor);
		}
	}
	
	/**
     * Changes the status to emergency and vice-versa. <br>
     */
	public void switchEmergencyStatus() {
		state.switchEmergencyStatus();
	}
	
	/**
     * Changes the status to blocked doors and vice-versa. <br>
     */
	public void switchBlockedDoorsStatus() {
		state.switchBlockedDoorsStatus();
	}

}