### Enunciado

Se quiere crear una aplicación para organizar un mundial de fútbol. 
La aplicación permite visualizar los equipos que participarán y la
ficha técnica de sus jugadores. Cada equipo tiene un país de origen,
el nombre del director técnico y la ruta de la imagen con la bandera
del país que representa. Para cada jugador perteneciente a un equipo
se conoce el nombre, la edad, la altura, el peso, el salario, la ruta
a la imagen con la foto del jugador y la posición en la que juega: 
delantero, centrocampista, defensa o arquero.
      
Se espera que el programa cuente con la siguiente funcionalidad: (1) 
presentar al usuario la información de un jugador dados el nombre del
equipo al que pertenece y el nombre del jugador, (2) agregar un equipo
al mundial, el cual estará sin jugadores inicialmente, (3) agregar un
jugador a un equipo (dados el nombre del equipo y toda la información
del jugador), (4) calcular el valor de la nómina de un equipo. Este 
valor corresponde a la suma de los salarios de los jugadores del equipo.

El programa debe crear un archivo de texto (en el directorio
“data\reportes”), cuyo nombre debe ser el nombre del equipo seguido 
de un número único generado por el programa con el reporte. Por último, 
(5) el programa debe permitir modificar la información de los jugadores
del mundial usando un archivo de texto; toda la información de un jugador
puede ser modificada exceptuando su nombre y el equipo al que pertenece. 

La información del mundial debe ser persistente y el proceso debe ser 
completamente transparente para el usuario. Esto quiere decir que el
programa debe ser capaz de guardar la información en un archivo cada 
vez que el usuario termina la ejecución del mismo y de utilizar dicha 
información cuando el usuario vuelve a ejecutarlo para reconstruir el
estado del modelo del mundo. El programa no debe preguntarle al usuario
el nombre del archivo, sino que lo tiene que manejar todo internamente. 
      
En caso de cualquier error en la ejecución del programa, éste debe 
presentar una caja de diálogo con un mensaje claro que explique la razón
del problema. La siguiente es una lista de los errores que se pueden 
presentar y el tipo de acción que debe realizar el programa: 