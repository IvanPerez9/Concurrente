/**
 * 
 */
package HojaEjercicios1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Ejercicio8 {
	
	/*
	 * Simular la gestión de cuenta bancaria donde existen dos tipos de procesos, uno 
	 * encargado de extraer 200 euros y otro que ingresa 200 euros. La cuenta bancaria 
	 * nunca podrá exceder de 1000 euros ni tampoco puede encontrarse en números 
	 * rojos. El número de cada tipo de proceso será configurado mediante dos 
	 * parámetros del algoritmo
	 */

	private static int cuenta;
	private static Semaphore sem;
	
	
	public static void incrementar (int id) throws InterruptedException {
		while(true) {
			sem.acquire();
			if (cuenta < 1000) {
				cuenta += 200;
				System.out.println("Ingreso de 200, cuenta: " + cuenta);
				sem.release();
			} else {
				sem.release();
				System.out.println("No puede tener mas dinero");
			}
			Thread.sleep(new Random().nextInt(3000)*2);	

		}
	}
	
	public static void decrementar (int id) throws InterruptedException {
		while(true) {
			sem.acquire();
			if (cuenta > 200) {
				cuenta -= 200;
				System.out.println("Cuenta decrementada, quedan " + cuenta);
				sem.release();
			} else {
				sem.release();
				System.out.println("No puede sacar dinero");
			}
			Thread.sleep(new Random().nextInt(3000)*2);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		sem = new Semaphore(1);
		cuenta = 0;
		List<Thread> ingresos = new ArrayList<>();
		List<Thread> transferencias = new ArrayList<>();
		
		for (int i = 0; i < 4/2; i++) {
			final int id = i;
			ingresos.add(new Thread(() -> {
				try {
					incrementar(id + 1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			})) ;
		}
		
		for (int i = 0; i < 4 ; i++) {
			final int id = i;
			transferencias.add(new Thread(() -> {
				try {
					decrementar(id + 1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			})) ;
		}
		
		for (Thread th : ingresos) {
			th.start();
		}
		
		for (Thread th : transferencias) {
			th.start();
		}
		
		for (Thread th : ingresos) {
			th.join();
		}
		
		for (Thread th : transferencias) {
			th.join();
		}
	}
}
