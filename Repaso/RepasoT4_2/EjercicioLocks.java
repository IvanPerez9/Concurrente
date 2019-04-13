/**
 * 
 */
package RepasoT4_2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class EjercicioLocks {

	private static int x = 0;
	private static Lock xLock = new ReentrantLock();
	
	public static void incrementar() {
		for (int i = 0; i < 567; i++) {
			xLock.lock();
			x++; 
			xLock.unlock();
		}
	}
	
	public static void main(String[] args) {
		List<Thread> ths = new ArrayList<>();
		
		for (int i = 0; i < 4; i++) {
			ths.add(new Thread(()-> incrementar()));
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
		
		System.out.println("Valor de x: " + x);
	}
	
}
