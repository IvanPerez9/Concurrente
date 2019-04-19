/**
 * 
 */
package HojaEjercicios1;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.jsoup.select.Evaluator.IsEmpty;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Ejercicio4Cajero {
	
	private static Queue<Integer> clientesL;

	public Ejercicio4Cajero() {
		this.clientesL = new LinkedList<>();
	}

	public synchronized void cajero (int cliente) {
		try {
			Thread.sleep(1000);
			while(clientesL.isEmpty()) {
				this.wait();
			}
			clientesL.add(cliente);
			System.out.println();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
