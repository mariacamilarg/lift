/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * LIFT
 *
 * Software and Data Engineering
 * Télécom SudParis 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package view;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel with banner image.
 */
@SuppressWarnings("serial")
public class PanelImage extends JPanel
{
    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Constructor of panel. <br>
     * <b>post: </b> Panel is initialized.
     */
    public PanelImage( )
    {
        JLabel image = new JLabel( );
        ImageIcon icon = new ImageIcon( "./data/img/header.png" );
        image = new JLabel( "" );
        image.setIcon( icon );
        add( image );
    }

}
