/**
 * 
 */
package RepasoT6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Optional;
import java.util.Random;

import com.sun.org.apache.xml.internal.serialize.LineSeparator;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class EjercicioTeoria {

	/*
	 * Crear un fichero con n líneas y m números (float) separados por un espacio 
	 *  n y m deben ser números razonablemente grandes. 
	 *  Utilizar los streams de Java para procesar dicho fichero. 
	 *  
	 *  Se debe calcular el promedio de cada línea.
	 *  Finalmente se devuelve el máximo de todos los promedios
	 */
	
	private final static int M = 1000;
	private final static int N = 5000;
	
	public static void crearFichero (int m, int n) {
		try {
			PrintWriter escribirFichero = new PrintWriter("Resources/EjercicioT6.txt") ;
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					escribirFichero.print(new Random().nextInt(N )+ " ");
				}
				escribirFichero.println(" ");
			}
			escribirFichero.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		crearFichero(M, N);
		
		// Leer fichero
		try(BufferedReader leer = new BufferedReader(new FileReader("Resources/EjercicioT6.txt"))) {
			Optional<Float> num = leer.lines().parallel()
					.map(line -> {
						float avg = 0;
						for (String s : line.trim().split(" ")) {
							avg += Float.parseFloat(s);
						}
						return avg/M;
					})
					.reduce(Float::max);
			
			System.out.println(" MAX: " + num.get());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
}
