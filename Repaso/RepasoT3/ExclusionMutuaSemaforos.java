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
public class ExclusionMutuaSemaforos {

	/*
	 * Garantizar que se realiza en un orden determinado un numero N de procesos.
	 * Inicializamos a 1 el semaforo , ya que es seccion critica
	 */
	
	private static Semaphore semaforo;
	private static final int SIZE = 5;
	
	public static void metodo() throws InterruptedException {
		for (int i = 0; i < SIZE; i++) {
			semaforo.acquire();
			//Seccion critica
			System.out.println("SC1");
			System.out.println("SC2");
			semaforo.release();
			System.out.println("SNC1");
			System.out.println("SNC2");
		}
	}
	
	public static void proceso(String name) {
		new Thread(() ->{
			try {
				metodo();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} , name).start();
	}
	
	public static void main(String[] args) {
		semaforo = new Semaphore(1);
		proceso("Hilo 1");
		proceso("Hilo 2");
	}
	
}
