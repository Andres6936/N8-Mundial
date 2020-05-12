/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: EquipoTest.java,v 1.7 2007/04/12 13:38:06 carl-veg Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingenieráa de Sistemas y Computacián
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_mundial
 * Autor: Milena Vela - 28-ago-2006
 * Autor: Daniel Romero - 30-nov-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.mundial.test;

import java.util.ArrayList;

import uniandes.cupi2.mundial.mundo.ElementoExisteException;
import uniandes.cupi2.mundial.mundo.Equipo;
import uniandes.cupi2.mundial.mundo.Jugador;
import junit.framework.TestCase;

/**
 * Esta es la clase que sirve para verificar la clase Equipo del mundo.
 */
public class EquipoTest extends TestCase
{
    // -----------------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------------

    /**
     * Es el equipo sobre el que se realizan las pruebas
     */
    private Equipo equipo;

    // -----------------------------------------------------------------------
    // Mátodos
    // -----------------------------------------------------------------------

    /**
     * Construye un equipo sin jugadores
     */
    private void setupEscenario1( )
    {
        equipo = new Equipo( "Colombia", "Maturana", "prueba.jpg" );
    }

    /**
     * Construye un equipo con algunos jugadores
     */
    private void setupEscenario2( )
    {
        equipo = new Equipo( "Colombia", "Maturana", "prueba.jpg" );

        Jugador j1 = new Jugador( "J1", 20, "Arquero", 1.80, 80.0, 10000.0, "j1.jpg" );
        Jugador j2 = new Jugador( "J2", 30, "Defensa", 1.50, 70.5, 5000.5, "j2.jpg" );
        Jugador j3 = new Jugador( "J3", 40, "Delantero", 1.70, 74.2, 15000.5, "j3.jpg" );

        try
        {
            equipo.agregarJugador( j1 );
            equipo.agregarJugador( j2 );
            equipo.agregarJugador( j3 );
        }
        catch( ElementoExisteException e )
        {
            e.printStackTrace( );
        }
    }

    /**
     * Prueba que los jugadores se agreguen correctamente. <br>
     * <b> Mátodos a probar: </b> <br>
     * agregarJugador. <br>
     * <b> Objetivo: </b> Probar que el mátodo agregarjugador() es capaz de agregar jugadores de forma correcta al mundial. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al agregar un jugador que no existe en un equipo, este debe ser adicionado al equipo.
     */
    public void testAgregarJugadorOK( )
    {
        setupEscenario1( );

        Jugador j1 = new Jugador( "nombre1", 20, "Arquero", 1.80, 80.0, 10000.0, "nombre1.jpg" );
        int numJugadores = equipo.darNombresJugadores( ).size( );
        try {
            equipo.agregarJugador(j1);
            assertNotNull("El jugador no se agregá correctamente", equipo.darJugador(j1.darNombre()));
            assertEquals("El jugador no se agregá correctamente", numJugadores + 1, equipo.darNombresJugadores().size());
        }
        catch( ElementoExisteException e )
        {
            fail("No se debiá arrojar excepcián");
        }
    }

    /**
     * Prueba que los jugadores se agreguen correctamente. <br>
     * <b> Mátodos a probar: </b> <br>
     * agregarJugador. <br>
     * <b> Objetivo: </b> Probar que el mátodo agregarjugador() arroja excepcián cuando se trata de agregar un jugador con <br>
     * un nombre repetido. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al agregar un jugador que existe en un equipo se debe arrojar una excepcián indicándolo.
     */
    public void testAgregarJugadorError( )
    {
        setupEscenario1( );

        Jugador j1 = new Jugador( "nombre1", 20, "Arquero", 1.80, 80.0, 10000.0, "nombre1.jpg" );
        Jugador j2 = new Jugador( "nombre1", 30, "Defensa", 1.70, 90.0, 11000.0, "nombre2.jpg" );
        int numJugadores = equipo.darNombresJugadores( ).size( );
        try
        {
            equipo.agregarJugador( j1 );
        }
        catch( ElementoExisteException e )
        {
            fail("No se debiá arrojar excepcián");
        }
        try
        {
            equipo.agregarJugador(j2);
            fail("Se debiá arrojar excepcián");
        }
        catch( ElementoExisteException e )
        {
            assertEquals("El námero de jugadores no debiá cambiar", numJugadores + 1, equipo.darNombresJugadores().size());
        }
    }

    /**
     * Verifica los mátodos que retornan datos del equipo. <br>
     * <b> Mátodos a probar: </b> <br>
     * darPais, darDirector, darImagen. <br>
     * <b> Objetivo: </b> Probar que los mátodos que dan informacián de un equipo retornan la informacián correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al consultar el nombre del equipo, debe corresponder al nombre definido en el escenario. <br>
     * 2. Al consultar el director del equipo, debe corresponder al director definido en el escenario. <br>
     * 3. Al consultar la ruta a la imagen del equipo, debe corresponder a la ruta definida en el escenario. <br>
     */
    public void testDatos( )
    {
        setupEscenario1();

        assertEquals("El nombre del equipo está mal", "Colombia", equipo.darPais());
        assertEquals("El director del equipo está mal", "Maturana", equipo.darDirector());
        assertEquals("La imagen del equipo está mal", "prueba.jpg", equipo.darImagen());

    }

    /**
     * Verifica el mátodo darJugador.<br>
     * Se busca un jugador que está dentro del equipo. <br>
     * <b> Mátodos a probar: </b> <br>
     * darJugador. <br>
     * <b> Objetivo: </b> Probar que el mátodo darJugador() retorne un jugador que existe en el equipo. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al pedir un jugador que existe en el equipo, áste debe ser retornada.
     */
    public void testDarJugadorOk( )
    {
        setupEscenario2( );

        Jugador j = equipo.darJugador( "J2" );
        assertNotNull("El jugador se deberáa haber encontrado", j);
    }

    /**
     * Verifica el mátodo darJugador.<br>
     * Se busca un jugador que no está dentro del equipo, esperando que retorne null. <br>
     * <b> Mátodos a probar: </b> <br>
     * darJugador. <br>
     * <b> Objetivo: </b> Probar que el mátodo darJugador() no retorne un jugador que no existe en el equipo. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al pedir un jugador que no existe en el equipo se debe obtener null.
     */
    public void testDarJugadorError( )
    {
        setupEscenario2( );

        Jugador j = equipo.darJugador( "J5" );
        assertNull("El jugador NO se deberáa haber encontrado", j);
    }

    /**
     * Verifica el mátodo darNombresJugadores, que deberáa retornar los nombres de los 3 jugadores que están en el equipo. Este mátodo se verifica usando el escenario 2. <br>
     * <b> Mátodos a probar: </b> <br>
     * darNombresJugadores. <br>
     * <b> Objetivo: </b> Probar que el mátodo darNombresJugadores() retornen los nombres de todos los jugadores que hay en el equipo. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al pedir el arreglo con los nombres de los jugadores de un equipo, dicho arreglo debe contener todos los nombres de los jugadores existentes.
     */
    public void testDarNombresJugadores( )
    {
        setupEscenario2( );

        ArrayList nombres = equipo.darNombresJugadores( );
        assertEquals("El námero de nombres es incorrecto", 3, nombres.size());

        String nombre1 = ( String )nombres.get( 0 );
        String nombre2 = ( String )nombres.get( 1 );
        String nombre3 = ( String )nombres.get( 2 );

        assertEquals( "El nombre del jugador no es el esperado", "J1", nombre1 );
        assertEquals( "El nombre del jugador no es el esperado", "J2", nombre2 );
        assertEquals( "El nombre del jugador no es el esperado", "J3", nombre3 );
    }

    /**
     * Verifica el mátodo que calcula el valor de la námina de un equipo. <br>
     * <b> Mátodos a probar: </b> <br>
     * calcularValorNomina. <br>
     * <b> Objetivo: </b> Probar que el mátodo calcularValorNomina() calcula correctamente el valor de la námina del equipo de acuerdo al salario de los jugadores. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al consultar el valor de la námina de un equipo, este valor debe corresponder a la suma de de los salarios de todos sus jugadores.
     */
    public void testCalcularValorNomina( )
    {
        setupEscenario2( );

        assertEquals("El cálculo de la námina del equipo está mal", 30001.0, equipo.calcularValorNomina(), 0);
    }

    /**
     * Verifica el mátodo que modifica la informacián de un jugador. <br>
     * <b> Mátodos a probar: </b> <br>
     * modificarInformacionJugador. <br>
     * <b> Objetivo: </b> Probar que el mátodo modificarInformacionJugador() modifica correctamente la informacián de un jugador existente. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al modificar la informacián de un jugador, al consultarla se deben obtener los nuevos valores.
     */
    public void testModificarInformacionJugador( )
    {
        setupEscenario2();

        equipo.modificarInformacionJugador("J2", 34, "Delantero", 1.99, 38.7, 25000.0, "imagen");

        Jugador jugador = equipo.darJugador("j2");

        assertEquals("La informacián del jugador no se modifico correctamente", "J2", jugador.darNombre());
        assertEquals("La informacián del jugador no se modifico correctamente", 34, jugador.darEdad());
        assertEquals("La informacián del jugador no se modifico correctamente", "Delantero", jugador.darPosicion());
        assertEquals("La informacián del jugador no se modifico correctamente", 1.99, jugador.darAltura(), 0);
        assertEquals("La informacián del jugador no se modifico correctamente", 38.7, jugador.darPeso(), 0);
        assertEquals("La informacián del jugador no se modifico correctamente", 25000.0, jugador.darSalario(), 0);
        assertEquals("La informacián del jugador no se modifico correctamente", "imagen", jugador.darImagen());

    }

}
