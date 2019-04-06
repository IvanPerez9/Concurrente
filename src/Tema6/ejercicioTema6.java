/**
 * 
 */
package Tema6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.Random;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class ejercicioTema6 {
	
	private final static int M = 1000;
	private final static int N = 5000;

	public static void main(String[] args) throws IOException {
		String path = "Resources/File2.txt" ;
		Long start = System.currentTimeMillis();
		crearFichero(M, N, path);
		
		try (BufferedReader entrada = new BufferedReader(new FileReader(path))) {
			Optional<Float> maxVal = entrada.lines() // Reduce devuelve un optional
					.parallel() // Sin esto no es paralelo
					.map(line -> {
						float avg = 0;
						for (String stNum : line.trim().split(" ")) {
							avg += Float.parseFloat(stNum);
						}
						return avg/M;
					})
					.reduce(Math::max);
			System.out.println("MAX: " + maxVal.get());
			Long end = System.currentTimeMillis();
			System.out.println("Tiempo: " + (end-start));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void crearFichero (int m , int n, String path) throws IOException {
		PrintWriter escribir = new PrintWriter(path);
		Random rdn = new Random(M);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				escribir.print((rdn.nextInt(M)*100 + " "));
			}
			escribir.println();
			System.out.println("ROW: " + i);
		}
		escribir.close();
	}
	
}
