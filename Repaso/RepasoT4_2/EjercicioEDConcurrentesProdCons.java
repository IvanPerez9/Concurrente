/**
 * 
 */
package RepasoT4_2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class EjercicioEDConcurrentesProdCons {

	private static int SIZE = 5;
	private static BlockingQueue<String> queue = new ArrayBlockingQueue<>(SIZE);
	
	public static void productor (int id) {
		try {
			for (int i = 0; i < 10; i++) {
				String product = "Productor " + id + " producto: " + i ;
				queue.put(product);
				System.out.println("Produciendo: " + product);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void consumidor (int id) {
		try {
			while(true) {
				// Al ser bloqueante, si no hay nada se bloquea sola, no hace falta controlarlo OJO
				String elem = queue.take();
				System.out.println("Consumidor: " + id + " producto: " + elem);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			int id = i+1;
			new Thread(() -> productor(id)).start();
		}
		for (int i = 0; i < 2; i++) {
			int id = i+1;
			new Thread(() -> consumidor(id)).start();
		}
	}
	
}
