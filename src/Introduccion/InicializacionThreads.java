/**
 * 
 */
package Introduccion;

/**
 * @date 29 ene. 2019
 * 
 * @author Iván - IvanPerez9
 *
 */
public class InicializacionThreads {

	public static void main(String[] args) {

		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("Thread 1");
			}
		}).start();

		new Thread(() -> System.out.println("Thread 2")).start();

		new Thread(() -> {
			System.out.println("Thread 3");
		}).start();
	}
	
}
