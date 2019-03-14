/**
 * 
 */
package Tema5;

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
public class forkJoinDyV extends RecursiveTask<Integer>{

	/*
	 * Realizar el ejercicio anterior de encontrar el maximo de un array por DyV , pero con forkJoin
	 * Caso base y caso general
	 * 
	 * Al ser una tarea que devuelve un valor, usaremos RecursiveTask -> Nos obliga a compute
	 * Tambien implementar el constructor
	 */
	
	private static final int TH_SEQ = 2;
	
	private int[] v;
	private int start;
	private int end;
	
	
	/*
	 * Compute es el que lleva la logica, caso base y caso recursivo
	 */
	@Override
	protected Integer compute() {
		if (end - start <= TH_SEQ) {
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < v.length; i++) {
				max = Math.max(max, v[i]);
			}
			return max;
		} else {
			int mitad = (end+start) / 2;
			forkJoinDyV left = new forkJoinDyV(v, start, mitad);
			forkJoinDyV right = new forkJoinDyV(v, mitad, end);
			
			right.fork();
			int m1 = left.compute();
			
			/* Mejorar de rendimiento llamar a compute con uno de las propias divisiones
			 * Solo right fork, y left compute -> Orden fork, compute, join (Diapo 37)
			 * 
			 * Sino: 
			 * 
			 * left.fork();
			 * right.fork();
			 * int m1 = left.join(); 
			 * int m2 = right.join();
			 * 
			 */
			
			int m2 = right.join();
			
			return Math.max(m1, m2);
		}
	}

	/**
	 * @param v
	 * @param start
	 * @param end
	 */
	public forkJoinDyV(int[] v, int start, int end) {
		this.v = v;
		this.start = start;
		this.end = end;
	}
	
	/**
	 * Generate a random Array
	 * @param size
	 * @return
	 */
	public static int[] generateRandomArray (int size) {
		int[] aux = new int[size];
		Random rdn = new Random();
		for (int i = 0; i < aux.length; i++) {
			aux[i] = rdn.nextInt(size);
		}
		return aux;
	}
	
	/**
	 * Crear el pool de forkJoin y mandarle un task de forkJoin
	 * @param args
	 */
	public static void main(String[] args) {
		int[] v = generateRandomArray(10);
		ForkJoinPool pool = new ForkJoinPool();
		int m = pool.invoke(new forkJoinDyV(v, 0, v.length));
		
		System.out.println(Arrays.toString(v));
		System.out.println("Max: " + m);
		
		pool.shutdown();
	}
	
}
