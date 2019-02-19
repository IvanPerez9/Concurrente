/**
 * 
 */
package Tema4.EjercicioMonitores;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Comprar implements Runnable {

	private Monitor monitor;
	
	public Comprar(Monitor monitor) {
		super();
		this.monitor = monitor;
	}

	@Override
	public void run() {
		while (true) {
			System.out.println("Compro la entrada " + monitor.getEntrada());
		}
	}

}
