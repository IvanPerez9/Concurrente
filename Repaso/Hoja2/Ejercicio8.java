/**
 * 
 */
package Hoja2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Ejercicio8 {

	/* 
	 * Encontrar palabras en un fichero
	 */
	private static final int SIZE = 10;
	public static final String[] arrayStrings = {"Hola" , "The" , "all" , "el",  "resto" , "no" , "salen" , "son" , "en" , "español" } ;
	
	public static int contarPalabras (String word, BufferedReader file ) throws IOException {
		int contador = 0;
		String linea ;
		while((linea = file.readLine()) != null ) {
			linea.toLowerCase();
			String[] palabras = linea.trim().split(" ");
			for (String s : palabras) {
				if (s.equals(word)) {
					contador++;
				}
			}
		}
		return contador;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
		ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		String path = "Resources/big_file.txt" ;
		BufferedReader entrada = new BufferedReader(new FileReader(path));
		Map<String,Future<Integer>> mapa = new HashMap<>();
		
		for (int i = 0; i < SIZE; i++) {
			final int id = i;
			Future<Integer> valor = pool.submit(() -> contarPalabras(arrayStrings[id], entrada));
			mapa.put(arrayStrings[id], valor);
		}
		
		for (String map : mapa.keySet()) {
			String k = map;
			Integer value = mapa.get(k).get();
			System.out.println("Palabra: "+ "'" + k + "'"+  " está " + value + " veces");
		}
		
		pool.shutdown();
		entrada.close();
	}
	
}
