/**
 * 
 */
package RepasoT4_2_Monitores;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class EjercicioMonitoresSyncronized {

	private static final int VENDEDORES = 5;
	private static final int COMPRADORES = 10;
	private static MonitorSyncronized monitor ;
	
	public static void comprar() {
		for (int i = 0; i < COMPRADORES; i++) {
			try {
				monitor.comprar(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void vender() {
		for (int i = 0; i < VENDEDORES; i++) {
			monitor.vender(i);
		}
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		monitor = new MonitorSyncronized();
		List<Thread> ths = new ArrayList<>();
		
		ths.add(new Thread(()-> comprar()));
		
		for (int i = 0; i < VENDEDORES; i++) {
			ths.add(new Thread(() -> vender()));
		}
		
		for (Thread th : ths) {
			th.start();
		}
		
		for (Thread th : ths) {
			th.join();
		}
	}
}
