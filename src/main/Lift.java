/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * LIFT
 *
 * Software and Data Engineering
 * Télécom SudParis 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package main;

/**
 * Class that represents a lift controller
 */
public class Lift
{
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Lift state.
     */
    private State state;
    
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

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Builds the lift. <br>
     * <b>post: </b> The lift is created with an empty state <br>
     */
    public Lift() {
    	state = new State();
    	
    	topFloor = 6;
        bottomFloor = 0;
        nFloors = topFloor - bottomFloor + 1;
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

}