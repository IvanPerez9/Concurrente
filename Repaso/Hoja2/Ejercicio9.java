/**
 * 
 */
package Hoja2;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Ejercicio9 {

	private static final int TMN = 5;
	
	public static int[] generaterArray (int size) {
		int[] array = new int[size] ;
		Random rdn = new Random();
		for (int i = 0; i < array.length; i++) {
			array[i] = rdn.nextInt(TMN);
		}
		return array;
	}
	
	public static int sequenceCounter (int n, int[] array) {
		int max = 0 ;
		if (array.length <= 1) {
			return array.length;
		}
		for (int i = 0; i < array.length; i++) {
			int aux = 1;
			while ((i+1 < array.length) && (array[i+1] == array[i])) {
				aux++;
				i++;
			}
			if (max < aux) {
				max = aux;
			}
		}
		return max;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newCachedThreadPool();
		Map<Future<Integer>, Integer> map = new HashMap<>();
		int[] arrayMain = {0,3,1,1,2,3,3,3,3,3,4};
		
		for (int i = 0; i < arrayMain.length; i++) {
			final int finalI = i;
			Future<Integer> elem = pool.submit(() -> sequenceCounter(arrayMain[finalI], arrayMain));
			map.put(elem, arrayMain[finalI]);
		}
		
		for (Future<Integer> i : map.keySet()) {
			Integer key = i.get();
			Integer value = map.get(i);
			System.out.println("Numero de sequencia: " + value + " longitud: " + key);
		}
		
		pool.shutdown();
	}
	
}
