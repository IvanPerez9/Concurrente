/**
 * 
 */
package HojaEjercicios1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Ejercicio8 {

	private static int cuenta;
	
	
	public synchronized static void incrementar (int id) {
		if(cuenta + 200 < 1000) {
			cuenta = cuenta + 200;
		}
	}
	
	public synchronized static void decrementar (int id) {
		if (cuenta - 200 > 0 ) {
			cuenta = cuenta - 200;
		}
	}
	
	public static void main(String[] args) {
		List<Thread> ths = new ArrayList<>();
		
		for (int i = 0; i < 5; i++) {
			final int id = i+1;
			ths.add(new Thread (() -> incrementar(id)));
		}
		
		for (Thread thread : ths) {
			thread.start();
		}
		
		for (Thread thread : ths) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for (Thread th : ths) {
			System.out.println(th);
		}
	}
}
