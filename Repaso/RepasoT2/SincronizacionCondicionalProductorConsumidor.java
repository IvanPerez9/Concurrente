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
	 * Se desea implementar un programa concurrente con un proceso que produce información (productor) y otro proceso que 
	 * hace uso de esa información (consumidor) 
	 * El proceso productor genera un número aleatorio y termina
	 * El proceso consumidor muestra por pantalla el número generado y termina
	 * 
	 * Se desea ampliar el programa anterior de forma que el proceso productor esté constantemente produciendo
	 * El proceso consumidor estará constantemente consumiendo los productos 
	 * No se puede quedar ningún producto sin consumir 
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
