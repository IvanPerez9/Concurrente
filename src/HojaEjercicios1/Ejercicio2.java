/**
 * 
 */
package HojaEjercicios1;

import java.util.Scanner;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Ejercicio2 {

	/*
	 * Programa secuencial que cuente el numero de vocales en una frase
	 */
	
	public static int contarVocales (StringBuilder frase) {
		int contador = 0;
		for (int i = 0; i < frase.length(); i++) {
			if ((frase.charAt(i) == 'a') || (frase.charAt(i) == 'e') || (frase.charAt(i) == 'i') || (frase.charAt(i) == 'o') || (frase.charAt(i) == 'u') ) {
				contador++;
			}
		}
		return contador;
	}
	
	public static void main(String[] args) {
		Scanner entrada = new Scanner (System.in);
		StringBuilder frase = new StringBuilder(entrada.nextLine());
		System.out.println(contarVocales(frase));
		entrada.close();
	}
	
}
