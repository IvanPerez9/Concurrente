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
 * Exclusion mutua
 */

public class Semaphore_2_Exclusion {

	private static Semaphore mutex; // Mutual exclusion
	
	public static void process () {
		try {
			mutex.acquire(); // Puedo pasar ?
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		System.out.println("SC1");
		System.out.println("SC2");
		mutex.release();
		System.out.println("SN1");
		System.out.println("SN2");
	}
	
	public static void main(String[] args) {
		mutex = new Semaphore(1);
		// No puede ser 0 porque si entra se bloquea, lo dejo a 1 para que entre solo 1
		for (int i = 0; i < 5; i++) {
			new Thread (() -> process()).start();
		}
	}
	
}

