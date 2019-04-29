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
public class SincronizacionBarreraSemaforoWhile {

	private static final int N = 5;
	private static int nProc ;
	
	private static Semaphore desbloqueo;
	private static Semaphore semnProc;
	
	private static Semaphore out; // Comprobar que salen todos, por si hay mas iteraciones, no se intercalen las A
	
	public static void proceso () throws InterruptedException {
			Thread.sleep(200);
			System.out.println("A");
			semnProc.acquire();
			nProc++;
			if (nProc < N) {
				semnProc.release();
				desbloqueo.acquire();
				out.release();
			} else {
				Thread.sleep(2000);
				System.out.println("*");
				nProc = 0;
				for (int i = 0; i < N -1; i++) {
					desbloqueo.release();
				}
				for (int i = 0; i < N; i++) {
					Thread.sleep(200);
					out.acquire();
				}
			}
	}
	
	public static void main(String[] args) {
		desbloqueo = new Semaphore(0); // Sincronizacion condicional -> Barrera
		semnProc = new Semaphore(1); // Exclusion mutua
		out = new Semaphore(1);
		
		for (int i = 0; i < N; i++) {
			new Thread(() -> {
				try {
					Thread.sleep(1000);
					proceso();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}, "Hilo" + (i+1)).start();
 		}
	}
	
}
