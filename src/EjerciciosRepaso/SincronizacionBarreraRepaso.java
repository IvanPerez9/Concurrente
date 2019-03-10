/**
 * 
 */
package EjerciciosRepaso;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class SincronizacionBarreraRepaso {

	/*
	 * Se indica el numero de hilos del proceso y el metodo Runnable que ejecutara cuando todos los hilos lleguen a la barrera con await()
	 * 
	 * 
	 * Implementar un programa concurrente donde NPROC hilos escriban una A y se queden 
	 * bloqueados. El último hilo escribirá (además de la A) un *
	 * 
	 */
	
	private static final int NPROC = 5;
	private static CyclicBarrier barrera = new CyclicBarrier(NPROC);
	private static Lock lock = new ReentrantLock(); // Necesario exclusion mutua al pintar la A
	
	public static void ejecucion (int id) {
		try {
			while(true) {
				lock.lock();
				System.out.println("A");
				lock.unlock();
				Thread.sleep(1000);
				barrera.await();
			}
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		barrera = new CyclicBarrier(NPROC, () -> System.out.println("*")); // Cuando termine con el numero de Thread printa lo otro
		for (int i = 0; i < NPROC; i++) {
			int id = i+1;
			new Thread (() -> ejecucion(id) ).start();
		}
	}
}
