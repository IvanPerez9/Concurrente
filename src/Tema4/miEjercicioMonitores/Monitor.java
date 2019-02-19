/**
 * 
 */
package Tema4.miEjercicioMonitores;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Monitor {

	/**Ejemplo de Monitor sencillo. Encapsula una variable protegida por
	*la abstraccion y posee una interfaz de dos metodos para
	*incrementarla y decrementarla y un tercero para conocer el valor
	*del recurso protegido.
	*/

	private static int datos; // Protegido
	private boolean condicion = false;
	
	public Monitor (int Dato) {
		this.datos = Dato;
	}
	
	public synchronized void incrementar() {
		while (!condicion) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			condicion = true;
			datos++;
			notifyAll();
		}
	}
	
	public synchronized void decrementar() {
		while (!condicion) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			condicion = true;
			datos--;
			notifyAll();
		}
	}
}
