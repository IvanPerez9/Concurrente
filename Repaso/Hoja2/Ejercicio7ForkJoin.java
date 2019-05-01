/**
 * 
 */
package Hoja2;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
@SuppressWarnings("serial")
public class Ejercicio7ForkJoin extends RecursiveTask<Integer>  {

	/*
	 * Implemente un programa concurrente de hilos que sea capaz de encontrar el 
	 * valor máximo de un array de elementos generados aleatoriamente. Para 
	 * solventar el problema el array de m elementos será dividido de manera 
	 * equitativa en un conjunto de sub-arrays que se asignarán a cada hilo
	 */
	
	private static final int NHILOS = 3;
	private static final int SIZE = 12;
	
	private int start ;
	private int end ;
	private int[] array ;
	
	public Ejercicio7ForkJoin(int start, int end, int[] array) {
		super();
		this.start = start;
		this.end = end;
		this.array = array;
	}

	public static int[] generateRandomArray (int size) {
		int[] aux = new int[SIZE];
		Random rdn = new Random();
		for (int i = 0; i < aux.length; i++) {
			aux[i] = rdn.nextInt(size);
		}
		return aux;
	}

	@Override
	protected Integer compute() {
		if ((end-start) < NHILOS) {
			int maximo = Integer.MIN_VALUE;
			for (int i = 0; i < array.length; i++) {
				maximo = Math.max(maximo, array[i]);
			}
			return maximo;
		} else {
			int mitad = (end+start) / 2;
			
			Ejercicio7ForkJoin izq = new Ejercicio7ForkJoin(start, mitad, array);
			Ejercicio7ForkJoin der = new Ejercicio7ForkJoin(mitad + 1, end, array);
			
			izq.fork();
			int n = der.compute();
			int m = izq.join();
			
			int salida = Math.max(n, m);
			return salida;
		}
	}
	
	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool();
		int[] array = generateRandomArray(SIZE);
		int num = pool.invoke(new Ejercicio7ForkJoin(0, array.length, array)) ;
		
		System.out.println(Arrays.toString(array));
		
		System.out.println("Maximo: " + num);
		pool.shutdown();
	}
	
}
