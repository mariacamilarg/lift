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
        
        JPanel panelActions = new JPanel( new GridLayout( nFloors+2, 1 ) );
        
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
        
        // Emergency button
    	cmdEmergency = "EMERGENCY";
    	btnEmergency = new JButton( );
    	btnEmergency.setText( cmdEmergency );
    	btnEmergency.setActionCommand( cmdEmergency );
    	btnEmergency.addActionListener( this );
    	panelActions.add(btnEmergency);
    	
    	// Block Doors button
    	cmdBlockDoors = "BLOCK_DOORS";
    	btnBlockDoors = new JButton( );
    	btnBlockDoors.setText( cmdBlockDoors );
    	btnBlockDoors.setActionCommand( cmdBlockDoors );
    	btnBlockDoors.addActionListener( this );
    	panelActions.add(btnBlockDoors);
        

    	add( panelActions, BorderLayout.CENTER );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    
    /**
     * Actualiza los campos del panel con la informaci�n del empleado. <br>
     * <b>post: </b> Los campos muestran la nueva informaci�n.
     * @param pNombre Nombre del empleado. pNombre != null && pNombre != "".
     * @param pApellido Apellido del empleado. pApellido != null && pApellido != "".
     * @param pSexo Sexo del empleado. pSexo pertenece a {"m","f"}.
     * @param pFechaIngreso Fecha de ingreso a la empresa. pFechaIngreso != null && pFechaIngreso != "".
     * @param pFechaNacimiento Fecha de Nacimiento del empleado. pFechaNacimiento != null && pFechaNacimiento != "".
     * @param pImagen Ruta donde se encuentra la imagen. pImagen != null.
     
    public void actualizarCampos( String pNombre, String pApellido, String pSexo, String pFechaIngreso, String pFechaNacimiento, String pImagen )
    {
        txtNombre.setText( pNombre );
        txtApellido.setText( pApellido );
        txtGenero.setText( pSexo );
        txtFIngreso.setText( pFechaIngreso );
        txtFNacimiento.setText( pFechaNacimiento );
        remove( lblImagen );
        lblImagen = new JLabel( new ImageIcon( "./data/imagenes/" + pImagen ) );
        lblImagen.setHorizontalAlignment( JLabel.CENTER );
        lblImagen.setVerticalAlignment( JLabel.CENTER );
        lblImagen.setPreferredSize( new Dimension( 170, 0 ) );
        add( lblImagen, BorderLayout.EAST );

    }
    */

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
        		principal.insideActionFloor(floor);
        		return;
        	}
        }
        
        // Emergency command
        if( command.equals(cmdEmergency) )
        {
            principal.insideActionEmergency();
        }
        
        // Emergency command
        else if( command.equals(cmdBlockDoors) )
        {
            principal.insideActionBlockDoors();
        }
    }

}
