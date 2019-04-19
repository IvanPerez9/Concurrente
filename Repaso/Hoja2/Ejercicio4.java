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
public class Ejercicio4 {

	/*
	 * Elementos de valor igual a su indice
	 */
	
	public static int valor (int array, int pos) {
		if (array == pos) {
			return pos;
		}
		return 0;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		int[] array = {6, 1, 5, 7, 4, 8};
		ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		List<Future<Integer>> lista = new ArrayList<>();
		List<Integer> salida = new ArrayList<>();
		
		for (int i = 0; i < array.length; i++) {
			final int id = i;
			Future<Integer> valor = pool.submit(() -> valor(array[id], id));
			lista.add(valor);
		}
		
		for (Future<Integer> f : lista) {
			salida.add(f.get());
		}
		
		for (Integer i : salida) {
			if (i != 0) {
				System.out.print(i + " ");
			}
		}
		
		pool.shutdown();
	}
	
}
