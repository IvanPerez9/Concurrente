/**
 * 
 */
package Tema4;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Ejercicio4_1 {

	/*
	 * Similar code to Messages in class 
	 */
	
	public static void hiloSecundario() {
		boolean continuar = true;
		while(continuar) {
			System.out.println("MSJ: La vida es bella");
			System.out.println("MSJ: O no...");
			System.out.println("MSJ: Y molestan");
			if (Thread.interrupted()){
				System.out.println("MSJ: Se acabo");
				continuar = false;
				return;
			}
		}
	}
	
	// Borrar las e.printStackTrace() si no hace falta
	
	public static void main(String[] args) {
		Thread hiloMensajes = new Thread(() -> {
			hiloSecundario();
		});
		
		hiloMensajes.start();
		
		// No puedo hacer join de golpe porque los bloqueo. Y no quiero bloquear porque se llama uno al otro
		
		boolean continuar = true;
		int contador = 0;
		while (continuar) {
			if (!hiloMensajes.isAlive()) {
				continuar = false;
			} else {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (contador == 5) {
					System.out.println("\nCansado de esperar");
					hiloMensajes.interrupt();
					// Hago el join para esperar que termine y no interrumpirle a la mitad .
					// Junto con el interrupt -> Cuando puedas parate
					try {
						hiloMensajes.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					continuar = false;
				} else {
					System.out.println("Todavia esperando");
					contador++;
				}
			}		}
		System.out.println("Por fin");
	}
	
}
