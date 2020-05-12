package mundial.mundo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

/**
 * Es la clase para representar el mundial de fátbol <br>
 * <b>inv: </b> <br>
 * equipos != null <br>
 * No hay dos equipos del mismo paás
 */

public class Mundial
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Nombre del archivo de registro de errores del programa
     */
    private static final String LOG_FILE = "data/error.log";

    /**
     * Námero de datos a leer para un equipo
     */
    public static final int DATOS_EQUIPO = 4;

    /**
     * Námero de datos a leer para un jugador
     */
    public static final int DATOS_JUGADOR = 7;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * La lista de equipos del mundial
     */
    private ArrayList<Equipo> equipos = new ArrayList<>();

    /**
     * Es el archivo de donde se cargan y salvan los equipos
     */
    private final String archivoMundial;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo mundial de fátbol. <br>
     * Si el archivo indicado no existe, entonces el mundial se crea vacáo y su estado se guardará en el archivo indicado.<br>
     * Si el archivo existe, entonces de ál se saca la informacián de los equipos y jugadores.
     * @param nombreArchivoMundial Es el nombre del archivo que contiene los datos del mundial
     * @throws PersistenciaException Se lanza esta excepcián si se encuentran problemas cargando los datos del archivo
     */
    public Mundial( String nombreArchivoMundial ) throws PersistenciaException
    {
        archivoMundial = nombreArchivoMundial;

        URL resource = getClass().getClassLoader().getResource(nombreArchivoMundial);
        if (resource != null) {
            File archivo = new File(resource.getFile());

            if (archivo.exists()) {
                // El archivo existe: se debe recuperar de allá el estado del modelo del mundo
                try {
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
                    equipos = (ArrayList)ois.readObject();
                    ois.close();
                } catch (Exception e) {
                    // Se atrapan en este bloque todos los tipos de excepcián
                    registrarError(e);
                    throw new PersistenciaException("Error fatal: imposible restaurar el estado del programa (" + e.getMessage() + ")");
                }
            }
        } else {
            // El archivo no existe: es la primera vez que se ejecuta el programa
            equipos = new ArrayList<>();
        }
        verificarInvariante();
    }

    // -----------------------------------------------------------------
    // Mátodos
    // -----------------------------------------------------------------

    /**
     * Retorna un equipo del mundial dado su paás.
     * @param nombreEquipo el nombre del equipo a buscar - nombreEquipo != null
     * @return El equipo cuyo nombre es igual al nombre dado. Si no se encontrá se retorna null.
     */
    public Equipo darEquipo( String nombreEquipo )
    {
        Equipo equipoBuscado = null;
        boolean esta = false;

        for( int i = 0; i < equipos.size( ) && !esta; i++ )
        {
            Equipo e = (Equipo)equipos.get(i);
            if( e.darPais( ).equalsIgnoreCase( nombreEquipo ) )
            {
                equipoBuscado = e;
                esta = true;
            }
        }
        return equipoBuscado;
    }

    /**
     * Agrega un nuevo equipo al mundial
     * @param pais El nombre del paás que representa el equipo - pais != null
     * @param director El nombre del director tácnico del equipo - director != null
     * @param imagen La imagen con la bandera del paás del equipo - imagen != null
     * @throws ElementoExisteException Esta excepcián se lanza si ya existe un equipo con el mismo nombre
     */
    public void agregarEquipo( String pais, String director, String imagen ) throws ElementoExisteException
    {
        if( darEquipo( pais ) != null )
            throw new ElementoExisteException( "El equipo " + pais + " ya existe en el mundial" );

        Equipo equipo = new Equipo( pais, director, imagen );
        equipos.add( equipo );

        verificarInvariante( );
    }

    /**
     * Retorna un vector con los nombres de los equipos
     *
     * @return Vector con los nombres de los equipos
     */
    public ArrayList<String> darNombresEquipos()
    {
        ArrayList<String> nombresEquipos = new ArrayList<>();
        for (Equipo e : equipos) {
            nombresEquipos.add(e.darPais());
        }
        return nombresEquipos;
    }

    /**
     * Agrega un nuevo jugador al equipo
     * @param nombreEquipo El nombre del equipo para adicionar el jugador - hay un equipo con ese nombre en el mundial
     * @param nombreJ El nombre del jugador a crear - nombreJ != null
     * @param edadJ El námero de aáos del jugador - edadJ > 0
     * @param posicionJ La posicián en la que juega el jugador - posicionJ != null
     * @param alturaJ La altura en metros del jugador - alturaJ > 0
     * @param pesoJ El peso en kilogramos del jugador - pesoJ > 0
     * @param salarioJ El salario anual del jugador en millones de dálares - salarioJ > 0
     * @param imagenJ La ruta de la imagen con la foto del jugador - imagenJ != null
     * @throws ElementoExisteException Esta excepcián se lanza si ya existe otro jugador en el equipo con el mismo nombre
     */
    public void agregarJugadorAEquipo( String nombreEquipo, String nombreJ, int edadJ, String posicionJ, double alturaJ, double pesoJ, double salarioJ, String imagenJ ) throws ElementoExisteException
    {
        Equipo e = darEquipo( nombreEquipo );
        e.agregarJugador( new Jugador( nombreJ, edadJ, posicionJ, alturaJ, pesoJ, salarioJ, imagenJ ) );
        verificarInvariante( );
    }

    /**
     * Calcula el valor de la námina de un equipo y genera el reporte correspondiente
     * @param nombreEquipo El nombre del equipo para el que se va a calcular el valor de la námina
     * @param rutaReporte el directorio donde debe generarse el reporte - rutaReporte != null
     * @return Retorna el nombre del archivo en el que se generá el reporte
     * @throws IOException Se genera esta excepcián si hay problemas salvando el archivo con el reporte
     */
    public String calcularValorNomina( String nombreEquipo, String rutaReporte ) throws IOException
    {
        // Genera el nombre para el archivo con el reporte de la námina
        String strTiempo = Long.toString( System.currentTimeMillis( ) );
        String nombreArchivo = nombreEquipo + "_" + strTiempo + ".nomina";

        // Guarda el archivo con el reporte
        File directorioReportes = new File( rutaReporte );
        if( !directorioReportes.exists( ) )
            directorioReportes.mkdirs( );
        File archivoReporte = new File( directorioReportes, nombreArchivo );
        PrintWriter out = new PrintWriter( archivoReporte );

        Date fecha = new Date( );
        out.println("Mundial_CUPI2 - Reporte Námina - Equipo: " + nombreEquipo);
        out.println("Fecha:            " + fecha.toString());
        out.println("Total Námina:          " + darEquipo(nombreEquipo).calcularValorNomina() + " millones anuales");
        out.close();

        return nombreArchivo;

    }

    /**
     * Actualiza la informacián de los jugadores a partir de la informacián contenida en un archivo de texto.<br>
     * El archivo debe tener una lánea en la cual se encuentra el námero de jugadores a los que se les va a modificar la informacián, <br>
     * y luego debe haber una lánea por cada jugador al que se le va a modificar la informacián. <br>
     * Cada lánea tiene el siguiente formato: <nombre paás>;<nombre jugador>;<edad>;<posicián>;<altura>;<peso>;<salario>;<imágen>
     * @param archivo El archivo que tiene la informacián de los jugadores - archivo!= null
     * @throws IOException Se lanza esta excepcián si hay problemas leyendo el archivo de los jugadores
     * @throws ArchivoJugadoresException Se lanza esta excepcián si el archivo no cumple con el formato esperado
     */
    public void modificarInformacionJugadores( File archivo ) throws ArchivoJugadoresException, IOException
    {

        String linea = null;
        int numeroJugadores = -1;

        // Abre el archivo con los equipos y jugadores. Si no existe, el constructor del flujo lanza la excepcián FileNotFoundException
        BufferedReader lector = new BufferedReader( new FileReader( archivo ) );

        // Lee la primera lánea del archivo (el námero de equipos)
        linea = lector.readLine( );

        if( linea != null && !linea.equals( "" ) )
        {
            linea = linea.trim( );

            try
            {
                numeroJugadores = Integer.parseInt( linea );
            }
            catch( NumberFormatException e )
            {
                // Hubo un error al leer el námero de jugadores
                throw new ArchivoJugadoresException("El archivo no tiene el formato esperado. Se esperaba el námero de jugadores");
            }
            int cont = 0;

            linea = lector.readLine( );
            linea = linea.trim( );

            while( cont < numeroJugadores )
            {
                modificarJugador( linea );
                linea = lector.readLine( );
                cont++;

                if( linea != null )
                {
                    linea = linea.trim( );

                }
                else if( cont < numeroJugadores )
                {
                    throw new ArchivoJugadoresException("El archivo no tiene el formato esperado. Se esperaba la informacián de " + numeroJugadores + " jugadores");
                }
            }

        }
        else
        {
            // Hubo un error al leer el námero de jugadores
            throw new ArchivoJugadoresException("El archivo no tiene el formato esperado. Se esperaba el námero de jugadores");
        }

        lector.close( );

    }

    /**
     * Carga un equipo a partir de la informacián contenida en un archivo
     * @param info Es el lector de archivo del cual se lee la informacián del equipo - El lector de archivo ya está listo para leer
     * @throws ArchivoJugadoresException Se lanza esta excepcián si el archivo no tiene el formato esperado
     * @throws IOException Si hay errores leyendo la siguiente lánea del archivo
     * @throws ArchivoJugadoresException Si la lánea del archivo no tiene el formato esperado
     */

    private void modificarJugador( String info ) throws ArchivoJugadoresException, IOException
    {
        String[] nuevosDatos = info.split( ";" );
        // Verificar que se tengan los 8 datos del jugador
        if( nuevosDatos.length == 8 )
        {
            String nombreEquipo = nuevosDatos[ 0 ];
            String nombreJugador = nuevosDatos[ 1 ];
            Equipo equipo = darEquipo( nombreEquipo );

            if( equipo != null )
            {
                if( equipo.darJugador( nombreJugador ) != null )
                {
                    int edad = Integer.parseInt( nuevosDatos[ 2 ] );
                    String posicion = nuevosDatos[ 3 ];
                    double altura = Double.parseDouble( nuevosDatos[ 4 ] );
                    double peso = Double.parseDouble( nuevosDatos[ 5 ] );
                    double salario = Double.parseDouble( nuevosDatos[ 6 ] );
                    String imagen = nuevosDatos[ 7 ];

                    if( edad < 0 || posicion.equals( "" ) || altura < 0 || peso < 0 || salario < 0 || imagen.equals( "" ) )
                    {
                        throw new ArchivoJugadoresException("La datos del jugador " + nombreJugador + " no son válidos");
                    }

                    equipo.modificarInformacionJugador( nombreJugador, edad, posicion, altura, peso, salario, imagen );
                }
                else
                {
                    throw new ArchivoJugadoresException( "El jugador con nombre " + nombreJugador + " no se encuentra en el campeonato" );
                }
            }
            else
            {
                throw new ArchivoJugadoresException( "El archivo no tiene el formato esperado. El equipo con nombre " + nombreEquipo + " no se encuentra en el campeonato" );
            }
        }
        else
        {
            throw new ArchivoJugadoresException( "El archivo no tiene el formato esperado. Se esperaban los ocho datos del jugador" );
        }
    }

    // -----------------------------------------------------------------
    // Persistencia
    // -----------------------------------------------------------------

    /**
     * Salva el mundial en un archivo binario
     * @throws PersistenciaException Se lanza esta excepcián si hay problemas guardando la informacián del mundial en el archivo
     */
    public void salvarMundial( ) throws PersistenciaException
    {
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream( archivoMundial ) );
            oos.writeObject( equipos );
            oos.close( );
        }
        catch( IOException e )
        {
            registrarError( e );
            throw new PersistenciaException( "Error al salvar: " + e.getMessage( ) );
        }
    }

    /**
     * Registra en el archivo de log del programa toda la informacián referente a una excepcián, ocurrida durante el proceso de persistencia
     * @param excepcion Es la excepcián que contiene la informacián del error
     */
    public void registrarError( Exception excepcion )
    {
        try
        {
            FileWriter out = new FileWriter( LOG_FILE, true );
            PrintWriter log = new PrintWriter( out );
            log.println( "---------------------------------------" );
            log.println( "Mundial.java :" + new Date( ).toString( ) );
            log.println( "---------------------------------------" );
            excepcion.printStackTrace( log );
            log.close( );
            out.close( );
        }
        catch( IOException e )
        {
            excepcion.printStackTrace( );
            e.printStackTrace( );
        }
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase: <br>
     * equipos != null <br>
     * No hay dos equipos del mismo paás
     */
    private void verificarInvariante( )
    {
        assert equipos != null : "La lista de equipos es null";
        assert !buscarEquiposConElMismoNombre() : "Hay dos equipos con el mismo paás";
    }

    /**
     * Este mátodo sirve para revisar si hay equipos repetidos dentro del mundial.
     * @return Retorna true si hay un equipo que aparece repetido dentro de la lista de equipos. Retorna false en caso contrario.
     */
    private boolean buscarEquiposConElMismoNombre( )
    {
        for( int i = 0; i < equipos.size( ); i++ )
        {
            Equipo e1 = ( Equipo )equipos.get( i );
            for( int j = 0; j < equipos.size( ); j++ )
            {
                if( i != j )
                {
                    Equipo e2 = ( Equipo )equipos.get( j );
                    if( e1.darPais( ).equals( e2.darPais( ) ) )
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // -----------------------------------------------------------------
    // Puntos de Extensián
    // -----------------------------------------------------------------

    /**
     * Mátodo para la extensián 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * Mátodo para la extensián2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }

}