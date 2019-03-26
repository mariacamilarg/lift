/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * LIFT
 *
 * Software and Data Engineering
 * Télécom SudParis 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package main;

import java.util.Arrays;

import view.ViewLift;

/**
 * Class that represents a lift controller
 */
public class Lift
{
	// -----------------------------------------------------------------
    // Enumerations
    // -----------------------------------------------------------------
	public enum Status{
	       GOING_UP, GOING_DOWN, STATIONARY, EMERGENCY, DOORS_BLOCKED;
	   }
	
	public enum Direction{
	       UP, DOWN;
	   }
	
	public enum DoorType{
	       INNER, OUTER;
	   }
	
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
	
	public static final int MOVE_TIME_BETWEEN_FLOORS = 1000; // in milliseconds
	public static final int WAIT_TIME_DOORS_OPENED = 4000; // in milliseconds
	public static final int LOOP_WAIT_TIME = 1000; // in milliseconds
	
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
    
    /**
     * Returns the lift's current floor. <br>
     */
    public int getCurrentFloor() {
		return state.getPosition();
	}
    
    /**
     * Returns the lift's status. <br>
     */
    public String getStatus() {
		return state.getStatus().toString();
	}
    
    /**
     * Moves the lift. <br>
     */
    public void move() {
    	
    	try {
	    	while(true) {
	    		
	    		// Check status: don't move if emergency or if doors are blocked
	    		if ( (state.getStatus() == Status.EMERGENCY) || (state.getStatus() == Status.DOORS_BLOCKED) ) {
	    			// wait until button is pressed again 
	    			continue; 
	    		} else {
	    			
	    			if ( !state.isStopsEmpty() ) {
		    			
		    			// Process next stop from state
		    			Stop nextStop = state.getNextStop();
		    	    	int nextFloor = nextStop.getFloor();
		    	    	
		    	    	int currentFloor = state.getPosition();
		    	    	
		    	    	// Move between floors
		    	    	while (currentFloor != nextFloor) {
		    	    		Thread.sleep(MOVE_TIME_BETWEEN_FLOORS);
		    	    		
		    	    		if (nextFloor > currentFloor) {
		    	    			state.setPosition(currentFloor + 1);
		    	    			state.setStatus(Status.GOING_UP);
		    	    		} else {
		    	    			state.setPosition(currentFloor - 1);
		    	    			state.setStatus(Status.GOING_DOWN);
		    	    		}
		    	    		
		    	    		currentFloor = state.getPosition();
		    	    		
		    	    		view.refresh();
		    	    	}
		    	    	
		    	    	// Stop
		    	    	stopAtFloor(nextFloor);
		    		} else {
		    			state.setStatus(Status.STATIONARY);
		    			view.refresh();
		    		}
	    			
	    		}
	    		
	    		// For efficiency
	    		Thread.sleep(LOOP_WAIT_TIME);
	    	}
    	} catch (InterruptedException e) {
			// Exception from Thread.sleep()
			e.printStackTrace();
		}
    }
    
    /**
     * Stops at a particular floor. <br>
     * @param floor Floor to stop at
     * @param direction Direction to be going when it stops
     * @throws InterruptedException 
     */
    public void stopAtFloor(int floor) throws InterruptedException {
    	// Opens doors
    	//int floorIndex = Arrays.asList(floors).indexOf(floor);
		//Door outerDoor = outerDoors[floorIndex];
		
		//outerDoor.open();
		//outerDoors[floorIndex] = outerDoor;
    	outerDoors[floor].open();
		innerDoor.open();
		
		// Updates the view
		view.enableInsideButtonFloor(floor);
		view.enableOutsideButtonsFloor(floor);
		view.openFloorDoors(floor);
		view.refresh();
		
		// Remove stop from state
    	state.removeStop();
		
		// Waits for people to get in/out
    	Thread.sleep(WAIT_TIME_DOORS_OPENED);
    	
    	// Closes doors
    	innerDoor.close();
    	outerDoors[floor].close();
    	//outerDoor.close();
    	//outerDoors[floorIndex] = outerDoor;
    	
    	// Updates the view
    	view.closeFloorDoors(floor);
    	view.refresh();    	
    }
    
    /**
     * Inserts a stop in the state. <br>
     * @param floor Floor to stop at
     * @param direction Direction to be going when it stops
     */
    public void insertStop(int floor, Direction direction) {
    	Stop newStop = new Stop(floor, direction);
    	state.insertStop(newStop);
    	System.out.println( "STOPS: " + state.getStops().toString() );
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