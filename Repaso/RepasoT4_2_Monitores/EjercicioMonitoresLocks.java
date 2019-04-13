/**
 * 
 */
package RepasoT4_2_Monitores;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class EjercicioMonitoresLocks {

	/*
	 * Tantas variables condicionales como sea necesario
	 * Ahora signalAll y await
	 * Await tiene activaciones inesperadas, asi que meterlo en un while tambien
	 */
	
	/*
	 * Atiende peticiones web y operaciones de mantenimiento
	 */
	
	private ReentrantLock xLock;
	private Condition peticionW;
	private Condition mantenimientoW;
	
	private int numPeticiones;
	private int numMantenimiento;
	
	public EjercicioMonitoresLocks() {
		xLock = new ReentrantLock();
		peticionW = xLock.newCondition();
		mantenimientoW = xLock.newCondition();
		
		numMantenimiento = 0;
		numPeticiones = 0;
	}
	
	public void empezarWeb (int id) {
		xLock.lock();
		while(numMantenimiento > 0) {
			try {
				System.out.println("Esperando");
				peticionW.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		numPeticiones++;
		peticionW.signal();
		System.out.println("Empieza web: " + id);
		xLock.unlock();
	}
	
	public void empezarMantenimiento (int id) {
		xLock.lock();
		while((numPeticiones > 0) || (xLock.getWaitQueueLength(peticionW) > 0 ) ) {
			try {
				mantenimientoW.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		numMantenimiento++;
		mantenimientoW.signal();
		System.out.println("Empieza mantenimiento: " + id);
		xLock.unlock();
	}
	
	public void stopWeb(int id) {
		xLock.lock();
		System.out.println("Termina web: " + id);
		numPeticiones--;
		if (numPeticiones == 0) {
			peticionW.signal();
		}
		xLock.unlock();
	}
	
	public void stopMantenimiento(int id) {
		xLock.lock();
		System.out.println("Termina mantenimiento: " + id);
		numMantenimiento--;
		if (numMantenimiento == 0) {
			mantenimientoW.signal();
		}
		xLock.unlock();
	}
	
}
