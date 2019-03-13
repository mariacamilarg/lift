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
public class Stop
{
	// -----------------------------------------------------------------
    // Enumerations
    // -----------------------------------------------------------------
	enum Direction{
	       UP, DOWN;
	   }
	
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Stop's floor.
     */
    private int floor;
    
    /**
     * Stops's direction 
     */
    private Direction direction;
    

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Builds the lift. <br>
     * <b>post: </b> The lift is created with an empty state <br>
     */
    public Stop(int pFloor, Direction pDirection)
    {
        floor = pFloor;
        direction = pDirection;
    }

}