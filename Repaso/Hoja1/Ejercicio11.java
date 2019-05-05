/**
 * 
 */
package Hoja1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Ejercicio11 {

	/*
	 * Simular garaje gratuito de vehículos. Implementar un garaje con capacidad para 
	 * N coches, cuyo acceso se realiza a través de E barreras automáticas de entrada y 
	 * S de salida. Dichas barreras se encuentran numeradas, desde la barrera 0 hasta 
	 * la E − 1 son de entrada y desde la barrera E hasta la E + S − 1 son de salida. Simular 
	 * el uso de las barreras mandando a dormir durante 5 milisegundos al vehículo 
	 * que está pasando. El número de vehículos será un parámetro de la aplicación concurrente
	 */
	
	private static final int CAPACIDAD = 20;
	private static final int COCHES = 10;
	private static int contador; // Coches dentro
	
	private static Semaphore semE ;
	private static Semaphore semS;
	private static Semaphore semContador;
	
	
	public static void salidas (int id) {
		try {
			semS.acquire();
			System.out.println("Coche " + id + " sale" );
			semContador.acquire();
			contador--;
			semContador.release();
			System.out.println("Ha salido " + id);
			semS.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void entrada (int id) {
		try {
			semE.acquire();
			semContador.acquire();
			if (contador < CAPACIDAD) {
				System.out.println("Coche " + id + " entra") ;
				contador++;
				semContador.release();
				System.out.println("Coche " + id + " dentro");
				Thread.sleep(500);
			} else {
				System.out.println("Lleno");
				semContador.release();
			}
			semE.release();
			salidas(id);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		semE = new Semaphore(1);
		semS = new Semaphore(1);
		semContador = new Semaphore(1);
		contador = 0;
		
		List<Thread> ths = new ArrayList<>();
		for (int i = 0; i < COCHES; i++) {
			final int id = i+1;
			Thread th = new Thread(() -> entrada(id));
			ths.add(th);
		}
		
		for (Thread th : ths) {
			th.start();
		}
		for (Thread th : ths) {
			th.join();
		}
	}
}
