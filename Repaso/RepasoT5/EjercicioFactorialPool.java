/**
 * 
 */
package RepasoT5;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class EjercicioFactorialPool {

	/*
	 * Implementa un programa que calcule el factorial de los primeros 1.000.000 enteros de manera concurrente
	 */
	
	public static BigInteger factorial (int num) {
		BigInteger fac = BigInteger.ONE;
		while(num > 0) {
			fac = fac.multiply(BigInteger.valueOf(num));
			num--;
		}
		return fac;
	}
	
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		ConcurrentHashMap<Integer, Future<BigInteger>> results = new ConcurrentHashMap<>();
		
		for (int i = 0; i < 1000000; i++) {
			final int id = i;
			Future<BigInteger> numero = pool.submit(() -> factorial(id));
			results.put(id, numero);
		}
		
		while (!results.isEmpty()) {
			for (Map.Entry<Integer, Future<BigInteger>> aux : results) {
				System.out.println(aux.getKey() + "facotrial -> " + aux.getValue().get() );
			}
		}
	}
	
}
