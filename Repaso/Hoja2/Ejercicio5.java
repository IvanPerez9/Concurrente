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
public class Ejercicio5 {

	/*
	 * Primos en un rango
	 */
	
	public static boolean esPrimo(int num) {
		for (int i = 2; i < num; i++) {
			if(num % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newCachedThreadPool();
		List<Future<Boolean>> lista = new ArrayList<>();
		int contador = 0 ;
		boolean valoraux = false;
		
		int[] array = {1,2,3,4,5,6,7,20,11,34,6456,23,6};
		
		for (int i = 0; i < array.length; i++) {
			final int id = i;
			Future<Boolean> valor = pool.submit(() -> esPrimo(array[id]));
			lista.add(valor);
		}
		
		for (Future<Boolean> f : lista) {
			valoraux = f.get();
			if (valoraux == true) {
				contador++;
			}
		}
		
		System.out.println(contador);
		pool.shutdown();
	}
	
}
