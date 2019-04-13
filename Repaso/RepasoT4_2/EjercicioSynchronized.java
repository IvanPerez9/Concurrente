/**
 * 
 */
package RepasoT4_2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class EjercicioSynchronized {

	/*
	 * Exclusion mutua con syncronized , tienen un objeto que hace de cerrojo
	 * Son reentrantes, lo que permite su reutilizacion
	 */
	
	private static double x = 0;
	private static Object xLock = new Object();
	
	public static void incrementar() {
		for (int i = 0; i < 8; i++) {
			synchronized (xLock) {
				x++;
			}
		}
	}
	
	public static void main(String[] args) {
		List<Thread> ths = new ArrayList<>();
		
		for (int i = 0; i < 3; i++) {
			ths.add(new Thread(() -> incrementar()));
		}
		
		for (Thread th : ths) {
			th.start();
		}
		
		for (Thread th : ths) {
			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("X incrementada a: " + x);
	}
	
}
