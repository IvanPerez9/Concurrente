/**
 * 
 */
package Hoja1;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Ejercicio9 {

	/*
	 * . Simula el funcionamiento de una peluquería que tiene una silla utilizada por el 
	 * peluquero para cortar el pelo y N sillas en la que esperan los clientes su turno. El 
	 * funcionamiento será el siguiente: si no hay clientes, el peluquero se sienta en la 
	 * silla a la espera que llegue el siguiente cliente. Cuando llega un cliente, si el 
	 * peluquero está activo, es decir, se encuentra cortando el cabello a un cliente, 
	 * tendrá que esperarse en las N sillas siempre y cuando una se encuentren vacías
	 */
	
	private static BlockingQueue<Integer> cola;
	private static final int SILLAS = 4;
	
	
	public static void esperar () throws InterruptedException {
		while(true) {
			//Thread.sleep(new Random().nextInt(2000));
			int cliente = new Random().nextInt(10);
			cola.add(cliente);
			System.out.println("Cliente " + cliente + " esperando");
			Thread.sleep(200);
		}
	}
	
	public static void cortarPelo () throws InterruptedException {
		while(true) {
			int cliente = cola.take();
			System.out.println("Cortando pelo a " + cliente);
			Thread.sleep(new Random().nextInt(2000));
		}
	}
	
	public static void main(String[] args) {
		cola = new LinkedBlockingQueue<>();
		
		Thread productor = new Thread(() -> {
			try {
				esperar();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		// con 2 peluqueros ??? 
		
		for (int i = 0; i < 2; i++) {
			new Thread(() -> {
				try {
					cortarPelo();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
		}
		
		productor.start();
	}
}
