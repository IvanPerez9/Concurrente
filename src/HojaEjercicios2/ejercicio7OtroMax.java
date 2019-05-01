/**
 * 
 */
package HojaEjercicios2;

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
public class ejercicio7OtroMax {

	/*
	 * Lo mismo sin ForkJoin, le paso al pool de thread los tramos que quiero devidir.
	 * Array de m elementos y N hilos, dividir el array en N partes y pasarle 1 a cada hilo
	 */
	
	private static final int SIZE = 12;
	private static final int HILOS = 3;
	
	public static Integer maximoArray (int[] array) {
		int max = 0;
		max = Arrays.stream(array)
			.max().getAsInt();
		return max;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		int []array = {1,2,3,4,8,9,20,4,6,7,20,14};
		
		ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		List<Future<Integer>> lista = new ArrayList<>();
		List<Integer> salida = new ArrayList<>();
		
		int dividir = SIZE / HILOS ;
		
		for (int i = 0; i < array.length; i++) {
			int[] aux = Arrays.copyOfRange(array, i, i + dividir);
			Future<Integer> num = pool.submit(() -> maximoArray(aux));
			lista.add(num);
		}
		
		for (Future<Integer> f : lista) {
			salida.add(f.get());
		}
		
		int maximo = Collections.max(salida);
		
		System.out.println(maximo);
		pool.shutdown();
		
	}
	
}
