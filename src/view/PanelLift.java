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
    private JLabel lblStatus;
    
    /**
     * TextField for current direction
     */
    private JTextField txtStatus;

    
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
        lblStatus = new JLabel( "Status: " );

        txtFloor = new JTextField( principal.getCurrentFloor() );
        txtFloor.setEditable( false );
        txtStatus = new JTextField( principal.getStatus() );
        txtStatus.setEditable( false );

        JPanel panelLift = new JPanel( new GridLayout( 1, 4 ) );
        panelLift.add( lblFloor );
        panelLift.add( txtFloor );
        panelLift.add( lblStatus );
        panelLift.add( txtStatus );

        add( panelLift, BorderLayout.CENTER );
    }


    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Updates the fields with the state of the lift. <br>
     * <b>post: </b> The fields show current state.
    */
    public void refresh() {
    	txtFloor.setText( principal.getCurrentFloor() + "" );
    	txtStatus.setText( principal.getStatus() );
	}

}
