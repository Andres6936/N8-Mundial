package mundial.interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 * Es el panel donde se encuentran los botones principales de la aplicacián
 */
public class PanelBotones extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para agregar un equipo
     */
    private static final String AGREGAR_EQUIPO = "AgregarEquipo";

    /**
     * Comando para agregar un jugador
     */
    private static final String AGREGAR_JUGADOR = "AgregarJugador";

    /**
     * Comando para calcular el valor de la námina de un equipo
     */
    private static final String CALCULAR_NOMINA = "CalcularNomina";

    /**
     * Comando para cargar los equipos y jugadores del mundial
     */
    private static final String MODIFICAR_INFORMACION_JUGADORES = "ModificarInformacionJugadores";

    /**
     * Comando Opcián 1
     */
    private static final String OPCION_1 = "OPCION_1";

    /**
     * Comando Opcián 2
     */
    private static final String OPCION_2 = "OPCION_2";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicacián
     */
    private InterfazMundial principal;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Es el botán para agregar un equipo
     */
    private JButton botonAgregarEquipo;

    /**
     * Es el botán para agregar un jugador
     */
    private JButton botonAgregarJugador;

    /**
     * Es el botán para calcular la námina de un equipo
     */
    private JButton botonCalcularNomina;

    /**
     * Es el botán para modificar la informacián de los jugadores
     */
    private JButton botonModificarInformacionJugadores;

    /**
     * Botán Opcián 1
     */
    private JButton botonOpcion1;

    /**
     * Botán Opcián 2
     */
    private JButton botonOpcion2;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param ventana Ventana principal - ventana!=null
     */
    public PanelBotones( InterfazMundial ventana )
    {
        principal = ventana;
        setBorder( new CompoundBorder( new EmptyBorder( 5, 5, 5, 5 ), new TitledBorder( "Opciones" ) ) );
        setLayout( new GridLayout( 2, 3, 3, 3 ) );

        // Botán agregar equipos
        botonAgregarEquipo = new JButton( "Agregar Equipo" );
        botonAgregarEquipo.setActionCommand(AGREGAR_EQUIPO);
        botonAgregarEquipo.addActionListener(this);
        add(botonAgregarEquipo);

        // Botán agregar jugador
        botonAgregarJugador = new JButton("Agregar Jugador");
        botonAgregarJugador.setActionCommand(AGREGAR_JUGADOR);
        botonAgregarJugador.addActionListener(this);
        add(botonAgregarJugador);

        // Botán calcular námina
        botonCalcularNomina = new JButton("Calcular Námina");
        botonCalcularNomina.setActionCommand(CALCULAR_NOMINA);
        botonCalcularNomina.addActionListener(this);
        add(botonCalcularNomina);

        // Botán modificar informacián jugadores
        botonModificarInformacionJugadores = new JButton("Modificar Jugadores");
        botonModificarInformacionJugadores.setActionCommand(MODIFICAR_INFORMACION_JUGADORES);
        botonModificarInformacionJugadores.addActionListener(this);
        add(botonModificarInformacionJugadores);

        // Botán opcián 1
        botonOpcion1 = new JButton("Opcián 1");
        botonOpcion1.setActionCommand(OPCION_1);
        botonOpcion1.addActionListener(this);
        add(botonOpcion1);

        // Botán opcián 2
        botonOpcion2 = new JButton("Opcián 2");
        botonOpcion2.setActionCommand(OPCION_2);
        botonOpcion2.addActionListener(this);
        add(botonOpcion2);
    }

    // -----------------------------------------------------------------
    // Mátodos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     *
     * @param evento Accián que generá el evento - evento!=null
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );

        if( AGREGAR_EQUIPO.equals( comando ) )
        {
            principal.mostrarDialogoAgregarEquipo( );
        }
        else if( AGREGAR_JUGADOR.equals( comando ) )
        {
            principal.mostrarDialogoAgregarJugador( );
        }
        else if( CALCULAR_NOMINA.equals( comando ) )
        {
            principal.calcularValorNomina( );
        }
        else if( MODIFICAR_INFORMACION_JUGADORES.equals( comando ) )
        {
            principal.modificarInformacionJugadores( );
        }
        else if( OPCION_1.equals( comando ) )
        {
            principal.reqFuncOpcion1( );
        }
        else if( OPCION_2.equals( comando ) )
        {
            principal.reqFuncOpcion2( );
        }
    }

}
