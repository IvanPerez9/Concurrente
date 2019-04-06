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
public class SincronizacionCondicionalProductorConsumidor {

	/*
	 * Tema 2:
	 * 
	 * Se desea implementar un programa concurrente con un proceso que produce informaci�n (productor) y otro proceso que 
	 * hace uso de esa informaci�n (consumidor) 
	 * El proceso productor genera un n�mero aleatorio y termina
	 * El proceso consumidor muestra por pantalla el n�mero generado y termina
	 * 
	 * Se desea ampliar el programa anterior de forma que el proceso productor est� constantemente produciendo
	 * El proceso consumidor estar� constantemente consumiendo los productos 
	 * No se puede quedar ning�n producto sin consumir 
	 * No se puede consumir dos veces el mismo producto 
	 * 
	 */
	
	private static final int SIZE = 10;
	private static int numero = 0;
	private static boolean producido = false; // -> Marca si un producto se ha consumido, entonces puedo producir otro
	
	public static void productor () {
		while(true) {
			while(producido) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Random rdn = new Random();
			numero = rdn.nextInt(SIZE);
			producido = true;
			System.out.println("Producido: " + numero);
		}
		
	}
	
	public static void consumidor () {
		while(true) {
			while (!producido) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("El numero consumido es: " + numero);
			numero = -1;
			producido = false;
		}
		
	}
	
	public static void main(String[] args) {
		Thread productor = new Thread(() -> productor());
		Thread consumidor = new Thread(() -> consumidor());
		
		productor.start();
		consumidor.start();
	}
}
