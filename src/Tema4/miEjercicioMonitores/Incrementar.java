/**
 * 
 */
package Tema4.miEjercicioMonitores;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Incrementar extends Thread implements Runnable {

private Monitor Data;
	
	public Incrementar (Monitor aux) {
		Data = aux;
	}
	
	public void run() {
		Data.incrementar();
	}
}
