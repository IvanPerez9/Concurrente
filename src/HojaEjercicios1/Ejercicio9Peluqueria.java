/**
 * 
 */
package HojaEjercicios1;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Ejercicio9Peluqueria {

	/*
	 * . Simula el funcionamiento de una peluquería que tiene una silla utilizada por el 
	 * peluquero para cortar el pelo y N sillas en la que esperan los clientes su turno. El 
	 * funcionamiento será el siguiente: si no hay clientes, el peluquero se sienta en la 
	 * silla a la espera que llegue el siguiente cliente. Cuando llega un cliente, si el 
	 * peluquero está activo, es decir, se encuentra cortando el cabello a un cliente, 
	 * tendrá que esperarse en las N sillas siempre y cuando una se encuentren vacías
	 */
	
	private static BlockingQueue<Integer> cola ;
	private static final int NSILLAS = 4;
	
	/*
	 * Esperar para cortar el pelo
	 */
	public static void productor () throws InterruptedException {
		while(true) {
			int cliente = new Random().nextInt(10) ;
			cola.put(cliente);
			System.out.println("Cliente: " + cliente + " esperando.");
			Thread.sleep(1000);
		}
	}
	
	/*
	 * Si hay cliente lo coje
	 */
	public static void consumidor() throws InterruptedException {
		while(true) {
			int cliente = cola.take();
			System.out.println("Corta el pelo a " + cliente);
			Thread.sleep(new Random().nextInt(3000));
		}
	}
	
	public static void main(String[] args) {
		cola = new LinkedBlockingQueue<>(NSILLAS);
		
		Thread productor = new Thread(() -> {
			try {
				productor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		Thread consumidor = new Thread(() -> {
			try {
				consumidor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		productor.start();
		consumidor.start();
	}
	
}
