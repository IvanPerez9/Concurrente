/**
 * 
 */
package Tema4.EjercicioMonitoresCerrojos;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class MonitorLock {

	/*
	 *  Utilizar un monitor con 1 Lock, 2 condiciones y 2variables enteras
	 *  que controlan en número de operaciones de
	 *  mantenimiento y de peticiones web
	 */
	
	private int operacionesMantenimiento;
	private int peticionesWeb;
	
	private ReentrantLock xLock;
	
	private Condition mantenimiento ;
	private Condition peticiones ;
	
	/*
	 * Si pongo syncronize esta en exclusion mutua respecto al objeto monitos
	 *
	 * Si lo pongo con Locks está en EM respecto a Lock, y como todas mis variables parten de Lock
	 * Y controlar las condiciones 
	 */
	
	public MonitorLock() {
		operacionesMantenimiento = 0;
		peticionesWeb = 0;
		xLock = new ReentrantLock();
		mantenimiento = xLock.newCondition();
		peticiones = xLock.newCondition();
	}
	
	public void startWeb (String name) throws InterruptedException {
		xLock.lock();
		while (operacionesMantenimiento > 0) {
			System.out.println(name + "esperando servidor ocupado");
			peticiones.await();
		}
		peticionesWeb++;
		peticiones.signal();
		System.out.println("Empieza " + name );
		xLock.unlock();
	}
	
	public void startMantenimiento (String name) throws InterruptedException {
		xLock.lock();
		
		//getWaitQueueLength para verificar que si hay gente esperando en Web no haga mantenimiento
		
		while(peticionesWeb > 0 || xLock.getWaitQueueLength(peticiones) > 0) { 
			System.out.println(name + "esperando servidor ocupado");
			mantenimiento.await();
		}
		operacionesMantenimiento++;
		System.out.println("Empieza " + name );
		mantenimiento.signal(); // Cuando he terminado aviso a mi condicion
		xLock.unlock();
	}
	
	public void stoptWeb (String name) {
		xLock.lock();
		System.out.println("Ha terminado " + name );
		peticionesWeb--;
		if (peticionesWeb == 0) {
			mantenimiento.signal();
		}
		xLock.unlock();
	}
	
	public void stoptMantenimiento (String name) {
		xLock.lock();
		System.out.println("Ha terminado " + name );
		operacionesMantenimiento--;
		if (operacionesMantenimiento == 0) {
			peticiones.signal();
		}
		xLock.unlock();
	}
	
	/*
	 * Para iniciar solo que vaya de 1 en 1 , termina cuando estan todos
	 * Una vez termina avisa al otro respectivamente
	 */
}