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
					monitor.startMantenimiento("mantenimiento");
					Thread.sleep(1000);
					monitor.stoptMantenimiento("para mantenimiento");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}));
		}
	}
	
}
