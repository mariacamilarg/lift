/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * LIFT
 *
 * Software and Data Engineering
 * Télécom SudParis 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 * Panel that displays each floor status and actions
 */
@SuppressWarnings("serial")
public class PanelFloors extends JPanel implements ActionListener
{

	// -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
	
	public static final String OPENED = "OPENED";
	public static final String CLOSED = "Closed";
	
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Main app connection
     */
    private ViewLift principal;
    
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
    // View attributes
    // -----------------------------------------------------------------

    /**
     * Floors
     */
    private int[] floors;
    
    /**
     * Labels for inner doors
     */
    private JLabel[] lblsInnerDoors;

    /**
     * Labels for outer doors
     */
    private JLabel[] lblsOuterDoors;

    /**
     * Button commands to order elevator up
     */
    private String[] cmdsLiftUp;
    
    /**
     * Buttons to order elevator up
     */
    private JButton[] btnsLiftUp;
    
    /**
     * Button commands to order elevator down
     */
    private String[] cmdsLiftDown;
    
    /**
     * Buttons to order elevator down
     */
    private JButton[] btnsLiftDown;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Build the panel with the floor information. <br>
     * <b>post: </b> Panel is initialized.
     * @param pPrincipal Main view of the app. pPrincipal != null.
     */
    public PanelFloors( ViewLift pPrincipal )
    {
        principal = pPrincipal;

        setLayout( new BorderLayout( ) );
        setBorder( new CompoundBorder( new EmptyBorder( 0, 0, 5, 0 ), new TitledBorder( "Floors" ) ) );

        topFloor = principal.getTopFloor();
        bottomFloor = principal.getBottomFloor();
        nFloors = principal.getnFloors();
        
        floors = new int[nFloors];
        lblsInnerDoors = new JLabel[nFloors];
        lblsOuterDoors = new JLabel[nFloors];
        
        cmdsLiftUp = new String[nFloors];
        btnsLiftUp = new JButton[nFloors];
        
        cmdsLiftDown = new String[nFloors];
        btnsLiftDown = new JButton[nFloors];
        
        JPanel panelFloors = new JPanel( new GridLayout( nFloors+1, 5 ) );
        panelFloors.add( new JLabel("FLOOR") );
        panelFloors.add( new JLabel("INNER DOORS") );
        panelFloors.add( new JLabel("OUTER DOORS") );
        panelFloors.add( new JLabel("REQ. UP") );
        panelFloors.add( new JLabel("REQ. DOWN") );
        
        int counter = 0;
        for (int i=topFloor; i>=bottomFloor; i--) {
        	// Floor
        	JLabel lblFloor = new JLabel( Integer.toString(i) );
        	panelFloors.add( lblFloor );
        	floors[counter] = i;
        	
        	// Inner Doors
        	JLabel lblInnerDoor = new JLabel( CLOSED );
        	panelFloors.add(lblInnerDoor);
        	lblsInnerDoors[counter] = lblInnerDoor;
        	
        	// Outer Doors
        	JLabel lblOuterDoor = new JLabel( CLOSED );
        	panelFloors.add(lblOuterDoor);
        	lblsOuterDoors[counter] = lblOuterDoor;
        	
        	// Outside Actions
        	String commandUp = "OUTSIDE_UP_" + i;
        	JButton btnLiftUp = new JButton( );
        	btnLiftUp.setText( "UP ^" );
        	btnLiftUp.setActionCommand( commandUp );
        	btnLiftUp.addActionListener( this );
        	panelFloors.add(btnLiftUp);
        	cmdsLiftUp[counter] = commandUp;
        	btnsLiftUp[counter] = btnLiftUp;
        	
        	String commandDown = "OUTSIDE_DOWN_" + i;
        	JButton btnLiftDown = new JButton( );
        	btnLiftDown.setText( "DOWN v" );
        	btnLiftDown.setActionCommand( commandDown );
        	btnLiftDown.addActionListener( this );
        	panelFloors.add(btnLiftDown);
        	cmdsLiftDown[counter] = commandDown;
        	btnsLiftDown[counter] = btnLiftDown;
        	
        	// Counter
        	counter++;
        }
        
        add( panelFloors, BorderLayout.CENTER );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Updates the doors' labels with OPENED for the respective floor. <br>
     * @param floor for which the doors will open
     * <b>post: </b> The inner/outer doors labels are updated.
    */
    public void openFloorDoors(int floor) {
    	//int floorIndex = Arrays.asList(floors).indexOf(floor);
    	
    	JLabel lblOuterDoor = lblsOuterDoors[nFloors-floor-1];
    	JLabel lblInnerDoor = lblsInnerDoors[nFloors-floor-1];
    	
		lblOuterDoor.setText(OPENED);
		lblInnerDoor.setText(OPENED);
	}
    
    /**
     * Updates the doors' labels with CLOSED for the respective floor. <br>
     * @param floor for which the doors will close
     * <b>post: </b> The inner/outer doors labels are updated.
    */
    public void closeFloorDoors(int floor) {
    	//int floorIndex = Arrays.asList(floors).indexOf(floor);
    	
    	JLabel lblInnerDoor = lblsInnerDoors[nFloors-floor-1];
    	JLabel lblOuterDoor = lblsOuterDoors[nFloors-floor-1];
    	
    	lblInnerDoor.setText(CLOSED);
		lblOuterDoor.setText(CLOSED);
	}
    
    public void enableOutsideButtonsFloor(int floor) {
		//int floorIndex = Arrays.asList(floors).indexOf(floor);
		JButton btnUp = btnsLiftUp[nFloors-floor-1];
		JButton btnDown = btnsLiftDown[nFloors-floor-1];
		
		btnUp.setEnabled(true);
		btnDown.setEnabled(true);
	}
    
    /**
     * Handles button events
     * @param pEvent Click on button event. pEvent != null.
     */
    public void actionPerformed( ActionEvent pEvent )
    {
    	String command = pEvent.getActionCommand( );
        
        // Floor commands (up and down)
        for (int i=0; i<nFloors; i++) {
        	int floor = floors[i];
        	String cmdUp = cmdsLiftUp[i];
        	String cmdDown = cmdsLiftDown[i];
        	
        	// up
        	if (command.equals(cmdUp)) {
        		JButton btnUp = btnsLiftUp[i];
        		btnUp.setEnabled(false);
        		principal.outsideActionUp(floor);
        		return;
        	}
        	
        	// down
        	if (command.equals(cmdDown)) {
        		JButton btnDown = btnsLiftDown[i];
        		btnDown.setEnabled(false);
        		principal.outsideActionDown(floor);
        		return;
        	}
        }
    }
}
