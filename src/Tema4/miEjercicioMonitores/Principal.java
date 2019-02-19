/**
 * 
 */
package Tema4.miEjercicioMonitores;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Principal {

	public static void main(String[] args) {
		Monitor mon = new Monitor(100);
		
		new Incrementar(mon).start();
		new Restar(mon).start();
		
		for (int i = 0; i < 5; i++) {
			System.out.println(mon.toString());
		}
	}
	
}
