/**
 * 
 */
package Hoja1;

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
	
	private static int cuenta ;
	private static Semaphore sem;
	
	
	public static void incrementarCuenta () {
		while(true) {
			try {
				sem.acquire();
				if (cuenta < 1000) {
					System.out.print("Incremento la cuenta en 200: ");
					cuenta += 200;
					sem.release();
					System.out.println(cuenta);
				} else {
					sem.release();
					System.out.println("No puede incrementar mas");
				}
				Thread.sleep(new Random().nextInt(3000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void sacarDinero () {
		while(true) {
			try {
				sem.acquire();
				if (cuenta > 200) {
					System.out.print("Saco dinero de la cuenta: ");
					cuenta -= 200;
					sem.release();
					System.out.println(cuenta);
				} else {
					sem.release();
					System.out.println("No puede sacar más dinero");
				}
				Thread.sleep(new Random().nextInt(3000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		cuenta = 0;
		sem = new Semaphore(1);
		
		new Thread(() -> incrementarCuenta()).start();
		new Thread(() -> sacarDinero()).start();
	}
	
}
