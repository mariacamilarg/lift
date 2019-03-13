/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * LIFT
 *
 * Software and Data Engineering
 * Télécom SudParis 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package main;

import java.util.ArrayList;

/**
 * Class that represents a lift controller
 */
public class State
{
	// -----------------------------------------------------------------
    // Enumerations
    // -----------------------------------------------------------------
	enum Status{
	       GOING_UP, GOING_DOWN, STATIONARY, EMERGENCY, DOORS_BLOCKED;
	   }
	
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Lift's current position (floor).
     */
    private int position;
    
    /**
     * Lift's inner doors status (false=closed, true=opened).
     */
    private boolean inner_doors_opened;
    
    /**
     * Lift's current status
     */
    private Status status;
    
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
        inner_doors_opened = false;
        status = Status.STATIONARY;
        stops = new ArrayList<Stop>();
    }

}