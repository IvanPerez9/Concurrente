/**
 * 
 */
package Tema5;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class ejercicioFactorialPool {

	/*
	 * En este caso irá obteniendo los resultados segun vaya obteniendolos
	 */
	
	public static BigInteger factorial (int num) {
		BigInteger f = BigInteger.ONE; // Al ser bigInteger usar metodos propios
		while(num > 0) {
			f = f.multiply(BigInteger.valueOf(num));
			num--;
		}
		return f;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		int max = 1000000;
		// Cambiarlo por un concurrent hashmap de integer y future big integer
		ConcurrentHashMap<Integer, Future<BigInteger>> resultsM = new ConcurrentHashMap<>();
		for (int i = 0; i < max; i++) {
			int n = i;
			resultsM.add(pool.submit(() -> factorial(n)));
		}
		
		// Aqui recorrer con getValues y el keySet()
		while(!resultsM.isEmpty()) {
			for (Entry<Integer, Future<BigInteger>> entry : resultsM.entrySet()) {
				Future<BigInteger> results = entry.getValue();
			}
			for (Future<BigInteger> future : resultsM) {
				if (future.isDone()) {
					System.out.println(future.get());
					resultsM.remove(future);
				}
			}
		}
		pool.shutdown();
	}
	
}
