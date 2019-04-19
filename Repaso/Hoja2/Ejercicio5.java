/**
 * 
 */
package Hoja2;

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
	
	public static int primosRango (int ini , int end) {
		int contador = 0;
		for (int i = ini; i < end; i++) {
			if (esPrimo(i)) {
				contador++;
			}
		}
		return contador;
	}
	
	public static boolean esPrimo(int n) {
	    for(int i=2;i<n;i++) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newCachedThreadPool();
		int ini = 10;
		int end = 500;
		
		Future<Integer> contador = pool.submit(() -> primosRango(ini, end));
		
		System.out.println("Desde " + ini + " hasta " + end + " hay " + contador.get() + " numeros primos.");
		pool.shutdown();
	}
}
