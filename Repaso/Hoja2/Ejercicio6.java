/**
 * 
 */
package Hoja2;

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
public class Ejercicio6 {

	/*
	 * Contar longitud de cadenas salvo las vacias
	 */
	
	public static int contarCadenas (String cadena) {
		int contador = 0;
		for (int i = 0; i < cadena.length(); i++) {
			contador ++;
		}
		return contador;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		List<Future<Integer>> lista = new ArrayList<>();
		String[] arrayPrueba = {"", "", "cse", "rox", "", "homework", "", "7", ""};
		
		for (int i = 0; i < arrayPrueba.length; i++) {
			String palabra = arrayPrueba[i];
			Future<Integer> valor = pool.submit(() -> contarCadenas(palabra));
			lista.add(valor);
		}
		
		for (Future<Integer> k : lista) {
			int valor = k.get();
			if (valor != 0) {
				System.out.print(valor + " ");
			}
		}
		
		pool.shutdown();
	}
	
}
