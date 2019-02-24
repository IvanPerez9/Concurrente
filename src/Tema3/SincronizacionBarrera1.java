/**
 * 
 */
package Tema3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class SincronizacionBarrera1 {

	/*
	 * Un proceso tiene que esperar al resto de procesos para poder continuar
	 * 
	 * Programa con N procesos, cada proceso escribe la A y luego la B
	 * Los procesos tienen que esperar a que todos escriban la A antes de escribir la B
	 * 
	 */
	
	private static final int NPROCESOS = 3;
	private static volatile int contadorP ;
	
	private static Semaphore sincronizacionBarrera;
	private static Semaphore ExclusionMutua; // La variable de contador en EM
	
	public static void proceso() throws InterruptedException {
		System.out.println("A");
		ExclusionMutua.acquire();
		contadorP++;
		if (contadorP < NPROCESOS) {
			ExclusionMutua.release();
			sincronizacionBarrera.acquire();
		} else {
			ExclusionMutua.release();
			for (int i = 0; i < NPROCESOS; i++) {
				Thread.sleep(500);
				sincronizacionBarrera.release();
			}
		}
		System.out.println("B");
	}
	
	public static void main(String[] args) {
		contadorP = 0;
		sincronizacionBarrera = new Semaphore(0);
		ExclusionMutua = new Semaphore(1); // EM siempre 1 para permitir iniciar
		
		List<Thread> ths = new ArrayList<Thread>();
		for (int i = 0; i < NPROCESOS; i++) {
			Thread th = new Thread(() -> {
				try {
					proceso();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			th.start();
			ths.add(th);
		}
	}
	
	// Esta implementacion no permite usar solo 1 barrera porque se produce inanicion
}
