/**
 * 
 */
package EjerciciosRepaso;

import java.util.concurrent.Exchanger;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class ProductorConsumidorIntercambiadorRepaso {

	/*
	 * Problema de productor consumidor con un intercambiador -> Tema 4.2
	 * 
	 * Permite implementarlo sin buffer -> Objeto de la clase Exchange que permite a dos hilos intercambiar
	 * El primer hilo queda bloqueado hasta que ambos ejecutan el metodo exchange() , despues ambos intercambian los valores pasado por parametros
	 * Es un metodo bloqueante asi que eleva excepcion
	 */
	
	static Exchanger<Double> intercambiador = new Exchanger<>();
	static final int VALOR = 10;
	
	public static void productor() {
		try {
			double producto = 0;
			for (int i = 0; i < VALOR; i++) {
				producto ++;
				Thread.sleep(500);
				intercambiador.exchange(producto); // Intercambia el valor de la variable cada vez que llega al exchange
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void consumidor () {
		try {
			for (int i = 0; i < VALOR; i++) {
				double producto = intercambiador.exchange(null); // Ambos tienen que llegar a "exchange" el consumidor no devuelve nada
				System.out.println("Producto " + producto);
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Thread th1 = new Thread (() -> productor() );
		Thread th2 = new Thread(() -> consumidor());
		
		th1.start();
		th2.start();
	}
	
}
