/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: MundialTest.java,v 1.8 2006/12/01 22:29:23 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingenieráa de Sistemas y Computacián
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_mundial
 * Autor: Juan Camilo Cortás - 07-jun-2006
 * Autor: Daniel Romero - 30-nov-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.mundial.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import junit.framework.TestCase;

import uniandes.cupi2.mundial.mundo.ArchivoJugadoresException;
import uniandes.cupi2.mundial.mundo.ElementoExisteException;
import uniandes.cupi2.mundial.mundo.Equipo;
import uniandes.cupi2.mundial.mundo.Jugador;
import uniandes.cupi2.mundial.mundo.Mundial;
import uniandes.cupi2.mundial.mundo.PersistenciaException;

/**
 * Esta es la clase que sirve para verificar la clase Mundial.
 */

public class MundialTest extends TestCase
{

    // ---------------------------------------------------------------------
    // Atributos
    // ---------------------------------------------------------------------

    /**
     * Es el mundial sobre el que se realizan las pruebas
     */
    private Mundial mundial1;

    /**
     * Es el mundial sobre el que se realizan las pruebas para saber si se salvá bien un archivo
     */
    private Mundial mundial2;

    // -----------------------------------------------------------------
    // Mátodos
    // -----------------------------------------------------------------

    /**
     * Construye un mundial vacáo
     */
    private void setupEscenario1( )
    {
        try
        {
            File archivo = new File( "./test/data/mundial.dat" );
            if( archivo.exists( ) )
            {
                archivo.delete( );
            }

            mundial1 = new Mundial( "./test/data/mundial.dat" );
        }
        catch( Exception e )
        {
            fail("No deberáa haber problemas cargando el archivo:" + e.getMessage());
        }
    }

    /**
     * Construye un mundial con tres equipos (equipo1, equipo2 y equipo3), cada uno con 3 jugadores
     */
    private void setupEscenario2( )
    {
        try
        {
            File archivo = new File( "./test/data/mundial.dat" );
            if( archivo.exists( ) )
            {
                archivo.delete( );
            }

            mundial1 = new Mundial( "./test/data/mundial.dat" );
            mundial1.agregarEquipo( "equipo1", "director1", "imagen1" );
            mundial1.agregarJugadorAEquipo( "equipo1", "jugador1", 1, "pos1", 1, 1, 1, "j_imagen1" );
            mundial1.agregarJugadorAEquipo( "equipo1", "jugador2", 2, "pos2", 2, 2, 2, "j_imagen2" );
            mundial1.agregarJugadorAEquipo( "equipo1", "jugador3", 3, "pos3", 3, 3, 3, "j_imagen3" );

            mundial1.agregarEquipo( "equipo2", "director2", "imagen2" );
            mundial1.agregarJugadorAEquipo( "equipo2", "jugador1", 1, "pos1", 1, 1, 1, "j_imagen1" );
            mundial1.agregarJugadorAEquipo( "equipo2", "jugador2", 2, "pos2", 2, 2, 2, "j_imagen2" );
            mundial1.agregarJugadorAEquipo( "equipo2", "jugador3", 3, "pos3", 3, 3, 3, "j_imagen3" );

            mundial1.agregarEquipo( "equipo3", "director3", "imagen3" );
            mundial1.agregarJugadorAEquipo( "equipo3", "jugador1", 1, "pos1", 1, 1, 1, "j_imagen1" );
            mundial1.agregarJugadorAEquipo( "equipo3", "jugador2", 2, "pos2", 2, 2, 2, "j_imagen2" );
            mundial1.agregarJugadorAEquipo( "equipo3", "jugador3", 3, "pos3", 3, 3, 3, "j_imagen3" );

        }
        catch( Exception e )
        {
            fail("No deberáa haber problemas cargando el archivo:" + e.getMessage());
        }
    }

    /**
     * Verifica el mátodo de creacián de la clase Mundial.<br>
     * Se espera que el mundial está vacáo. <br>
     * <b> Mátodos a probar: </b> <br>
     * Mundial (constructor), darEquipos. <br>
     * <b> Objetivo: </b> Probar que el mátodo Mundial() sea capaz de crear un mundial vacáo (sin equipos). <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear un mundial este debe quedar sin equipos. <br>
     */
    public void testMundial( )
    {
        setupEscenario1( );

        ArrayList equipos = mundial1.darNombresEquipos( );
        assertEquals("El námero de equipos es incorrecto", 0, equipos.size());
    }

    /**
     * Verifica el mátodo que retorna un equipo del mundial.<br>
     * Se verifica lo que sucede cuando se busca un equipo que existe en el mundial. <br>
     * <b> Mátodos a probar: </b> <br>
     * darEquipo. <br>
     * <b> Objetivo: </b> Probar que el mátodo darEquipo() sea capaz de encontrar un equipo existente. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar un equipo existente en el mundial, este debe ser retornado.
     */
    public void testDarEquipoOk( )
    {
        setupEscenario2( );

        Equipo equipo = mundial1.darEquipo( "equipo1" );
        assertNotNull("No se encontrá el equipo", equipo);
        assertEquals("El nombre del equipo retornado no es el esperado", "equipo1", equipo.darPais());
    }

    /**
     * Verifica el mátodo que retorna un equipo del mundial.<br>
     * Se verifica lo que sucede cuando se busca un equipo que NO existe en el mundial. <br>
     * <b> Mátodos a probar: </b> <br>
     * darEquipo. <br>
     * <b> Objetivo: </b> Probar que el mátodo darEquipo() no encuentre equipo que no existe en el mundial. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar un equipo que no existe en el mundial se debe obtener null.
     */
    public void testDarEquipoError( )
    {
        setupEscenario2( );

        Equipo equipo = mundial1.darEquipo( "equipo4" );
        assertNull("Se encontrá un equipo que no deberáa existir en el mundial", equipo);
    }

    /**
     * Verifica el mátodo que agrega un equipo al mundial para el caso en el que no hay error.<br>
     * En este caso los datos del equipo que se agrega son correctos. <br>
     * <b> Mátodos a probar: </b> <br>
     * agregarEquipo. <br>
     * <b> Objetivo: </b> Probar que el mátodo agregarEquipo() sea capaz de agregar un equipo en el mundial. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al agregar un equipo cuyo nombre no pertenece a otro equipo existente en el mundial, este debe ser adicionado. <br>
     * 2. Al buscar un equipo previamente agregado este debe ser encontrado.
     */
    public void testAgregarEquipoOk( )
    {
        setupEscenario2( );

        try
        {
            // Se agrega el equipo
            mundial1.agregarEquipo( "Mi equipo de prueba", "directorPrueba", "./data/imagenes/prueba.jpg" );
            assertEquals( "El equipo no fue agregado correctamente a el mundial", 4, mundial1.darNombresEquipos( ).size( ) );

            // Se verifica que el equipo haya quedado agregado correctamente
            Equipo equipo = mundial1.darEquipo( "Mi equipo de prueba" );
            assertNotNull("No se encontrá el equipo", equipo);
            assertEquals("El nombre del equipo retornado no es el esperado", "Mi equipo de prueba", equipo.darPais());
        }
        catch( ElementoExisteException e )
        {
            fail("El equipo deberáa haberse agregado correctamente, sin generar una excepcián: " + e.getMessage());
        }
    }

    /**
     * Verifica el mátodo que agrega un equipo al mundial para el caso en el que hay un error.<br>
     * En este caso se intenta agregar un equipo con un nombre repetido. <br>
     * <b> Mátodos a probar: </b> <br>
     * agregarEquipo. <br>
     * <b> Objetivo: </b> Probar que el mátodo agregarEquipo() arroje excepcián cuando se intente registrar un equipo con un nombre correspondiente a otro equipo existente en
     * el mundial<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al tratar de agregar un equipo cuyo nombre pertenece a otro equipo existente en el mundial se debe arrojar excepcián. <br>
     * 2. Al tratar de agregar un equipo cuyo nombre pertenece a otro equipo existente en el mundial no deben cambiar el námero de equipos del mundial.
     */
    public void testAgregarEquipoError( )
    {
        setupEscenario2( );

        int numeroEquipos = mundial1.darNombresEquipos( ).size( );
        try
        {
            // Se agrega el equipo y se espera que esto genere una excepcián
            mundial1.agregarEquipo("equipo1", "director1", "./data/imagenes/prueba.jpg");
            fail("El equipo no deberáa haberse agregado porque ya hay otro equipo con el mismo nombre");
        }
        catch( ElementoExisteException e )
        {
            // Verificar que no haya cambiado el mundial
            int numeroEquipos2 = mundial1.darNombresEquipos( ).size( );
            assertEquals("Cambiá el námero de equipos en el mundial", numeroEquipos, numeroEquipos2);
        }
    }

    /**
     * Verifica el mátodo que agrega un jugador a un equipo del mundial.<br>
     * Este caso verifica que se pueda agregar un jugador si los datos son correctos. <br>
     * <b> Mátodos a probar: </b> <br>
     * agregarJugadorAEquipo. <br>
     * <b> Objetivo: </b> Probar que el mátodo agregarJugadorAEquipo() sea capaz de agregar un jugador al equipo. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al agregar un jugador al equipo cuyo nombre no corresponde a otro jugador este debe ser adicionado.<br>
     * 2. Al buscar un jugador de un equipo previamente agregado, esta debe ser encontrado.
     */
    public void testAgregarJugadorAEquipoOk( )
    {
        setupEscenario2( );

        try
        {
            mundial1.agregarJugadorAEquipo( "equipo1", "jugador4", 4, "pos4", 4, 4, 4, "j_imagen4" );

            Equipo equipo = mundial1.darEquipo( "equipo1" );
            assertNotNull("No se encontrá el equipo", equipo);
            assertEquals("El jugador no fue agregado al equipo", 4, equipo.darNombresJugadores().size());

            Jugador j = equipo.darJugador( "jugador4" );
            assertNotNull("No se encontrá el jugador", j);
            assertEquals("El jugador no fue agregado correctamente al equipo", "jugador4", j.darNombre());
        }
        catch( ElementoExisteException e )
        {
            fail("El jugador deberáa haberse agregado correctamente: " + e.getMessage());
        }
    }

    /**
     * Verifica el mátodo que agrega un jugador a un equipo de el mundial.<br>
     * Este caso verifica que no se pueda agregar un jugador si el nombre del jugador está repetido. <br>
     * <b> Mátodos a probar: </b> <br>
     * agregarJugadorAEquipo. <br>
     * <b> Objetivo: </b> Probar que el mátodo agregarJugadorAEquipo() arroje excepcián al agregar un jugador al equipo cuyo nombre está repetido. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al tratar de agregar un jugador al equipo cuyo nombre está repetido, se debe arrojar excepcián.<br>
     * 2. Al tratar de agregar un jugador al equipo cuyo nombre está repetido, el námero de jugadores no debe cambiar.
     */
    public void testAgregarJugadorAEquipoError( )
    {
        setupEscenario2( );

        Equipo equipo = mundial1.darEquipo( "equipo1" );
        int numJugadores = equipo.darNombresJugadores( ).size( );

        try
        {
            mundial1.agregarJugadorAEquipo("equipo1", "jugador2", 1, "pos1", 20, 78.10, 2, "j_imagen1");
            fail("El jugador no deberáa haberse agregado porque el nombre está repetido y se deberáa generar una excepcián");
        }
        catch( ElementoExisteException e )
        {
            // Verificar que no haya cambiado el equipo
            int numJugadores2 = equipo.darNombresJugadores( ).size( );
            assertEquals("Cambiá el námero de jugadores en el equipo", numJugadores, numJugadores2);
        }
    }

    /**
     * Verifica el mátodo salvarMundial.<br>
     * La prueba se realiza creando un nuevo mundial, agregando algunos equipos y salvando la informacián.<br>
     * A continuacián se carga esta informacián en un nuevo mundial y se comparan ambos para verificar que la informacián se haya salvado correctamente. <br>
     * <b> Mátodos a probar: </b> <br>
     * salvarMundial, Mundial (constructor). <br>
     * <b> Objetivo: </b> Probar que el mátodo salvarMundial() sea capaz de guardar la informacián del mundial correctamente. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al salvar un mundial y crear otro mundial a partir del archivo en el que se salvo la primera, ambos mundiales deben <br>
     * ser iguales (tener la misma informacián).
     */
    public void testSalvarMundial( )
    {
        // Generar un námero aleatorio para el nombre del archivo
        Date fecha = new Date( );
        long tiempo = fecha.getTime( );
        String archivo = "./test/data/mundial" + tiempo + ".dat";

        // Crear el mundial que va a usar el archivo
        try
        {
            mundial1 = new Mundial( archivo );
        }
        catch( Exception e )
        {
            fail( "no se pudo cargar el archivo de prueba" );
        }

        // Meter informacián aleatoria en el mundial
        generarInformacion( mundial1 );

        // Salvar la informacián de el mundial
        try
        {
            mundial1.salvarMundial( );

            // Construir un nuevo mundial con los mismos datos
            mundial2 = new Mundial( archivo );

            // Comparar los dos mundiales
            compararMundiales( mundial1, mundial2 );

            // Eliminar el archivo de prueba temporal
            File archivoPrueba2 = new File( archivo );
            archivoPrueba2.delete( );
        }
        catch( PersistenciaException e )
        {
            fail("No se debiá arrojar excepcián");
        }
    }

    /**
     * Verifica el mátodo calcularValorNomina. <br>
     * Se espera calcular el valor de la námina de un equipo y que se genere un reporte con el formato correcto. <br>
     * <b> Mátodos a probar: </b> <br>
     * calcularValorNomina. <br>
     * <b> Objetivo: </b> Probar que el mátodo calcularValorNomina() sea capaz de generar el reporte. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al calcular el valor de la námina de un equipo, el valor debe corresponder a la suma de los salarios de todos los jugadores del equipo<br>
     * 2. Al calcular el valor de la námina de un equipo se debe generar un reporte con el formato e informacián correctos.
     */
    public void testCalcularValorNomina( )
    {
        setupEscenario2( );

        Equipo equipo = mundial1.darEquipo( "equipo2" );

        try
        {
            String nombreArchivoReporte = mundial1.calcularValorNomina( equipo.darPais( ), "./test/data/reporte/" );
            assertNotNull( "El nombre del archivo no debe ser null", nombreArchivoReporte );

            File archivoReporte = new File( "./test/data/reporte/" + nombreArchivoReporte );
            assertTrue( "El archivo debe existir", archivoReporte.exists( ) );

            // Revisar el contenido del archivo
            BufferedReader br = new BufferedReader(new FileReader(archivoReporte));

            // Tátulo
            String titulo = br.readLine();
            assertNotNull("La lánea no es la esperada", titulo);

            // Fecha
            String fecha = br.readLine();
            assertNotNull("La segunda lánea debe tener la fecha", fecha);
            assertTrue("La lánea no tiene el formato esperado", fecha.startsWith("Fecha:"));
            Date fechaHoy = new Date();
            String strFecha = fechaHoy.toString().substring(0, 10);
            assertTrue("La fecha de la factura no es la fecha de hoy", fecha.indexOf(strFecha) != -1);

            // Total námina
            String total = br.readLine();
            assertNotNull("La tercera lánea debe tener el valor total", total);
            assertTrue("La lánea no tiene el formato esperado - " + total, total.startsWith("Total Námina:"));
            assertTrue("El valor total no es el esperado", total.indexOf("6.0") != -1);

        }
        catch( IOException e )
        {
            fail("No deberáa producirse esta excepcián: " + e.getMessage());
        }
    }

    /**
     * Verifica el mátodo modificarInformacionJugadores<br>
     * Se espera que la informacián de los jugadores especificada en el archivo sea cambiada. <br>
     * <b> Mátodos a probar: </b> <br>
     * modificarInformacionJugadores. <br>
     * <b> Objetivo: </b> Probar que el mátodo modificarInformacionJugadores() sea capaz de modificar <br>
     * correctamente la informacián de los jugadores especifica en el archivo <b> Resultados esperados: </b> <br>
     * 1. Al modificar la informacián de los jugadores, al consultarla esta deberáa tener los nuevos valores.
     */
    public void testModificarInformacionJugadoresOK( )
    {
        setupEscenario2( );
        File archivoMundial = new File( "./test/data/jugadores1.txt" );
        try {
            mundial1.modificarInformacionJugadores(archivoMundial);
            Equipo equipo = mundial1.darEquipo("equipo1");
            Jugador jugador = equipo.darJugador("jugador2");

            assertEquals("La informacián del jugador no se modifico correctamente", "jugador2", jugador.darNombre());
            assertEquals("La informacián del jugador no se modifico correctamente", 4, jugador.darEdad());
            assertEquals("La informacián del jugador no se modifico correctamente", "pos4", jugador.darPosicion());
            assertEquals("La informacián del jugador no se modifico correctamente", "1.44", Double.toString(jugador.darAltura()));
            assertEquals("La informacián del jugador no se modifico correctamente", "44.0", Double.toString(jugador.darPeso()));
            assertEquals("La informacián del jugador no se modifico correctamente", "44000.4", Double.toString(jugador.darSalario()));
            assertEquals("La informacián del jugador no se modifico correctamente", "imagen4", jugador.darImagen());

            equipo = mundial1.darEquipo("equipo2");
            jugador = equipo.darJugador("jugador3");

            assertEquals("La informacián del jugador no se modifico correctamente", "jugador3", jugador.darNombre());
            assertEquals("La informacián del jugador no se modifico correctamente", 6, jugador.darEdad());
            assertEquals("La informacián del jugador no se modifico correctamente", "pos6", jugador.darPosicion());
            assertEquals("La informacián del jugador no se modifico correctamente", "1.66", Double.toString(jugador.darAltura()));
            assertEquals("La informacián del jugador no se modifico correctamente", "66.0", Double.toString(jugador.darPeso()));
            assertEquals("La informacián del jugador no se modifico correctamente", "66000.6", Double.toString(jugador.darSalario()));
            assertEquals("La informacián del jugador no se modifico correctamente", "imagen6", jugador.darImagen());

            equipo = mundial1.darEquipo("equipo3");
            jugador = equipo.darJugador("jugador1");

            assertEquals("La informacián del jugador no se modifico correctamente", "jugador1", jugador.darNombre());
            assertEquals("La informacián del jugador no se modifico correctamente", 2, jugador.darEdad());
            assertEquals("La informacián del jugador no se modifico correctamente", "pos2", jugador.darPosicion());
            assertEquals("La informacián del jugador no se modifico correctamente", "1.22", Double.toString(jugador.darAltura()));
            assertEquals("La informacián del jugador no se modifico correctamente", "22.0", Double.toString(jugador.darPeso()));
            assertEquals("La informacián del jugador no se modifico correctamente", "22000.2", Double.toString(jugador.darSalario()));
            assertEquals("La informacián del jugador no se modifico correctamente", "imagen2", jugador.darImagen());
        }
        catch( IOException e )
        {
            fail("No deberáa producirse esta excepcián: " + e.getMessage());
        }
        catch( ArchivoJugadoresException e )
        {
            fail("No deberáa producirse esta excepcián: " + e.getMessage());
        }
    }

    /**
     * Verifica que el mátodo modificarInformacionJugadores haga las verificaciones correctas para modificar la informacián de los jugadores <br>
     * El archivo utilizado tiene un formato incorrecto. <br>
     * <b> Mátodos a probar: </b> <br>
     * modificarInformacionJugadores. <br>
     * <b> Objetivo: </b> Probar que el mátodo modificarInformacionJugadores() genera la excepcián apropiada cuando el formato del archivo es incorrecto<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al intentar modificar la informacián de los jugadores a partir de un archivo con formato inválido, se debe lanzar excepcián.<br>
     * 2. La informacián de los jugadores no debe ser modificada
     */
    public void testModificarInformacionJugadoresError1( )
    {
        setupEscenario2( );
        File archivoMundial = new File( "./test/data/jugadores2.txt" );
        try
        {
            mundial1.modificarInformacionJugadores( archivoMundial );

        }
        catch( IOException e )
        {
            fail("No deberáa producirse esta excepcián: " + e.getMessage());
        }
        catch( ArchivoJugadoresException e )
        {
            // Se verifica que la informacián de los jugadores no se haya cambiado
            ArrayList equipos = mundial1.darNombresEquipos( );
            for( int i = 0; i < equipos.size( ); i++ )
            {
                String nombreEquipo = ( String )equipos.get( i );
                Equipo equipo = ( Equipo )mundial1.darEquipo( nombreEquipo );
                ArrayList jugadores = equipo.darNombresJugadores( );

                for( int j = 0; j < jugadores.size( ); j++ ) {
                    String nombre = (String)jugadores.get(j);

                    Jugador jugador = equipo.darJugador(nombre);

                    assertEquals("La informacián del jugador no se modifico correctamente", j + 1, jugador.darEdad());
                    assertEquals("La informacián del jugador no se modifico correctamente", "pos" + (j + 1), jugador.darPosicion());
                    assertEquals("La informacián del jugador no se modifico correctamente", (j + 1), jugador.darAltura(), 0);
                    assertEquals("La informacián del jugador no se modifico correctamente", (j + 1), jugador.darPeso(), 0);
                    assertEquals("La informacián del jugador no se modifico correctamente", (j + 1), jugador.darSalario(), 0);
                    assertEquals("La informacián del jugador no se modifico correctamente", "j_imagen" + (j + 1), jugador.darImagen());

                }
            }
        }
    }

    /**
     * Verifica que el mátodo modificarInformacionJugadores haga las verificaciones correctas para modificar la informacián de los jugadores <br>
     * El archivo utilizado tiene un formato incorrecto. <br>
     * <b> Mátodos a probar: </b> <br>
     * modificarInformacionJugadores. <br>
     * <b> Objetivo: </b> Probar que el mátodo modificarInformacionJugadores() genera la excepcián apropiada cuando el formato del archivo es incorrecto<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al intentar modificar la informacián de los jugadores a partir de un archivo con formato inválido, se debe lanzar excepcián.<br>
     * 2. Sálo se debe modificar informacián de los jugadores que se encuentra hasta la lánea que se pudo leer.
     */
    public void testModificarInformacionJugadoresError2( )
    {
        setupEscenario2( );
        File archivoMundial = new File( "./test/data/jugadores3.txt" );
        try
        {
            mundial1.modificarInformacionJugadores( archivoMundial );

        }
        catch( IOException e )
        {
            fail("No deberáa producirse esta excepcián: " + e.getMessage());
        }
        catch( ArchivoJugadoresException e ) {
            // Se debiá modificar la informacián de tres jugadores
            Equipo equipo = mundial1.darEquipo("equipo1");
            Jugador jugador = equipo.darJugador("jugador2");

            assertEquals("La informacián del jugador no se modifico correctamente", "jugador2", jugador.darNombre());
            assertEquals("La informacián del jugador no se modifico correctamente", 4, jugador.darEdad());
            assertEquals("La informacián del jugador no se modifico correctamente", "pos4", jugador.darPosicion());
            assertEquals("La informacián del jugador no se modifico correctamente", 1.44, jugador.darAltura(), 0);
            assertEquals("La informacián del jugador no se modifico correctamente", 44.0, jugador.darPeso(), 0);
            assertEquals("La informacián del jugador no se modifico correctamente", 44000.4, jugador.darSalario(), 0);
            assertEquals("La informacián del jugador no se modifico correctamente", "imagen4", jugador.darImagen());

            equipo = mundial1.darEquipo("equipo2");
            jugador = equipo.darJugador("jugador3");

            assertEquals("La informacián del jugador no se modifico correctamente", "jugador3", jugador.darNombre());
            assertEquals("La informacián del jugador no se modifico correctamente", 6, jugador.darEdad());
            assertEquals("La informacián del jugador no se modifico correctamente", "pos6", jugador.darPosicion());
            assertEquals("La informacián del jugador no se modifico correctamente", 1.66, jugador.darAltura(), 0);
            assertEquals("La informacián del jugador no se modifico correctamente", 66.0, jugador.darPeso(), 0);
            assertEquals("La informacián del jugador no se modifico correctamente", 66000.6, jugador.darSalario(), 0);
            assertEquals("La informacián del jugador no se modifico correctamente", "imagen6", jugador.darImagen());

            equipo = mundial1.darEquipo("equipo3");
            jugador = equipo.darJugador("jugador1");

            assertEquals("La informacián del jugador no se modifico correctamente", "jugador1", jugador.darNombre());
            assertEquals("La informacián del jugador no se modifico correctamente", 2, jugador.darEdad());
            assertEquals("La informacián del jugador no se modifico correctamente", "pos2", jugador.darPosicion());
            assertEquals("La informacián del jugador no se modifico correctamente", 1.22, jugador.darAltura(), 0);
            assertEquals("La informacián del jugador no se modifico correctamente", 22.0, jugador.darPeso(), 0);
            assertEquals("La informacián del jugador no se modifico correctamente", 22000.2, jugador.darSalario(), 0);
            assertEquals("La informacián del jugador no se modifico correctamente", "imagen2", jugador.darImagen());

            // La informacián de los otros jugadores debe ser la misma
            equipo = mundial1.darEquipo("equipo1");
            jugador = equipo.darJugador("jugador1");

            assertEquals("La informacián del jugador no se debiá modificar", "jugador1", jugador.darNombre());
            assertEquals("La informacián del jugador no se debiá modificar", 1, jugador.darEdad());
            assertEquals("La informacián del jugador no se debiá modificar", "pos1", jugador.darPosicion());
            assertEquals("La informacián del jugador no se debiá modificar", 1.0, jugador.darAltura(), 0);
            assertEquals("La informacián del jugador no se debiá modificar", 1.0, jugador.darPeso(), 0);
            assertEquals("La informacián del jugador no se debiá modificar", 1.0, jugador.darSalario(), 0);
            assertEquals("La informacián del jugador no se debiá modificar", "j_imagen1", jugador.darImagen());

            jugador = equipo.darJugador("jugador3");

            assertEquals("La informacián del jugador no se debiá modificar", "jugador3", jugador.darNombre());
            assertEquals("La informacián del jugador no se debiá modificar", 3, jugador.darEdad());
            assertEquals("La informacián del jugador no se debiá modificar", "pos3", jugador.darPosicion());
            assertEquals("La informacián del jugador no se debiá modificar", 3.0, jugador.darAltura(), 0);
            assertEquals("La informacián del jugador no se debiá modificar", 3.0, jugador.darPeso(), 0);
            assertEquals("La informacián del jugador no se debiá modificar", 3.0, jugador.darSalario(), 0);
            assertEquals("La informacián del jugador no se debiá modificar", "j_imagen3", jugador.darImagen());

            equipo = mundial1.darEquipo("equipo2");
            jugador = equipo.darJugador("jugador1");

            assertEquals("La informacián del jugador no se debiá modificar", "jugador1", jugador.darNombre());
            assertEquals("La informacián del jugador no se debiá modificar", 1, jugador.darEdad());
            assertEquals("La informacián del jugador no se debiá modificar", "pos1", jugador.darPosicion());
            assertEquals("La informacián del jugador no se debiá modificar", 1.0, jugador.darAltura(), 0);
            assertEquals("La informacián del jugador no se debiá modificar", 1.0, jugador.darPeso(), 0);
            assertEquals("La informacián del jugador no se debiá modificar", 1.0, jugador.darSalario(), 0);
            assertEquals("La informacián del jugador no se debiá modificar", "j_imagen1", jugador.darImagen());

            jugador = equipo.darJugador("jugador2");

            assertEquals("La informacián del jugador no se debiá modificar", "jugador2", jugador.darNombre());
            assertEquals("La informacián del jugador no se debiá modificar", 2, jugador.darEdad());
            assertEquals("La informacián del jugador no se debiá modificar", "pos2", jugador.darPosicion());
            assertEquals("La informacián del jugador no se debiá modificar", 2.0, jugador.darAltura(), 0);
            assertEquals("La informacián del jugador no se debiá modificar", 2.0, jugador.darPeso(), 0);
            assertEquals("La informacián del jugador no se debiá modificar", 2.0, jugador.darSalario(), 0);
            assertEquals("La informacián del jugador no se debiá modificar", "j_imagen2", jugador.darImagen());

            equipo = mundial1.darEquipo("equipo3");
            jugador = equipo.darJugador("jugador2");

            assertEquals("La informacián del jugador no se debiá modificar", "jugador2", jugador.darNombre());
            assertEquals("La informacián del jugador no se debiá modificar", 2, jugador.darEdad());
            assertEquals("La informacián del jugador no se debiá modificar", "pos2", jugador.darPosicion());
            assertEquals("La informacián del jugador no se debiá modificar", 2.0, jugador.darAltura(), 0);
            assertEquals("La informacián del jugador no se debiá modificar", 2.0, jugador.darPeso(), 0);
            assertEquals("La informacián del jugador no se debiá modificar", 2.0, jugador.darSalario(), 0);
            assertEquals("La informacián del jugador no se debiá modificar", "j_imagen2", jugador.darImagen());

            jugador = equipo.darJugador("jugador3");

            assertEquals("La informacián del jugador no se debiá modificar", "jugador3", jugador.darNombre());
            assertEquals("La informacián del jugador no se debiá modificar", 3, jugador.darEdad());
            assertEquals("La informacián del jugador no se debiá modificar", "pos3", jugador.darPosicion());
            assertEquals("La informacián del jugador no se debiá modificar", 3.0, jugador.darAltura());
            assertEquals("La informacián del jugador no se debiá modificar", 3.0, jugador.darPeso());
            assertEquals("La informacián del jugador no se debiá modificar", 3.0, jugador.darSalario());
            assertEquals("La informacián del jugador no se debiá modificar", "j_imagen3", jugador.darImagen());
        }
    }

    /**
     * Verifica que el mátodo modificarInformacionJugadores haga las verificaciones correctas para modificar la informacián de los jugadores <br>
     * El archivo utilizado tiene un formato incorrecto. <br>
     * <b> Mátodos a probar: </b> <br>
     * modificarInformacionJugadores. <br>
     * <b> Objetivo: </b> Probar que el mátodo modificarInformacionJugadores() genera la excepcián apropiada cuando el formato del archivo es incorrecto<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al intentar modificar la informacián de los jugadores a partir de un archivo con formato inválido, se debe lanzar excepcián.<br>
     * 2. El equipo del primer jugador en el archivo no existe. Se debe arrojar excepcián y la informacián de los jugadores no debe ser modificada.
     */
    public void testModificarInformacionJugadoresError3( )
    {
        setupEscenario2( );
        File archivoMundial = new File( "./test/data/jugadores4.txt" );
        try
        {
            mundial1.modificarInformacionJugadores( archivoMundial );

        }
        catch( IOException e )
        {
            fail("No deberáa producirse esta excepcián: " + e.getMessage());
        }
        catch( ArchivoJugadoresException e )
        {
            // Se verifica que la informacián de los jugadores no se haya cambiado
            ArrayList equipos = mundial1.darNombresEquipos( );
            for( int i = 0; i < equipos.size( ); i++ )
            {
                String nombreEquipo = ( String )equipos.get( i );
                Equipo equipo = mundial1.darEquipo( nombreEquipo );
                ArrayList jugadores = equipo.darNombresJugadores( );

                for( int j = 0; j < jugadores.size( ); j++ ) {
                    String nombre = (String)jugadores.get(j);

                    Jugador jugador = equipo.darJugador(nombre);

                    assertEquals("La informacián del jugador no se modifico correctamente", j + 1, jugador.darEdad());
                    assertEquals("La informacián del jugador no se modifico correctamente", "pos" + (j + 1), jugador.darPosicion());
                    assertEquals("La informacián del jugador no se modifico correctamente", (j + 1), jugador.darAltura(), 0);
                    assertEquals("La informacián del jugador no se modifico correctamente", (j + 1), jugador.darPeso(), 0);
                    assertEquals("La informacián del jugador no se modifico correctamente", (j + 1), jugador.darSalario(), 0);
                    assertEquals("La informacián del jugador no se modifico correctamente", "j_imagen" + (j + 1), jugador.darImagen());

                }
            }
        }
    }

    /**
     * Verifica que el mátodo modificarInformacionJugadores haga las verificaciones correctas para modificar la informacián de los jugadores <br>
     * El archivo utilizado tiene un formato incorrecto. <br>
     * <b> Mátodos a probar: </b> <br>
     * modificarInformacionJugadores. <br>
     * <b> Objetivo: </b> Probar que el mátodo modificarInformacionJugadores() genera la excepcián apropiada cuando el formato del archivo es incorrecto<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al intentar modificar la informacián de los jugadores a partir de un archivo con formato inválido, se debe lanzar excepcián.<br>
     * 2. El primer jugador en el archivo no existe. Se debe arrojar excepcián y la informacián de los jugadores no debe ser modificada.
     */
    public void testModificarInformacionJugadoresError4( )
    {
        setupEscenario2( );
        File archivoMundial = new File( "./test/data/jugadores5.txt" );
        try
        {
            mundial1.modificarInformacionJugadores( archivoMundial );

        }
        catch( IOException e )
        {
            fail("No deberáa producirse esta excepcián: " + e.getMessage());
        }
        catch( ArchivoJugadoresException e )
        {
            // Se verifica que la informacián de los jugadores no se haya cambiado
            ArrayList equipos = mundial1.darNombresEquipos( );
            for( int i = 0; i < equipos.size( ); i++ )
            {
                String nombreEquipo = ( String )equipos.get( i );
                Equipo equipo = mundial1.darEquipo( nombreEquipo );
                ArrayList jugadores = equipo.darNombresJugadores( );

                for( int j = 0; j < jugadores.size( ); j++ ) {
                    String nombre = (String)jugadores.get(j);

                    Jugador jugador = equipo.darJugador(nombre);

                    assertEquals("La informacián del jugador no se modifico correctamente", j + 1, jugador.darEdad());
                    assertEquals("La informacián del jugador no se modifico correctamente", "pos" + (j + 1), jugador.darPosicion());
                    assertEquals("La informacián del jugador no se modifico correctamente", j + 1, jugador.darAltura(), 0);
                    assertEquals("La informacián del jugador no se modifico correctamente", j + 1, jugador.darPeso(), 0);
                    assertEquals("La informacián del jugador no se modifico correctamente", j + 1, jugador.darSalario(), 0);
                    assertEquals("La informacián del jugador no se modifico correctamente", "j_imagen" + (j + 1), jugador.darImagen());

                }
            }
        }
    }

    // -----------------------------------------------------------------
    // Mátodos Auxiliares
    // -----------------------------------------------------------------

    /**
     * Este mátodo se encarga de verificar que dos mundiales sean iguales.<br>
     * El mátodo revisa que los mundiales tengan los mismos equipos y que cada equipo sea igual jugador por jugador al que hay en el otro mundial.<br>
     * No existe ninguna condicián que indique que los mundiales deben tener los equipos ordenados de la misma manera.
     * @param mundial1 El primer mundial para comparar
     * @param mundial2 El segundo mundial para comparar
     */
    private void compararMundiales( Mundial mundial1, Mundial mundial2 )
    {
        // Comparar que haya el mismo námero de equipos
        ArrayList equipos1 = mundial1.darNombresEquipos( );
        ArrayList equipos2 = mundial2.darNombresEquipos( );
        assertEquals("El námero de equipos es diferente", equipos1.size(), equipos2.size());

        for( int i = 0; i < equipos1.size( ); i++ )
        {
            // Verificar que los mismos equipos están en los dos mundiales
            String nombre_equipo = ( String )equipos1.get( i );
            Equipo e1 = mundial1.darEquipo( nombre_equipo );
            Equipo e2 = mundial2.darEquipo( nombre_equipo );
            assertNotNull("El segundo mundial no contenáa al equipo " + nombre_equipo, e2);

            // Comparar que haya el mismo námero de jugadores en los dos equipos
            ArrayList jugadores_e1 = e1.darNombresJugadores( );
            ArrayList jugadores_e2 = e2.darNombresJugadores( );
            assertEquals("El námero de jugadores es diferente", jugadores_e1.size(), jugadores_e2.size());
            for( int j = 0; j < jugadores_e1.size( ); j++ ) {
                // Verificar que los mismos jugadores están en los dos equipos
                String nombre_jugador = (String)jugadores_e1.get(j);
                Jugador j1 = e1.darJugador(nombre_jugador);
                Jugador j2 = e2.darJugador(nombre_jugador);
                assertNotNull("El equipo " + nombre_equipo + "del segundo mundial no contenáa el jugador " + nombre_jugador, e2);

                assertEquals("Los atributos de las dos jugadores no son idánticos", j1.darAltura(), j2.darAltura(), 0);
                assertEquals("Los atributos de las dos jugadores no son idánticos", j1.darPeso(), j2.darPeso(), 0);
                assertEquals("Los atributos de las dos jugadores no son idánticos", j1.darNombre(), j2.darNombre());
                assertEquals("Los atributos de las dos jugadores no son idánticos", j1.darSalario(), j2.darSalario(), 0);
                assertEquals("Los atributos de las dos jugadores no son idánticos", j1.darEdad(), j2.darEdad());
                assertEquals("Los atributos de las dos jugadores no son idánticos", j1.darPosicion(), j2.darPosicion());
                assertEquals("Los atributos de las dos jugadores no son idánticos", j1.darImagen(), j2.darImagen());
            }
        }
    }

    /**
     * Este mátodo genera informacián aleatoria para ingresar en el mundial
     * @param mundial El mundial donde se va a ingresar la informacián
     */
    private void generarInformacion( Mundial mundial )
    {
        int numeroEquipos = ( int ) ( Math.random( ) * 10 );
        int numeroJugadores = ( int ) ( Math.random( ) * 20 );

        try
        {
            for( int i = 0; i < numeroEquipos; i++ )
            {
                String nombreEquipo = "equipo_" + i;
                mundial.agregarEquipo( nombreEquipo, "random", "random.jpg" );

                for( int j = 0; j < numeroJugadores; j++ )
                {
                    String nombreJugador = "jugador_" + j;
                    mundial.agregarJugadorAEquipo( nombreEquipo, nombreJugador, 1, "pos2", 3, 4, 5, "imagen" );
                }
            }
        }
        catch( ElementoExisteException e )
        {
            fail("Deberáa haberse podido agregar el equipo o el jugador: " + e.getMessage());
        }
    }
}
