package mundial.mundo;

/**
 * Esta excepcián se lanza cuando se intenta agregar al mundial un elemento repetido. <br>
 * El mensaje asociado con la excepcián debe indicar el equipo o el jugador que causá el error.
 */
public class ElementoExisteException extends Exception
{
    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye la excepcián indicando el equipo o el jugador que causá el error
     *
     * @param nomElem nombre del elemento repetido
     */
    public ElementoExisteException( String nomElem )
    {
        super( nomElem );
    }
}
