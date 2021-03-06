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
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 * Panel that displays each floor status and actions
 */
@SuppressWarnings("serial")
public class PanelActions extends JPanel implements ActionListener
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
     * Button commands to each floor
     */
    private String[] cmdsFloors;
    
    /**
     * Buttons to each floor
     */
    private JButton[] btnsFloors;
    
    /**
     * Boolean to know if there is an emergency
     */
    private boolean inEmergency;
    
    /**
     * Button command for emergency
     */
    private String cmdEmergency;
    
    /**
     * Button to declare an emergency
     */
    private JButton btnEmergency;
    
    /**
     * Button command for block doors
     */
    private String cmdBlockDoors;
    
    /**
     * Button to block doors open
     */
    private JButton btnBlockDoors;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Build the panel with the inside-lift buttons. <br>
     * <b>post: </b> Panel is initialized.
     * @param pPrincipal Main view of the app. pPrincipal != null.
     */
    public PanelActions( ViewLift pPrincipal )
    {
        principal = pPrincipal;

        setLayout( new BorderLayout( ) );
        setBorder( new CompoundBorder( new EmptyBorder( 0, 0, 5, 0 ), new TitledBorder( "Inside Actions" ) ) );

        topFloor = principal.getTopFloor();
        bottomFloor = principal.getBottomFloor();
        nFloors = topFloor - bottomFloor + 1;
        
        floors = new int[nFloors];
        cmdsFloors = new String[nFloors];
        btnsFloors = new JButton[nFloors];
        
        JPanel panelActions = new JPanel( new GridLayout( nFloors+1, 1 ) );
        
        // Emergency and Block Doors Buttons
        JPanel panelEmergBlock = new JPanel( new GridLayout( 1, 2 ) );
        
        // Emergency button
        inEmergency = false;
    	cmdEmergency = "EMERGENCY";
    	btnEmergency = new JButton( );
    	btnEmergency.setText( cmdEmergency );
    	btnEmergency.setActionCommand( cmdEmergency );
    	btnEmergency.addActionListener( this );
    	panelEmergBlock.add(btnEmergency);
    	
    	// Block Doors button
    	cmdBlockDoors = "BLOCK_DOORS";
    	btnBlockDoors = new JButton( );
    	btnBlockDoors.setText( cmdBlockDoors );
    	btnBlockDoors.setActionCommand( cmdBlockDoors );
    	btnBlockDoors.addActionListener( this );
    	panelEmergBlock.add(btnBlockDoors);
    	
    	panelActions.add(panelEmergBlock);
        
    	// Floor buttons
        int counter = 0;
        for (int i=topFloor; i>=bottomFloor; i--) {
        	
        	// Floor button
        	String cmdFloor = "INSIDE_" + i;
        	JButton btnFloor = new JButton( );
        	btnFloor.setText( Integer.toString(i) );
        	btnFloor.setActionCommand( cmdFloor );
        	btnFloor.addActionListener( this );
        	panelActions.add(btnFloor);
        	
        	floors[counter] = i;
        	cmdsFloors[counter] = cmdFloor;
        	btnsFloors[counter] = btnFloor;
        	
        	// Counter
        	counter++;
        }

    	add( panelActions, BorderLayout.CENTER );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    public void enableInsideButtonFloor(int floor) {
		//int floorIndex = Arrays.asList(floors).indexOf(floor);
		JButton floorButton = btnsFloors[nFloors-floor-1];
		floorButton.setEnabled(true);
	}
    
    public void disableInsideButtonFloor(int floor) {
    	//int floorIndex = Arrays.asList(floors).indexOf(floor);
		JButton floorButton = btnsFloors[nFloors-floor-1];
		floorButton.setEnabled(false);
	}

    /**
     * Handles button events
     * @param pEvent Click on button event. pEvent != null.
     */
    public void actionPerformed( ActionEvent pEvent )
    {
        String command = pEvent.getActionCommand( );
        //System.out.println("Command received: " +command);
        
        // Floor commands
        for (int i=0; i<nFloors; i++) {
        	int floor = floors[i];
        	String cmdFloor = cmdsFloors[i];
        	
        	if (command.equals(cmdFloor)) {
        		//System.out.println("Command ap: " +cmdFloor);
        		JButton btnFloor = btnsFloors[i];
        		btnFloor.setEnabled(false);
        		principal.insideActionFloor(floor);
        		return;
        	}
        }
        
        // Emergency command
        if( command.equals(cmdEmergency) )
        {
            inEmergency = !inEmergency;
            
            for (int i=0; i<nFloors; i++) {
            	JButton btnFloor = btnsFloors[i];
            	btnFloor.setEnabled(!inEmergency);
            }
            btnBlockDoors.setEnabled(!inEmergency);
            
            principal.insideActionEmergency();
        }
        
        // Block doors command
        else if( command.equals(cmdBlockDoors) )
        {
            principal.insideActionBlockDoors();
        }
    }

}
