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
        panelFloors.add( new JLabel("OUTSIDE ACTIONS") );
        panelFloors.add( new JLabel("OUTSIDE ACTIONS") );
        
        int counter = 0;
        for (int i=topFloor; i>=bottomFloor; i--) {
        	// Floor
        	JLabel lblFloor = new JLabel( Integer.toString(i) );
        	panelFloors.add( lblFloor );
        	floors[counter] = i;
        	
        	// Inner Doors
        	JLabel lblInnerDoor = new JLabel( "Closed" );
        	panelFloors.add(lblInnerDoor);
        	lblsInnerDoors[counter] = lblInnerDoor;
        	
        	// Outer Doors
        	JLabel lblOuterDoor = new JLabel( "Closed" );
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
     * Handles button events
     * @param pEvent Click on button event. pEvent != null.
     */
    public void actionPerformed( ActionEvent pEvent )
    {
    	String command = pEvent.getActionCommand( );
        
        // Floor commands (up and down)
        for (int i=0; i<nFloors; i++) {
        	int floor = floors[i];
        	String cmd_up = cmdsLiftUp[i];
        	String cmd_down = cmdsLiftDown[i];
        	
        	// up
        	if (command.equals(cmd_up)) {
        		principal.outsideActionUp(floor);
        		return;
        	}
        	
        	// down
        	if (command.equals(cmd_down)) {
        		principal.outsideActionDown(floor);
        		return;
        	}
        }
    }

}
