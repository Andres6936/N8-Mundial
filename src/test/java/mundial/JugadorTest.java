/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: JugadorTest.java,v 1.6 2006/12/01 22:29:23 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingenieráa de Sistemas y Computacián
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_mundial
 * Autor: Milena Vela - 28-ago-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package mundial.test;

import mundial.mundo.Jugador;
import junit.framework.TestCase;

/**
 * Esta es la clase que sirve para verificar la clase Jugador del mundo.
 */

public class JugadorTest extends TestCase
{
    // -----------------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------------

    /**
     * Es el jugador sobre el que se realizan las pruebas
     */
    private Jugador jugador;

    // -----------------------------------------------------------------
    // Mátodos
    // -----------------------------------------------------------------

    /**
     * Construye un jugador para hacer las pruebas
     */
    private void setupEscenario1( )
    {
        jugador = new Jugador("Pelá", 55, "Delantero", 1.8, 70.0, 20000.0, "data/imagenes/pele.jpg");
    }

    /**
     * Verifica los mátodos que retornan los datos del jugador. <br>
     * <b> Mátodos a probar: </b> <br>
     * darNombre, darEdad, darPosicion, darAltura, darPeso, darSalario, darImagen. <br>
     * <b> Objetivo: </b> Probar que los mátodos que retornan datos del jugador retornan la informacián correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al consultar el nombre del jugador, debe corresponder al nombre definido en el escenario. <br>
     * 2. Al consultar la edad del jugador, debe corresponder a la edad definida en el escenario.. <br>
     * 3. Al consultar la posicián del jugador, debe corresponder a la posicián definida en el escenario. <br>
     * 4. Al consultar la altura del jugador, debe corresponder a la altura definida en el escenario. <br>
     * 5. Al consultar el peso del jugador, debe corresponder al peso definido en el escenario. <br>
     * 6. Al consultar el salario del jugador, debe corresponder al salario definido en el escenario. <br>
     * 7. Al consultar la ruta a la imagen del jugador, debe corresponder a la ruta definida en el escenario.
     */
    public void testDatos( )
    {
        setupEscenario1();

        assertEquals("El nombre del jugador está mal", "Pelá", jugador.darNombre());
        assertEquals("La edad del jugador está mal", 55, jugador.darEdad());
        assertEquals("La posicián del jugador está mal", "Delantero", jugador.darPosicion());
        assertEquals("La altura del jugador está mal", 1.8, jugador.darAltura());
        assertEquals("El peso del jugador está mal", 70.0, jugador.darPeso());
        assertEquals("El salario del jugador está mal", 20000.0, jugador.darSalario());
        assertEquals("La ruta a la imagen del jugador está mal", "data/imagenes/pele.jpg", jugador.darImagen());
    }

    /**
     * Verifica los mátodos que modifican los datos del jugador. <br>
     * <b> Mátodos a probar: </b> <br>
     * cambiarEdad, cambiarPosicion, cambiarAltura, cambiarPeso, cambiarSalario, cambiarImagen. <br>
     * <b> Objetivo: </b> Probar que los mátodos que retornan datos del jugador retornan la informacián correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al modificar la edad del jugador y consultarla, su valor debe corresponder a la áltima edad definida. <br>
     * 3. Al modificar la posicián del jugador y consultarla, su valor debe corresponder a la áltima posicián definida. <br>
     * 4. Al modificar la altura del jugador y consultarla, su valor debe corresponder a la áltima altura definida. <br>
     * 5. Al modificar el peso del jugador y consultarlo, su valor debe corresponder al áltimo peso definido. <br>
     * 6. Al modificar el salario del jugador y consultarlo, su valor debe corresponder al áltimo salario definido. <br>
     * 7. Al modificar la ruta a la imagen del jugador y consultarla, su valor debe corresponder a la áltima ruta definida.
     */
    public void testModificarDatos( )
    {
        setupEscenario1( );

        jugador.cambiarEdad(60);
        assertEquals("La edad del jugador está mal", 60, jugador.darEdad());
        jugador.cambiarPosicion("Defensa");
        assertEquals("La posicián del jugador está mal", "Defensa", jugador.darPosicion());
        jugador.cambiarAltura(1.5);
        assertEquals("La altura del jugador está mal", "1.5", Double.toString(jugador.darAltura()));
        jugador.cambiarPeso(80);
        assertEquals("El peso del jugador está mal", "80.0", Double.toString(jugador.darPeso()));
        jugador.cambiarSalario(1000);
        assertEquals("El salario del jugador está mal", "1000.0", Double.toString(jugador.darSalario()));
        jugador.cambiarImagen("imagen");
        assertEquals("La ruta a la imagen del jugador está mal", "imagen", jugador.darImagen());
    }

}
