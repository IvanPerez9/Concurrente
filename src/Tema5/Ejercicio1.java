/**
 * 
 */
package Tema5;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Ejercicio1 {
	
	/*
	 * Comprobar como funcionan los distintos tipos de pools de Threads
	 * 
	 * ExecutorService pool = Executors.newSingleThreadExecutor(); -> Solo 1 Hilo
	 * ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()); -> Tantos threads como procesadores tenga
	 * ExecutorService pool = Executors.newCachedThreadPool(); -> El que menos tarda
	 */

	private static final int SIZE = 1000;
	
	public static void execute(int id) {
		Random rnd = new Random();
		System.out.println("Starting Thread " + id); // Más que thread es "Tarea" 
		List<BigInteger> l = new ArrayList<>();
		for (int i = 0; i < SIZE; i++) {
			l.add(BigInteger.probablePrime(256, rnd));
		}
		BigInteger max = BigInteger.ZERO;
		for (int i = 0; i < SIZE; i++) {
			BigInteger next = l.get(i);
			if (next.compareTo(max) > 0) {
				max = next;
			}
		}
		System.out.println("Max: " + max);
	}
	
	/*
	 * Comprobar como ejecutan las distinttas pools
	 */
	public static void main(String[] args) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		ExecutorService pool = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			int id = i +1;
			pool.execute(() -> execute(id));
		}
		pool.shutdown();
		pool.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
		long endTime = System.currentTimeMillis() - startTime;
		System.out.println("Tiempo " + endTime / 1e3);
	}
	
}
