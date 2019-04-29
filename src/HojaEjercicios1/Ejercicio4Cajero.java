/**
 * 
 */
package HojaEjercicios1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Ejercicio4Cajero {
	
	public class Cajero {
		
		protected int[] cobrados;

		public Cajero(int[] cobrados) {
			super();
			this.cobrados = cobrados;
		}
		
		public Cajero() {}

		public int[] getCobrados() {
			return cobrados;
		}

		public void setCobrados(int[] cobrados) {
			this.cobrados = cobrados;
		}
	}
	
	private static Queue<Integer> clientesL;
	private static Lock xLock;

	public Ejercicio4Cajero() {
		this.clientesL = new LinkedList<>();
	}

	public void cajero (int cliente) {
		
	}
	
}
