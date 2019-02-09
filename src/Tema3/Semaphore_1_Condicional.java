/**
 * 
 */
package Tema3;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
import java.util.concurrent.Semaphore;

/*
 * Sincronizacion Condicional
 */

public class Semaphore_1_Condicional {

	//Declaracion de un semaforo
	private static Semaphore continuar;
	
	public static void procesoA() {
		new Thread(() ->{
			System.out.println("PA1 ");
			continuar.release();
			System.out.println("PA2 ");
		}).start();
	}
	public static void procesoB() {
		new Thread(() -> {
			System.out.println("PB1 ");
			try {
				continuar.acquire(); // Pide permiso para continuar
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("PB2 ");
		}).start();
	}
	
	public static void main(String[] args) {
		continuar = new Semaphore(0); //Numero de permisos 0 
		procesoA();
		procesoB();
	}
	
}

