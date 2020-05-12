package mundial.mundo;

/**
 * Esta excepcián se lanza cuando se presenta un error al leer o escribir el archivo con la informacián del estado del modelo del mundo. <br>
 * El mensaje asociado con la excepcián debe describir el problema que se presentá.
 */
public class PersistenciaException extends Exception
{
    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye la excepcián con el mensaje que se pasa como parámetro y que describe la causa del problema
     *
     * @param causa el mensaje que describe el problema
     */
    public PersistenciaException( String causa )
    {
        super( causa );
    }
}
