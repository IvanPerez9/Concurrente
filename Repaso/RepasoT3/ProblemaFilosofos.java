/**
 * 
 */
package RepasoT3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class ProblemaFilosofos {

	public static void main(String[] args) throws InterruptedException {
		int numeroFilosofos = 5;
		Semaphore[] semPalillos = new Semaphore[numeroFilosofos];
		for (int i = 0; i < numeroFilosofos; i++) {
			semPalillos[i] = new Semaphore(1); // Exclusion mutua
		}
		
		List<Thread> ths = new ArrayList<>();
		for (int i = 0; i < numeroFilosofos; i++) {
			FilosofoClase ph = new FilosofoClase(i, semPalillos, "Filosofo" + i+1);
			ths.add(ph);
			ph.start();
		}
		
		for (Thread th : ths) {
			th.join();
		}
	}
	
}
