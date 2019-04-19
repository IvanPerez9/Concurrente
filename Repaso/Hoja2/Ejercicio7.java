/**
 * 
 */
package Hoja2;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import HojaEjercicios2.ejercicio7Maximo;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Ejercicio7 extends RecursiveTask<Integer> {

	/*
	 * Fork join -> encontar el maximo
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int ELEM = 5;
	private static final int SIZE = 10;
	private int start ;
	private int end ;
	private int[] array ;
	
	public Ejercicio7(int start, int end, int[] array) {
		super();
		this.start = start;
		this.end = end;
		this.array = array;
	}

	public static int[] generateRandomArray (int size) {
		int[] aux = new int[size];
		Random rdn = new Random();
		for (int i = 0; i < aux.length; i++) {
			aux[i] = rdn.nextInt(size);
		}
		return aux;
	}

	@Override
	protected Integer compute() {
		if ((end - start) < ELEM) {
			int max = Integer.MIN_VALUE; 
			for (int i = 0; i < array.length; i++) {
				max = Math.max(max, array[i]);
			}
			return max;
		} else {
			int mitad = (end+start) / 2;
			
			Ejercicio7 izq = new Ejercicio7(start, mitad, array);
			Ejercicio7 der = new Ejercicio7(mitad, end, array);
			
			izq.fork();
			int aux = der.compute();
			
			int salida = izq.join();
			
			int max = Math.max(aux, salida);
			return max;
		}
	}
	
	public static void main(String[] args) {
		int[] arrayMain = generateRandomArray(SIZE);
		ForkJoinPool pool = new ForkJoinPool();
		int m = pool.invoke(new ejercicio7Maximo(0, arrayMain.length, arrayMain));
		
		System.out.println(Arrays.toString(arrayMain));
		System.out.println("Max: " + m);
		
		pool.shutdown();
	}
	
}
