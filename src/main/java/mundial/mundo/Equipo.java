package mundial.mundo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Es la clase que representa un equipo del mundial <br>
 * <b>inv: </b> <br>
 * jugadores != null <br>
 * pais != null <br>
 * director != null <br>
 * imagen != null <br>
 * No hay dos o más jugadores con el mismo nombre <br>
 */

public class Equipo implements Serializable
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Indicador de versián para la serializacián
     */
    private static final long serialVersionUID = 200L;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el vector con los jugadores del equipo
     */
    private ArrayList jugadores;

    /**
     * Es el nombre del paás que representa el equipo
     */
    private String pais;

    /**
     * Es el nombre del director tácnico del equipo
     */
    private String director;

    /**
     * Es la ruta de la imagen con la bandera del equipo
     */
    private String imagen;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo Equipo con los datos suministrados y sin jugadores
     *
     * @param paisE     Es el paás del equipo - paisE != null
     * @param directorE Es el nombre del director tácnico del equipo - directorE != null
     * @param imagenE   Es la bandera del equipo - imagenE != null
     */
    public Equipo( String paisE, String directorE, String imagenE )
    {
        jugadores = new ArrayList( );
        pais = paisE;
        director = directorE;
        imagen = imagenE;

        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Mátodos
    // -----------------------------------------------------------------

    /**
     * Retorna un jugador del equipo dado su nombre.
     * @param nombreJ Es el nombre del jugador a buscar - nombreJ != null
     * @return El jugador cuyo nombre es igual al nombre dado. Si no se encontrá retorna null.
     */
    public Jugador darJugador( String nombreJ )
    {
        Jugador jugadorBuscado = null;
        boolean esta = false;

        for( int i = 0; i < jugadores.size( ) && !esta; i++ )
        {
            Jugador j = ( Jugador )jugadores.get( i );
            if( j.darNombre( ).equalsIgnoreCase( nombreJ ) )
            {
                jugadorBuscado = j;
                esta = true;
            }
        }
        return jugadorBuscado;
    }

    /**
     * Adiciona un jugador al equipo. <br>
     * <b>post: </b> El jugador j ha sido agregado al equipo
     * @param j El nuevo jugador para adicionar al equipo - j!=null
     * @throws ElementoExisteException Si ya existáa un jugador con el mismo nombre
     */
    public void agregarJugador( Jugador j ) throws ElementoExisteException
    {
        if( darJugador( j.darNombre( ) ) != null )
            throw new ElementoExisteException( "El jugador " + j.darNombre( ) + " ya existe en el equipo" );

        jugadores.add( j );

        verificarInvariante( );
    }

    /**
     * Retorna el paás del equipo
     * @return Se retorná el paás al que pertenece el equipo
     */
    public String darPais( )
    {
        return pais;
    }

    /**
     * Retorna el nombre del director tácnico del equipo
     * @return Se retorná el director del equipo
     */
    public String darDirector( )
    {
        return director;
    }

    /**
     * Retorna la ruta de la imagen con la bandera del equipo
     * @return Se retorná la ruta de la imagen
     */
    public String darImagen( )
    {
        return imagen;
    }

    /**
     * Retorna el vector con los nombres de las jugadores del equipo
     * @return Se retorná el vector con los nombres de los jugadores
     */
    public ArrayList darNombresJugadores( )
    {
        ArrayList nombresJugadores = new ArrayList( );
        for( int i = 0; i < jugadores.size( ); i++ )
        {
            Jugador j = ( Jugador )jugadores.get( i );
            nombresJugadores.add( j.darNombre( ) );
        }
        return nombresJugadores;
    }

    /**
     * Calcula el valor de la námina del equipo
     * @return Se retorná el valor de la námina del equipo
     */
    public double calcularValorNomina( )
    {
        double valorTotal = 0;
        for( int i = 0; i < jugadores.size( ); i++ )
        {
            Jugador j = ( Jugador )jugadores.get( i );
            valorTotal = valorTotal + j.darSalario( );
        }
        return valorTotal;
    }

    /**
     * Modificacián la informacián del jugador con el nombre dado. <br>
     * <b>pre: </n> nombre pertenece a unos de los jugadores del equipo. <br>
     * @param nombre El nombre del jugador al que se le va a modificar la informacián - nombre!=null
     * @param edad La nueva edad del jugador - edad>0
     * @param posicion La nueva posicián del jugador - posiciom!=null
     * @param altura La nueva altura del jugador - altura>0
     * @param peso El nuevo peso del jugador - peso>0
     * @param salario El nuevo salario del jugador - salario>0
     * @param imagen La nueva imagen del jugador - imagen!=null
     */
    public void modificarInformacionJugador( String nombre, int edad, String posicion, double altura, double peso, double salario, String imagen )
    {
        Jugador jugador = darJugador( nombre );
        jugador.cambiarAltura( altura );
        jugador.cambiarEdad( edad );
        jugador.cambiarImagen( imagen );
        jugador.cambiarPeso( peso );
        jugador.cambiarPosicion( posicion );
        jugador.cambiarSalario( salario );
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase: <br>
     * jugadores != null <br>
     * pais != null <br>
     * director != null <br>
     * imagen != null <br>
     * No hay dos o más jugadores con el mismo nombre <br>
     */
    private void verificarInvariante()
    {
        assert jugadores != null : "La lista de jugadores no debe ser nula";
        assert pais != null : "El paás del equipo es inválido";
        assert director != null : "El director tácnico es inválido";
        assert imagen != null : "La ruta de la imagen es inválida";
        assert !buscarJugadoresConElMismoNombre() : "Hay dos jugadores con el mismo nombre";
    }

    /**
     * Este mátodo sirve para revisar si hay jugadores repetidos dentro del equipo.
     * @return Retorna true si hay un jugador que aparece repetido dentro de la lista de jugadores. Retorna false en caso contrario.
     */
    private boolean buscarJugadoresConElMismoNombre( )
    {
        for( int i = 0; i < jugadores.size( ); i++ )
        {
            Jugador j1 = ( Jugador )jugadores.get( i );
            for( int j = 0; j < jugadores.size( ); j++ )
            {
                if( i != j )
                {
                    Jugador j2 = ( Jugador )jugadores.get( j );
                    if( j1.darNombre( ).equalsIgnoreCase( j2.darNombre( ) ) )
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}