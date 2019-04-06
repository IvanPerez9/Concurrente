/**
 * 
 */
package Tema5;

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
public class ejercicioFicheroVocalesPool {

	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newCachedThreadPool();
		String path = "Resources/File.txt" ;
		BufferedReader entrada = new BufferedReader(new FileReader(path));
		String line = null;
		List<Future<Integer>> results = new ArrayList<>();
		int contador = 0;
		// Parser
		while((line = entrada.readLine()) != null) {
			String[] words = line.split(" "); //"\\s+"  La expresion regular del espacio
			for (String w : words) {
				Future<Integer> counter = pool.submit(()-> contarVocales(w));
				// en un futuro esto te puede devolver un entero
				//contador += contarVocales(w); Secuencial
				//contador += counter.get(); // Esto se pararía asi todo el rato y al final es sequiencial
				//Pasarlo mejor a un ED como la lista de tareas
				results.add(counter);
			}
		}
		
		// OJO CON DONDE TERMINA EL WHILE
		
		for (Future<Integer> f : results) {
			contador += f.get(); // Sin exclusion mutua, no hace falta
		}
		
		System.out.println(contador);
		entrada.close();
		pool.shutdown();
	}
	
	public static int contarVocales (String word) {
		StringBuilder arrayChar = new StringBuilder();
		arrayChar.append(word);
		int contador = 0;
		for (int i = 0; i < arrayChar.length(); i++) {
			char vocal = arrayChar.charAt(i);
			if ((vocal == 'a') || (vocal == 'e') || (vocal == 'i') || (vocal == 'o') || (vocal == 'u')|| 
				(vocal == 'A') || (vocal == 'E') || (vocal == 'I') || (vocal == 'O') || (vocal == 'U')){
				contador++;
			}
		}
		return contador;
	}
	
}
