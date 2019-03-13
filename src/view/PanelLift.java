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

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 * Panel that displays each floor status and actions
 */
@SuppressWarnings("serial")
public class PanelLift extends JPanel
{

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

	/**
     * Principal main class
     */
	private ViewLift principal;

    // -----------------------------------------------------------------
    // Atributes of the view
    // -----------------------------------------------------------------

    /**
     * Label for floor number.
     */
    private JLabel lblFloor;

    /**
     * TextField for current floor
     */
    private JTextField txtFloor;

    /**
     * Label for direction.
     */
    private JLabel lblDirection;
    
    /**
     * TextField for current direction
     */
    private JTextField txtDirection;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Build the panel with the current position and direction of the lift. <br>
     * <b>post: </b> Panel is initialized.
     * @param pPrincipal Main view of the app. pPrincipal != null.
     */
    public PanelLift( ViewLift pPrincipal )
    {
        principal = pPrincipal;

        setLayout( new BorderLayout( ) );
        setBorder( new CompoundBorder( new EmptyBorder( 0, 0, 5, 0 ), new TitledBorder( "Current Status" ) ) );

        lblFloor = new JLabel( "Floor: " );
        lblDirection = new JLabel( "Direction: " );

        txtFloor = new JTextField( "0" );
        txtFloor.setEditable( false );
        txtDirection = new JTextField( "-" );
        txtDirection.setEditable( false );

        JPanel panelLift = new JPanel( new GridLayout( 1, 4 ) );
        panelLift.add( lblFloor );
        panelLift.add( txtFloor );
        panelLift.add( lblDirection );
        panelLift.add( txtDirection );

        add( panelLift, BorderLayout.CENTER );
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

}
