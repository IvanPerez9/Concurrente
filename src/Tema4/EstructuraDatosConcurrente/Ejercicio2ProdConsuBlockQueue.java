/**
 * 
 */
package Tema4.EstructuraDatosConcurrente;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Ejercicio2ProdConsuBlockQueue {

	public static BlockingQueue<String> cola = new LinkedBlockingQueue<>();
	private static final int SIZE = 10;
	
	public static void productor (int id) {
		for (int i = 0; i < SIZE; i++) {
			try {
				String texto = "Productor " + id + " -> Producto " + i ;
				cola.put(texto);
				System.out.println("PRODUCIENDO PRODUCTO");
				Thread.sleep(new Random().nextInt(2000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void consumidor (int id) {
		try {
			while(true) {
				String msg = cola.take();
				System.out.println("CONSUMIDOR " + id + " : " + msg);
				Thread.sleep(new Random().nextInt(2000));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < SIZE; i++) {
			final int id = i+1;
			new Thread(() -> productor(id) ).start();
		}
		for (int i = 0; i < SIZE; i++) {
			final int id = i+1;
			new Thread(() -> consumidor(id) ).start();
		}
	}
}
