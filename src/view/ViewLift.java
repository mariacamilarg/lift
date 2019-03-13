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
    private Lift lift;

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
        setSize( 800, 800 );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        // Build main connection
        lift = new Lift( );

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
    // Methods
    // -----------------------------------------------------------------

    /**
     * Actualiza los datos actuales del lift. <br>
     * <b>post: </b> Se actualizaron los campos de la ventana que contienen la informaci�n del lift.
     */
    public void refresh( )
    {
    	/**
        String nombre, apellido, genero, fechaI, fechaN, imagen;
        double salario;

        nombre = lift.darNombre( );
        apellido = lift.darApellido( );

        int iGenero = lift.darGenero( );
        if( iGenero == 1 )
        {
            genero = "masculino";
        }
        else
        {
            genero = "femenino";
        }

        fechaI = lift.darFechaIngreso( );
        fechaN = lift.darFechaNacimiento( );
        salario = lift.darSalario( );
        imagen = lift.darImagen( );

        panelDatos.actualizarCampos( nombre, apellido, genero, fechaI, fechaN, imagen );
        panelDatos.actualizarSalario( salario );

        panelConsultas.limpiarCampos( );
        */

        validate( );
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
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
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

	public void outsideActionDown(int floor) {
		// TODO hacer
	}

	public void insideActionFloor(int i) {
		// TODO HACER
		
	}

}