package uniandes.cupi2.mundial.mundo;

/**
 * Esta excepcián sirve para indicar que hubo un problema procesando el archivo con la informacián de los jugadores a modificar
 */
public class ArchivoJugadoresException extends Exception
{
    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye la excepcián con una causa del error
     *
     * @param causa El mensaje que describe el problema que se presentá
     */
    public ArchivoJugadoresException( String causa )
    {
        super( causa );
    }

}
