/**
 * 
 */
package Tema4;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class MessageNew {

	/*
	 *  Dos hilos, 1 ppal y otro de mensajes
	 */
	
	// Hilo de mensajes como un metodo
	
	public static void hiloMensajes () {
		String[] array = new String[] {
				"La vida es bella",
				"O no..." ,
				"Los pajaritos cantan",
				"Y molestan"
		};
		for (String s : array) {
			try {
				Thread.sleep(1000);
				System.out.println("Hilo MSJ:_" + s);
			} catch (InterruptedException e) {
				System.out.println("Se acabo");
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread th = new Thread(() -> hiloMensajes());
		th.start();
		int contador = 0;
		while (th.isAlive()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (contador == 5) {
				System.out.println("Main: Cansado de esperar");
				th.interrupt();
				th.join();
			} else {
				System.out.println("Main: Esperando");
				contador++;
			}
		}
		System.out.println("\nPor fin!");
	}
}
