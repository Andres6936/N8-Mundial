package uniandes.cupi2.mundial.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Es el diálogo para crear un nuevo equipo
 */
public class DialogoCrearEquipo extends JDialog
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la clase principal de la interfaz
     */
    private InterfazMundial principal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el panel con los datos para crear el nuevo equipo
     */
    private PanelCrearEquipo panelDatos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el diálogo
     *
     * @param ventana Es una referencia a la clase principal de la interfaz - ventana!=null
     */
    public DialogoCrearEquipo( InterfazMundial ventana )
    {
        super( ventana, true );
        principal = ventana;

        panelDatos = new PanelCrearEquipo( this );

        add( panelDatos, BorderLayout.CENTER );

        setTitle( "Crear Equipo" );
        setResizable( false );
        pack( );
        centrar( );

    }

    // -----------------------------------------------------------------
    // Mátodos
    // -----------------------------------------------------------------

    /**
     * Centra la ventana en la pantalla
     */
    private void centrar( )
    {
        Dimension screen = Toolkit.getDefaultToolkit( ).getScreenSize( );
        int xEsquina = ( screen.width - getWidth( ) ) / 2;
        int yEsquina = ( screen.height - getHeight( ) ) / 2;
        setLocation( xEsquina, yEsquina );
    }

    /**
     * Crea el equipo y lo guarda
     */
    public void crearEquipo( )
    {
        boolean parametersOk = true;
        String pais = panelDatos.darPais( );
        String director = panelDatos.darDirector( );
        String imagen = panelDatos.darImagen( );

        if( ( pais.equals( "" ) || director.equals( "" ) ) || imagen.equals( "" ) )
        {
            parametersOk = false;
            JOptionPane.showMessageDialog( this, "Se deben ingresar todos los datos para crear el equipo", "Error", JOptionPane.ERROR_MESSAGE );
        }
        if( parametersOk )
        {
            boolean ok = principal.crearEquipo( pais, director, imagen );
            if( ok )
                dispose( );
        }
    }
}
