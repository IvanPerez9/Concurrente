/**
 * 
 */
package HojaEjercicios1;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Ejercicio11Garaje {

	/*
	 * Simular garaje gratuito de vehículos. Implementar un garaje con capacidad para 
	 * N coches, cuyo acceso se realiza a través de E barreras automáticas de entrada y 
	 * S de salida. Dichas barreras se encuentran numeradas, desde la barrera 0 hasta 
	 * la E − 1 son de entrada y desde la barrera E hasta la E + S − 1 son de salida. Simular 
	 * el uso de las barreras mandando a dormir durante 5 milisegundos al vehículo 
	 * que está pasando. El número de vehículos será un parámetro de la aplicación concurrente
	 */
	
	private static final int NCOCHES = 10;
	private static final int SIZE = 15;
	private static int cont ;
	
	private static Semaphore semE;
	private static Semaphore semS;
	private static Semaphore semContador;
	
	
	public static void salida (int id) throws InterruptedException {
		semS.acquire();
		System.out.println("Coche " + id + " sale.");
		semContador.acquire();
		cont--;
		semContador.release();
		semS.release();
		System.out.println("Ha salido");
	}
	
	public static void entrada (int id) throws InterruptedException {
		semE.acquire();
		System.out.println("Coche " + id + " esperando para entrar.");
		
		if (cont < SIZE) {
			semContador.acquire();
			cont++;
			if(cont == SIZE) {
				System.out.println("Parking lleno");
			}
			semContador.release();
			System.out.println("Coche " + id + " dentro de parking.");
		}
		semE.release();
		
		Thread.sleep(new Random().nextInt(500)); // Está durante un tiempo -> 5 milisegundos 
		salida(id);
	}
	
	public static void main(String[] args) {
		cont = 0; 
		semE = new Semaphore(1);
		semS = new Semaphore(1);
		semContador = new Semaphore(1);
		
		for (int i = 0; i < NCOCHES; i++) {
			final int id = i+1;
			new Thread(() -> {
				try {
					entrada(id);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();;
		}
	}
	
	
}
