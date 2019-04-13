/**
 * 
 */
package Tema4.EjercicioMonitoresCerrojos;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Main {
	
	public static void main(String[] args) {
		MonitorLock monitor = new MonitorLock();
		int nMan = 5;
		int nReq = 5;
		
		List<Thread> ths = new ArrayList<>();
		
		//Para mantenimiento
		for (int i = 0; i < nMan; i++) {
			int id = i+1;
			ths.add(new Thread(() ->  {
				try {
					monitor.startMantenimiento("mantenimiento" + id);
					Thread.sleep(1000);
					monitor.stoptMantenimiento("para mantenimiento" + id);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}));
		}
		
		for (int i = 0; i < nReq; i++) {
			int id2 = i+1;
			ths.add(new Thread(() ->  {
				try {
					monitor.startWeb("request" + id2);
					Thread.sleep(1000);
					monitor.stoptWeb("para mantenimiento" + id2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}));
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
	}
	
}
