/**
 * 
 */
package Hoja2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
public class Ejercicio7 {

	/*
	 * Array de m elementos y N hilos, dividir el array en N partes y pasarle 1 a cada hilo
	 */
	
	public static final int N = 4;
	
	public static int maximoArray(int[] array) {
		int max = Arrays.stream(array)
				.max().getAsInt();
		return max;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		int[] array = {1,20,3,4,7,9,4,10,45,30,20,1,33,21,5,6,7,4,6,18};
		ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		List<Future<Integer>> lista = new ArrayList<>();
		
		int dividir = array.length / N ;
		
		for (int i = 0; i < array.length; i+=dividir) {
			final int id = i;
			Future<Integer> valor = pool.submit(() -> maximoArray(Arrays.copyOfRange(array, id, id+dividir)));
			lista.add(valor);
		}
		
		List<Integer> lista2 = new ArrayList<>();
		for (Future<Integer> f : lista) {
			lista2.add(f.get());
		}
		
		System.out.println(Collections.max(lista2));
		pool.shutdown();
	}
	
}
