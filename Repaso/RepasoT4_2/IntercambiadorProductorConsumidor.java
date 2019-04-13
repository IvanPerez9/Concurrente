/**
 * 
 */
package RepasoT4_2;

import java.util.concurrent.Exchanger;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class IntercambiadorProductorConsumidor {

	/*
	 * Problema de productor consumidor con un intercambiador
	 * Los hilos se bloquean si usan exchange
	 * 
	 * Cuando el segundo hilo llama a exchange, ambos intercambian los valores pasados por parametro
	 */
	
	private static Exchanger<Double> intercambiador = new Exchanger<Double>();
	
	public static void productor() {
		try {
			double producto = 0;
			for (int i = 0; i < 10; i++) {
				producto++;
				Thread.sleep(1000);
				intercambiador.exchange(producto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void consumidor() {
		try {
			for (int i = 0; i < 10; i++) {
				double producto = intercambiador.exchange(null);
				System.out.println("Producto: " + producto);
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		 new Thread(() -> productor()).start();
		 new Thread(() -> consumidor()).start();
	}
	
}
