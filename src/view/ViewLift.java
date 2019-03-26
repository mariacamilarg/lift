/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * LIFT
 *
 * Software and Data Engineering
 * Télécom SudParis 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.UIManager;

import main.Lift;
import main.Lift.Direction;

/**
 * Principal view of the app
 */
@SuppressWarnings("serial")
public class ViewLift extends JFrame
{
    // -----------------------------------------------------------------
    // Atributes
    // -----------------------------------------------------------------

    /**
     * Principal main class
     */
    private static Lift lift;

    // -----------------------------------------------------------------
    // Atributes of the view
    // -----------------------------------------------------------------
    
    /**
     * Panel to decorate
     */
    private PanelImage panelImage;
    
    /**
     * Panel to show floors info and actions
     */
    private PanelFloors panelFloors;
    
    /**
     * Panel to perform actions inside the lift
     */
    private PanelActions panelActions;
    
    /**
     * Panel to show lift position and directions
     */
    private PanelLift panelLift;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Builds a new interface initialized with the lift as the main dictates. <br>
     * <b>post: </b> The main interface and its panels are initialized.
     */
    public ViewLift( )
    {
        setTitle( "Lift" );
        setSize( 800, 600 );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        // Build main connection
        lift = new Lift(this);

        // Build panels
        panelLift = new PanelLift( this );
        panelFloors = new PanelFloors( this );
        panelActions = new PanelActions( this );
        panelImage = new PanelImage( );

        // Organize structure layout
        getContentPane( ).setLayout( new BorderLayout( ) );
        getContentPane( ).add( panelImage, BorderLayout.NORTH );
        getContentPane( ).add( panelFloors, BorderLayout.CENTER );
        getContentPane( ).add( panelActions, BorderLayout.EAST );
        getContentPane( ).add( panelLift, BorderLayout.SOUTH );

        setLocationRelativeTo( null );
        setResizable( false );

        refresh( );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Executes the app
     * @param pArgs Execution params. Not necessary.
     */
    public static void main( String[] pArgs )
    {
        try
        {
            // Unifies interface for Mac and Windows.
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );

            ViewLift view = new ViewLift( );
            view.setVisible( true );
            
            lift.move();
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }

    // -----------------------------------------------------------------
    // General Methods
    // -----------------------------------------------------------------
    
    /**
     * Updates current lift's data. <br>
     * <b>post: </b> The fields with lift's information are updated.
     */
    public void refresh( )
    {
    	// Panel Lift
    	panelLift.refresh();

        validate( );
    }
    
    /**
     * Returns the lift's top floor number. <br>
     */
	public int getTopFloor() {
		return lift.getTopFloor();
	}
	
	/**
     * Returns the lift's bottom floor number. <br>
     */
	public int getBottomFloor() {
		return lift.getBottomFloor();
	}
	
	/**
     * Returns the lift's number of floors. <br>
     */
	public int getnFloors() {
		return lift.getnFloors();
	}
	
	// -----------------------------------------------------------------
    // Outside Actions Methods
    // -----------------------------------------------------------------

	/**
     * Handles a request to go up from a floor outside the lift. <br>
     * @param floor Floor from which the up button was pressed.
     */
	public void outsideActionUp(int floor) {
		lift.insertStop(floor, Direction.UP);
	}
	
	/**
     * Handles a request to go down from a floor outside the lift. <br>
     * @param floor Floor from which the down button was pressed.
     */
	public void outsideActionDown(int floor) {
		lift.insertStop(floor, Direction.DOWN);
		
	}
	
	/**
     * Opens the doors for the respective floor. <br>
     * @param floor for which the doors will open.
     */
	public void openFloorDoors(int floor) {
		panelFloors.openFloorDoors(floor);
	}
	
	/**
     * Closes the doors for the respective floor. <br>
     * @param floor for which the doors will close.
     */
	public void closeFloorDoors(int floor) {
		panelFloors.closeFloorDoors(floor);
	}
	
	/**
     * Enables the floor button inside the lift. <br>
     */
	public void enableOutsideButtonsFloor(int floor) {
		panelFloors.enableOutsideButtonsFloor(floor);
	}
	
	// -----------------------------------------------------------------
    // Inside Actions Methods
    // -----------------------------------------------------------------
	
	/**
     * Handles a floor button press inside the lift. <br>
     * @param floor Floor to which the person inside the lift wants to go
     */
	public void insideActionFloor(int floor) {
		lift.insertInsideStop(floor);
	}
	
	/**
     * Enables the floor button inside the lift. <br>
     */
	public void enableInsideButtonFloor(int floor) {
		panelActions.enableInsideButtonFloor(floor);
	}
	
	/**
     * Disables the floor button inside the lift. <br>
     */
	public void disableInsideButtonFloor(int floor) {
		panelActions.disableInsideButtonFloor(floor);
	}
	
	/**
     * Handles a emergency button press inside the lift. <br>
     */
	public void insideActionEmergency() {
		lift.switchEmergencyStatus();
		refresh();
	}

	/**
     * Handles a block button press inside the lift. <br>
     */
	public void insideActionBlockDoors() {
		lift.switchBlockedDoorsStatus();
		refresh();
	}

	// -----------------------------------------------------------------
    // Panel Lift Methods
    // -----------------------------------------------------------------
	
	/**
     * Returns current floor of the lift. <br>
     */
	public int getCurrentFloor() {
		return lift.getCurrentFloor();
	}

	/**
     * Returns current status of the lift. <br>
     */
	public String getStatus() {
		return lift.getStatus();
	}
	
}