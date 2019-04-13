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
public class MonitorLock {

	public static void main(String[] args) throws InterruptedException {
		EjercicioMonitoresLocks monitor = new EjercicioMonitoresLocks();
		
		int peticionesWeb = 5;
		int mantenimiento = 5;
		
		List<Thread> ths = new ArrayList<>();
		
		for (int i = 0; i < mantenimiento; i++) {
			int id = i+1;
			ths.add(new Thread(()-> {
				try {
					monitor.empezarMantenimiento(id);
					Thread.sleep(500);
					monitor.stopMantenimiento(id);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}));
		}
		
		for (int i = 0; i < peticionesWeb; i++) {
			int id = i+1;
			ths.add(new Thread(()-> {
				try {
					monitor.empezarWeb(id);
					Thread.sleep(500);
					monitor.stopWeb(id);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}));
		}
		
		for (Thread th : ths) {
			th.start();
		}
		
		for (Thread th : ths) {
			th.join();
		}
	}
	
}
