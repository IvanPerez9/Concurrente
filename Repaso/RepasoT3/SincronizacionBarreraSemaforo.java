/**
 * 
 */
package RepasoT3;

import java.util.concurrent.Semaphore;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class SincronizacionBarreraSemaforo {

	/*
	 * Sincronizacion de barrera no es más que una condicional, pero esperan a que todos lleguen a un determinado punto
	 */
	
	/*
	 * Se desea implementar un bucle en el que todos los procesos muestran A y se esperan.
	 * El ultimo proceso muestra * y desbloquea a los demás
	 * Si se necesita usar una única barrera en un bucle, debe tenerse control de los desbloqueos de los procesos 
	 */
	
	private static final int N = 5;
	private static int nProc ;
	
	private static Semaphore desbloqueo;
	private static Semaphore semnProc;
	
	
	public static void proceso () throws InterruptedException {
		System.out.println("A");
		semnProc.acquire();
		nProc++;
		if (nProc < N) {
			semnProc.release();
			desbloqueo.acquire();
		} else {
			System.out.println("*");
			nProc = 0;
			for (int i = 0; i < N -1; i++) {
				desbloqueo.release();
			}
		}
	}
	
	public static void main(String[] args) {
		desbloqueo = new Semaphore(0); // Sincronizacion condicional -> Barrera
		semnProc = new Semaphore(1); // Exclusion mutua
		for (int i = 0; i < N; i++) {
			new Thread(() -> {
				try {
					proceso();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}, "Hilo" + (i+1)).start();
 		}
	}
}
