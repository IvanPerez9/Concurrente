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
public class SincronizacionCondicionalSemaforo {

	/*
	 * Dos metodos que pinten ciertas letras, el proceso 2 no puede pintar "PB2" hasta que el 1 haya pintado su "PB1"
	 * Al ser sincronizacionCondicional iniciamos el semaforo a 0
	 */
	
	private static Semaphore semaforo ;
	
	public static void metodo1 () {
		new Thread(() -> {
			System.out.println("metodo 1: PA1");
			semaforo.release();
			System.out.println("metodo 1: PA2");
		}).start();
	}
	
	public static void metodo2 () {
		new Thread(() ->  {
			try {
				System.out.println("metodo 2: PB1");
				semaforo.acquire();
				System.out.println("metodo 2: PB2");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}
	
	public static void main(String[] args) {
		semaforo = new Semaphore(0);
		metodo1();
		metodo2();
	}
	
}
