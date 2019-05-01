/**
 * 
 */
package Hoja2;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Ejercicio1 extends RecursiveAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void compute() {
		if(array.length < 2) {
			return;
		}
		int mid = array.length / 2;
		int[] izq = Arrays.copyOfRange(array, 0, mid);
		int[] der = Arrays.copyOfRange(array, mid, array.length);
		
		if (array.length < SIZE) {
			mergeSort(array);
			mergeSort(array);
		} else {
			invokeAll(new Ejercicio1(izq), new Ejercicio1(der));
			merge(array, izq, der);
		}
	}

	/*
	 * Implementa el algoritmo de MergeSort de forma paralela -> pool de Threads, forkJoin
	 */
	
	private static final int SIZE = 10;
	private static int[] array;
	
	@SuppressWarnings("static-access")
	public Ejercicio1(int[] array) {
		this.array = array;
	}
	
	public static int[] generateRandomArray() {
		int[] array = new int[SIZE];
		for (int i = 0; i < array.length; i++) {
			array[i] = new Random().nextInt(10);
		}
		return array;
	}
	
	public static void mergeSort (int[] array) {
		if (array.length < 2) {
			return;
		}
		int mid = array.length / 2;
		int[] izq = Arrays.copyOfRange(array, 0, mid);
		int[] der = Arrays.copyOfRange(array, mid, array.length);
		
		mergeSort(izq);
		mergeSort(der);
		
		merge(array, izq, der);
	}
	
	
	public static void merge (int[] array , int[] izq , int[] der) {
		int i = 0;
		int j = 0;
		int k = 0;
		
		while (i < izq.length && j < der.length) {
			if (izq[i] <= der[j]) {
				array[k++] = izq[i++] ;
			}else {
				array[k++] = der[j++];
			}
		}
		while ( i < izq.length) {
			array[k++] = izq[i++];
		}
		while ( j < der.length) {
			array[k++] = der[j++];
		}
	}
	
	public static void main(String[] args) {
		int[] array = generateRandomArray();
		System.out.println("Arrays: " + Arrays.toString(array));
		
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(new Ejercicio1(array));
		
		System.out.println("Arrays ordenado: " + Arrays.toString(array));
		pool.shutdown();
	}
	
}
