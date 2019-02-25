/**
 * 
 */
package Tema4;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @date 25 feb. 2019
 * 
 * @author Iván - IvanPerez9
 *
 */
public class SincronizacionBarreraJavaEjercicio {

	private static final int NPROC = 4;
	private static CyclicBarrier barrier;
	private static Lock lock = new ReentrantLock(); // Es region critica tanto los tabuladores como la A
	
	// Por lo que si no pones en exclusion mutua el tabulador, se puede tabular 3 veces, y solo imprimir una A 
	
	// Barrera sin lambdas
	private static CyclicBarrier barrera = new CyclicBarrier(NPROC, new Runnable() {
		@Override
		public void run() {
			System.out.println("*");
		}
	});
			
	/*
	 * Tabular despues de cada hilo, empezando lo mas pegado a la izq e ir pasando de 1 en 1 a la derecha.
	 * El ultimo hilo escribe un * pero lo más pegado a la izq posible
	 */
	
	public static void proceso (int id) {
		try{
			while (true) {
				lock.lock();
				for (int i = 0; i < id; i++) {
					System.out.print("\t");
				}
				System.out.println("A");
				lock.unlock();
				Thread.sleep(1000);
				barrier.await(); // Esperar a que el siguiente pase 
			}
		}catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		barrier = new CyclicBarrier(4, () -> System.out.println("*")); // lambda
		for (int i = 0; i < NPROC; i++) {
			int id = i+1;
			new Thread (() -> proceso(id)).start();
		}
	}
	
	
}
