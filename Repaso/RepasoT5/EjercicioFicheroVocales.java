/**
 * 
 */
package RepasoT5;

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
public class EjercicioFicheroVocales {

	public static int contarVocales (String word) {
		int contador = 0;
		word.toLowerCase();
		for (int i = 0; i < word.length(); i++) {
			char vocal = word.charAt(i);
			if ((vocal == 'a') || (vocal == 'e') || (vocal == 'i') ||  (vocal == 'o') || (vocal == 'u') ) {
				contador ++;
			}
		}
		return contador;
	}
	
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newCachedThreadPool();
		String path = "Resources/File.txt" ;
		BufferedReader lector = new BufferedReader(new FileReader(path));
		
		int contador = 0 ;
		String linea ;
		List<Future<Integer>> lista = new ArrayList<>();
		
		while((linea = lector.readLine()) != null) {
			String[] words = linea.trim().split(" ");
			for (String s : words) {
				Future<Integer> valor = pool.submit(() -> contarVocales(s));
				lista.add(valor);
			}
		}
		
		for (Future<Integer> f : lista) {
			contador += f.get();
		}
		
		System.out.println(contador);
		pool.shutdown();
		lector.close();
	}
	
}
