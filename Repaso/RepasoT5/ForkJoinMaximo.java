/**
 * 
 */
package RepasoT5;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
@SuppressWarnings("serial")
public class ForkJoinMaximo extends RecursiveTask<Integer> {

	/*
	 * Realizar el ejercicio anterior de encontrar el maximo de un array por DyV , pero con forkJoin
	 * Caso base y caso general
	 * 
	 * Al ser una tarea que devuelve un valor, usaremos RecursiveTask -> Nos obliga a compute
	 * Tambien implementar el constructor
	 *  - Necesario para compute, y para llamar al metodo invoke en el main del pool
	 */
	
	private int start;
	private int end;
	private int[] array;
	
	public ForkJoinMaximo(int start, int end, int[] array) {
		this.start = start;
		this.end = end;
		this.array = array;
	}

	@Override
	protected Integer compute() {
		Integer maximo = Integer.MIN_VALUE;
		// Caso base
		if (end - start <= 2) {
			for (int i = 0; i < array.length; i++) {
				maximo = Math.max(maximo, array[i]);
			}
			return maximo;
		} else {
			// Caso recursivo -> dividir
			
			int mitad = (end+start) / 2;
			ForkJoinMaximo izq = new ForkJoinMaximo(start, mitad, array);
			ForkJoinMaximo der = new ForkJoinMaximo(mitad +1, end, array);
			
			izq.fork();
			int m = der.compute();
			int n = izq.join();
			
			return Math.max(m, n) ;
		}
	}
	
	public static void main(String[] args) {
		int[] array = {1,2,63,7,8,9,2,3,5,60,44,23,1,23,61};
		ForkJoinPool pool = new ForkJoinPool();
		
		int result = pool.invoke(new ForkJoinMaximo(0, array.length, array)) ;
		System.out.println(result);
		pool.shutdown();
	}
}
