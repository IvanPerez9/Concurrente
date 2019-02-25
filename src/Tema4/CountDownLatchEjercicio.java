/**
 * 
 */
package Tema4;

import java.util.concurrent.CountDownLatch;

/**
 * @date 25 feb. 2019
 * 
 * @author Iván - IvanPerez9
 *
 */
public class CountDownLatchEjercicio {

	private static final int NRUNNERS = 10;
	private static CountDownLatch latch = new CountDownLatch(4);
	
	// Con un semaforo, tienes que bloquear todo antes de que salgan los corredores. Tantos permisos como corredores
	
	public static void runner (int id) {
		System.out.println("ready " + id);
		try {
			latch.await(); // Me espero hasta que me digan que continue, es bloqueante elevo una excepcion
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("running " + id);
	}
	
	public static void judge() {
		for (int i = 3; i >= 0; i--) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Counter " +i);
			latch.countDown();
		}
	}
	
	public static void main(String[] args) {
		new Thread (() -> judge()).start();
		for (int i = 0; i < NRUNNERS; i++) {
			String string = args[i];
			int id = i+1;
			new Thread (() -> runner(id)).start();
		}
	}
	
	// Diferencia con la barrera que no hace falta que lleguen al mismo punto de sincronizacion, si no que solo hay 1 contando y cuando llegue ese contador es cuando inicio
	// Solo una unica salida, no hay reset, hacer de nuevo new 
}
