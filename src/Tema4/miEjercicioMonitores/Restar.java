/**
 * 
 */
package Tema4.miEjercicioMonitores;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Restar extends Thread implements Runnable {

	private Monitor Data;
	
	public Restar (Monitor aux) {
		Data = aux;
	}
	
	public void run() {
		Data.decrementar();
	}
}
