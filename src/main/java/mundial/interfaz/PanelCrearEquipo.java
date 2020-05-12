package mundial.interfaz;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * Es el panel donde se ingresan los datos para un nuevo equipo
 */
public class PanelCrearEquipo extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * El comando para el botán Crear
     */
    private static final String CREAR_EQUIPO = "CrearEquipo";

    /**
     * El comando para el botán Cancelar
     */
    private static final String CANCELAR = "Cancelar";

    /**
     * El comando para el botán Explorar
     */
    private static final String EXPLORAR = "Explorar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia al diálogo al que pertenece este panel
     */
    private DialogoCrearEquipo dialogo;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es la etiqueta "Paás: "
     */
    private JLabel etiquetaPais;

    /**
     * Es el campo de texto para ingresar el paás del equipo
     */
    private JTextField txtPais;

    /**
     * Es la etiqueta "Director: "
     */
    private JLabel etiquetaDirector;

    /**
     * Es el campo de texto para ingresar el director tácnico del equipo
     */
    private JTextField txtDirector;

    /**
     * Es la etiqueta "Imagen"
     */
    private JLabel etiquetaImagen;

    /**
     * Es el campo de texto para ingresar la ruta con la imagen del equipo
     */
    private JTextField txtImagen;

    /**
     * Es el botán usado para buscar la imagen
     */
    private JButton botonExplorar;

    /**
     * Es el botán que sirve para agregar un nuevo equipo
     */
    private JButton botonAgregarEquipo;

    /**
     * Es el botán que sirve para cerrar el diálogo sin guardar los cambios
     */
    private JButton botonCancelar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel creando las etiquetas y los campos de texto necesarios para crear un nuevo equipo
     * @param d Dialogo con el cual se crea un equipo
     */
    public PanelCrearEquipo( DialogoCrearEquipo d )
    {
        dialogo = d;
        setLayout( new GridBagLayout( ) );

        etiquetaPais = new JLabel("Paás: ");
        GridBagConstraints gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets( 3, 3, 3, 3 );
        add( etiquetaPais, gbc );

        txtPais = new JTextField( );
        txtPais.setPreferredSize( new Dimension( 80, 25 ) );
        gbc.gridx = 1;

        add( txtPais, gbc );

        etiquetaDirector = new JLabel("Director Tácnico: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add( etiquetaDirector, gbc );

        txtDirector = new JTextField( );
        txtDirector.setPreferredSize( new Dimension( 80, 25 ) );
        gbc.gridx = 1;
        add( txtDirector, gbc );

        etiquetaImagen = new JLabel( "Imagen: " );
        gbc.gridx = 0;
        gbc.gridy = 2;
        add( etiquetaImagen, gbc );

        JPanel panelImagen = new JPanel( );
        panelImagen.setLayout( new GridLayout( 1, 2, 3, 3 ) );

        txtImagen = new JTextField( );
        panelImagen.add( txtImagen );

        botonExplorar = new JButton( "Explorar" );
        botonExplorar.setActionCommand( EXPLORAR );
        botonExplorar.addActionListener( this );
        panelImagen.add( botonExplorar );

        gbc.gridx = 1;
        add( panelImagen, gbc );

        JPanel panelBotones = new JPanel( );
        panelBotones.setLayout( new GridBagLayout( ) );

        botonAgregarEquipo = new JButton( "Crear" );
        botonAgregarEquipo.setActionCommand( CREAR_EQUIPO );
        botonAgregarEquipo.addActionListener( this );
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelBotones.add( botonAgregarEquipo, gbc );

        botonCancelar = new JButton( "Cancelar" );
        botonCancelar.setActionCommand( CANCELAR );
        botonCancelar.addActionListener( this );
        gbc.gridx = 1;
        panelBotones.add( botonCancelar, gbc );

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add( panelBotones, gbc );

        setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
    }

    // -----------------------------------------------------------------
    // Mátodos
    // -----------------------------------------------------------------

    /**
     * Da el valor del campo de texto con el paás del equipo
     *
     * @return Se retorná el nombre del paás ingresado por el usuario
     */
    public String darPais( )
    {
        return txtPais.getText( );
    }

    /**
     * Da el valor del campo de texto con el director tácnico del equipo
     * @return Se retorná el nombre del director ingresado por el usuario
     */
    public String darDirector( )
    {
        return txtDirector.getText( );
    }

    /**
     * Da el valor del campo de texto con la ruta de la imagen de la bandera del equipo
     * @return Se retorná la ruta de la imagen ingresada por el usuario
     */
    public String darImagen( )
    {
        return txtImagen.getText( );
    }

    /**
     * Ejecuta una accián cuando se hace click sobre un botán
     * @param evento el evento del click sobre un botán - evento!=null
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );

        if( EXPLORAR.equals( comando ) )
        {
            JFileChooser fc = new JFileChooser("data/imagenes");
            fc.setDialogTitle( "Imagen de la bandera del equipo" );
            int resultado = fc.showOpenDialog( this );
            if( resultado == JFileChooser.APPROVE_OPTION )
            {
                File archivo = fc.getSelectedFile( );
                txtImagen.setText( "data/imagenes/" + archivo.getParentFile( ).getName( ) + "/" + archivo.getName( ) );
            }
        }
        else if( CREAR_EQUIPO.equals( comando ) )
        {
            dialogo.crearEquipo( );
        }
        else if( CANCELAR.equals( comando ) )
        {
            dialogo.dispose( );
        }
    }

}
