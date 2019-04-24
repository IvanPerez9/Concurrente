/**
 * 
 */
package Tema3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class SincronizacionBarrera2 {

	/*
	 * Lo mismo que antes, pero que tenga una unica barrera y control de los desbloqueos
	 * 
	 * El ultimo muestra un "*" antes de desbloquear a los demás
	 */
	
	private static final int NPROCESOS = 3;
	private static volatile int contadorP ;
	
	private static Semaphore sincronizacionBarrera;
	private static Semaphore ExclusionMutua; 
	
	private static Semaphore semUnblock; // TODO DUDA doble comprobacion? Inanicion donde ? -> Para comprobar que salen todos, por si hay mas de una iteracion
	
	public static void proceso() throws InterruptedException {
		System.out.println("A");
		ExclusionMutua.acquire();
		contadorP++;
		if (contadorP < NPROCESOS) {
			ExclusionMutua.release();
			sincronizacionBarrera.acquire(); // Puede hacer acquire siendo 0 ?
			semUnblock.release();
		} else {
			System.out.println("*");
			contadorP = 0;
			for (int i = 0; i < NPROCESOS; i++) {
				Thread.sleep(200);
				sincronizacionBarrera.release();
			}
			for (int i = 0; i < NPROCESOS; i++) {
				Thread.sleep(200);
				semUnblock.acquire();
			}
		}
		System.out.println("B");
	}
	
	public static void main(String[] args) {
		sincronizacionBarrera = new Semaphore(0);
		semUnblock = new Semaphore(1);
		ExclusionMutua = new Semaphore(1);
		
		List<Thread> ths = new ArrayList<Thread>();
		for (int i = 0; i < NPROCESOS; i++) {
			Thread th = new Thread(() -> {
				try {
					proceso();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			th.start();
			ths.add(th);
		}
	}
	
	/*
	 * Dudas: 
	 * 	- Linea 29 -> Doble comprobacion ?
	 *  - Lineas 37 -> Acquire siendo 0 ?
	 *  - Linea 41 -> contaodor ?
	 *  - Linea 56 -> Iiniciar a 1 o 0
	 */
}
