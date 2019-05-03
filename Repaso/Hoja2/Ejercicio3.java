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
		int contador = 0 ;
		for (int i = 0; i < palabra.length(); i++) {
			palabra.toLowerCase();
			if ((palabra.charAt(i) == 'a') || (palabra.charAt(i) == 'e') || (palabra.charAt(i) == 'i') || (palabra.charAt(i) == 'o') || (palabra.charAt(i) == 'u') ) {
				contador++;
			}
		}
		return contador;
	}
	
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newCachedThreadPool();
		String path = "Resources/big_file.txt" ;
		BufferedReader file = new BufferedReader(new FileReader(path));
		List<Future<Integer>> lista = new ArrayList<>();
		int contador = 0;
		String linea;
		
		while ((linea = file.readLine()) != null) {
			String[] palabras = linea.trim().split(" ");
			for (String s : palabras) {
				Future<Integer> result = pool.submit(() -> contarVocales(s));
				lista.add(result);
			}
		}
		
		for (Future<Integer> f : lista) {
			contador += f.get();
		}
		
		pool.shutdown();
		
		System.out.println("Total de vocales: " + contador);
	}
	
}
