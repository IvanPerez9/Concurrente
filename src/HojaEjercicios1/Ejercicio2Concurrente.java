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
	
	public static int contarVocales (Character c) {
		if ((c == 'a') || (c == 'e') || (c == 'i') || (c == 'o') || (c == 'u') ) {
			contador++;
		}
		return contador;
	}
	
	public static void main(String[] args) {
		Scanner entrada = new Scanner (System.in);
		StringBuilder frase = new StringBuilder(entrada.nextLine());
		
		LinkedList<Thread> ths = new LinkedList<Thread>();
		int i = 0;
		
		while (i < frase.length()) {
			Thread hilo = new Thread(() -> contarVocales(frase.charAt(i)));
			i++;
		}
		
		entrada.close();
	}
	
}
