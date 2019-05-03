/**
 * 
 */
package Hoja2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
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
public class Ejercicio8 {

	/* 
	 * Encontrar palabras en un fichero
	 */
	
	public static int Ocurrencias (BufferedReader file , String word) throws IOException {
		int contador = 0;
		String linea;
		while ((linea = file.readLine()) != null) {
			String[] palabras = linea.trim().split(" ");
			for (String s : palabras) {
				if (s.equals(word)) {
					contador++;
				}
			}
		}
		return contador;
	}
	
	public static void main(String[] args) throws FileNotFoundException, InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newCachedThreadPool();
		String path = "Resources/big_file.txt";
		List<Future<Integer>> lista = new ArrayList<>();
		BufferedReader file = new BufferedReader(new FileReader(path));
		String[] arrayStrings = {"Hola" , "The" , "all" , "el",  "resto" , "no" , "salen" , "son" , "en" , "español" }; 
		int contador = 0;
		
		for (int i = 0; i < arrayStrings.length; i++) {
			final int id = i;
			Future<Integer> valor = pool.submit(() -> Ocurrencias(file, arrayStrings[id]));
			lista.add(valor);
		}
		
		for (Future<Integer> i : lista) {
			System.out.println(arrayStrings[contador] + " -> " + i.get());
			contador++;
		}
		pool.shutdown();
	}
	
}
