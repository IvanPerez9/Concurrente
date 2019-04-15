/**
 * 
 */
package RepasoT4_2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class EjecicioCyclicBarrier {

	/*
	 * Implementar un programa concurrente donde NPROC hilos escriban una A y se queden 
	 * bloqueados. El último hilo escribirá (además de la A) un *
	 */
	
	private static final int NPROC = 4;
	
	private static CyclicBarrier barrera = new CyclicBarrier(NPROC, new Runnable() {
		// Al terminar pinta esto
		@Override
		public void run() {
			System.out.println("*");
		}
	});
	
	
	public static void proceso () {
		try {
			System.out.println("A");
			Thread.sleep(1000);
			barrera.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		List<Thread> ths = new ArrayList<>();
		
		for (int i = 0; i < NPROC; i++) {
			ths.add(new Thread(()->proceso()));
		}
		
		for (Thread th : ths) {
			th.start();
		}
	}
}
