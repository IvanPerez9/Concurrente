/**
 * 
 */
package RepasoT2;

import java.util.Random;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class SincronizacionCondicionalClienteServidor {

	/*
	 * El proceso cliente hace una petición al proceso servidor y espera su respuesta, cuando la recibe, la procesa.
	 * El proceso servidor no hace nada hasta que recibe una petición, momento en el que la contesta.
	 * El proceso cliente va a pedir un número aleatorio al servidor y lo va a procesar mostrándolo por pantalla
	 * 
	 * Se desea ampliar el programa anterior de forma que el proceso cliente esté constantemente haciendo peticiones y el proceso servidor atendiendo a las mismas
	 */
	
	private static int numero = 0;
	private static boolean peticion = false; // -> Si realiza el cliente una peticion
	private static boolean respuesta = false; // -> Si ya se ha servido un numero
	
	public static void servidor () {
		while(true) {
			while(peticion) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			numero = new Random().nextInt(10);
			System.out.println("Servido el: " + numero);
			try {
				Thread.sleep(2500);
				respuesta = true;
				peticion = false;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void cliente () {
		while(true) {
			while(respuesta) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Consumido el: " + numero);
			try {
				Thread.sleep(2000);
				peticion = true;
				respuesta = false;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	// OJO con los Sleeps para ver bien las peticiones
	
	public static void main(String[] args) {
		Thread servidor = new Thread(() -> servidor());
		Thread cliente = new Thread(() -> cliente());
		
		servidor.start();
		cliente.start();
	}
}
