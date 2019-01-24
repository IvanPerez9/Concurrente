/**
 * 
 */
package Introduccion;

/**
 * @date 24 ene. 2019
 * 
 * @author Iván - IvanPerez9
 *
 */
public class Introduccion {

	/**
	 *  Ejemplos de programación concurrente. Diapositiva 46 - Introduccion
	 *  Problemas de sincronización (condición de carrera)
	 *	
	 *	 •Programa concurrente que incremente (hilo 1) y decremente (hilo 2) una variable
	 *	
	 *	 •Programa concurrente que escriba 5 veces XXX (hilo 1), otras 5 --- (hilo 2) y una vez *** (hilo 3)
	 *
	 *	 - Espera activa ? si le añado un sleep() 
	 */
	
	static int n = 10; // static si no no puede tener acceso desde otro metodo static 
	
	public static void main(String[] args) {
		Thread th1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					System.out.println("XXX");
				}
			}
		});
		Thread th2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					System.out.println("---");
				}
				
			}
		});
		
		Thread th3 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					System.out.println("***");
				}
				
			}
		});
		
		th1.start();
		th2.start();
		th3.start();
	}
	
	
}
