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
     * Buttons to order elevator up
     */
    private JButton[] btnsLiftUp;
    
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
        btnsLiftUp = new JButton[nFloors];
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
        	btnsLiftUp[counter] = btnLiftUp;
        	
        	String commandDown = "OUTSIDE_DOWN_" + i;
        	JButton btnLiftDown = new JButton( );
        	btnLiftDown.setText( "DOWN v" );
        	btnLiftDown.setActionCommand( commandDown );
        	btnLiftDown.addActionListener( this );
        	panelFloors.add(btnLiftDown);
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
     * Manejo de los eventos de los botones.
     * @param pEvento Evento de click sobre un bot�n. pEvento != null.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
    	//TODO hacer 
        String command = pEvento.getActionCommand( );

        if( command.equals( "OUTSIDE_DOWN_1" ) )
        {
            principal.outsideActionDown(1);
        }
    }

}
