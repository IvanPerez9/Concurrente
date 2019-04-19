/**
 * 
 */
package Hoja2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Ejercicio3 {

	public static int contarVocales (String palabra) {
		int contador = 0;
		for (int i = 0; i < palabra.length(); i++) {
			palabra.toLowerCase();
			char vocal = palabra.charAt(i);
			if ((vocal == 'a') || (vocal == 'e') || (vocal == 'i') || (vocal == 'o') || (vocal == 'u')|| 
					(vocal == 'A') || (vocal == 'E') || (vocal == 'I') || (vocal == 'O') || (vocal == 'U')){
					contador++;
				}
		}
		return contador;
	}

	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		String path = "Resources/big_file.txt" ;
		BufferedReader entrada = new BufferedReader(new FileReader(path));
		ExecutorService pool = Executors.newCachedThreadPool();
		String linea;
		List<Future<Integer>> lista = new ArrayList<>();
		int contadorF = 0;
		
		while ((linea = entrada.readLine()) != null) {
			String[] palabra = linea.split(" ");
			for (String pal : palabra) {
				Future<Integer> salida = pool.submit(() -> contarVocales(pal) );
				lista.add(salida);
			}
		}
			
		for (Future<Integer> f : lista) {
			contadorF += f.get();
		}
		
		System.out.println(contadorF);
		pool.shutdown();
		entrada.close();
	}
	
}
