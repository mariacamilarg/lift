/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * LIFT
 *
 * Software and Data Engineering
 * Télécom SudParis 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package main;

import main.Lift.DoorType;

/**
 * Class that represents a lift controller
 */
public class Door
{
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

	/**
     * Doors status (false=closed, true=opened).
     */
    private boolean isOpen;
    
    /**
     * Door type
     */
    private DoorType type;
    
    /**
     * Door's floor
     */
    private int floor;
    

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Builds the lift. <br>
     * <b>post: </b> The lift is created with an empty state <br>
     */
    public Door(boolean pIsOpen, DoorType pType, int pFloor)
    {
        isOpen = pIsOpen;
        type = pType;
        floor = pFloor;
    }
    
    public boolean isOpen() {
    	return isOpen;
    }
    
    public DoorType getType() {
    	return type;
    }
    
    public int getFloor() {
    	return floor;
    }

    public void open() {
    	isOpen = true;
    }
    
    public void close() {
    	isOpen = false;
    }
}