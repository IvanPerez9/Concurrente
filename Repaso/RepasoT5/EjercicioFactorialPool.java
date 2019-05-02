/**
 * 
 */
package RepasoT5;

import java.math.BigInteger;
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
 *  
 *  Con conjunto, en SRC con mapa 
 */
public class EjercicioFactorialPool {

	/*
	 * Implementa un programa que calcule el factorial de los primeros 1.000.000 enteros de manera concurrente
	 */
	
	private static final int SIZE = 1000;
	
	public static BigInteger factorial (int num) {
		BigInteger fac = BigInteger.ONE;
		while(num > 0) {
			fac = fac.multiply(BigInteger.valueOf(num));
			num--;
		}
		return fac;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		List<Future<BigInteger>> results = new ArrayList<>();
		
		for (int i = 0; i < SIZE; i++) {
			final int id = i;
			Future<BigInteger> numero = pool.submit(() -> factorial(id));
			results.add(numero);
		}
		
		List<BigInteger> lista = new ArrayList<>();
		
		for (Future<BigInteger> f : results) {
			lista.add(f.get());
		}		
		
		for (int i = 0; i < SIZE; i++) {
			System.out.println("Numero " + i + " -> " + lista.get(i));
		}
		
		pool.shutdown();
	}
	
}
