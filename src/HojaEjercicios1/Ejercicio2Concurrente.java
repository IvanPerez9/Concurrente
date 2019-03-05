/**
 * 
 */
package HojaEjercicios1;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Ejercicio2Concurrente {

	/*
	 * Cada letra 1 hilo ?? 
	 */
	
	static volatile int contador = 0;
	
	public static void contarVocales (Character c) {
		if ((c == 'a') || (c == 'e') || (c == 'i') || (c == 'o') || (c == 'u') ) {
			contador++;
		}
	}
	
	public static void main(String[] args) {
		Scanner entrada = new Scanner (System.in);
		StringBuilder frase = new StringBuilder(entrada.nextLine());
		int salida = 1;
		
		LinkedList<Thread> ths = new LinkedList<Thread>();
		for (int j = 0; j < frase.length(); j++) {
			int contador = j;
			Thread hilo = new Thread(() -> contarVocales(frase.charAt(contador)));
			ths.add(hilo);
		}
		
		for (Thread thread : ths) {
			thread.start();
		}
		for (Thread thread : ths) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(contador);
		entrada.close();
	}
	
}
