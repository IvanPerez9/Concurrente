/**
 * 
 */
package Tema4;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class NumeroAleatoriosInterrupt {
	
	/*
	 * Crear una clase que genere numeros primos y los guarde en un fichero.
	 * Hacer que le interrumpa otro hilo, pero no deje ningun numero a la mitad
	 */

	public static void generarNumeros () {
		try {
			PrintWriter escribirFichero = new PrintWriter("Resources/salida.txt");
			while(true) {
				BigInteger numero = BigInteger.probablePrime(1024, new Random ());
				escribirFichero.println (numero.toString());
				if (Thread.interrupted()) {
					escribirFichero.append("Fin fichero");
					escribirFichero.close();
					return;
					// Importante el return vacio si no no para
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("Error al abrir el fichero");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public static void main(String[] args) {
		Thread th = new Thread(() -> generarNumeros());
		th.start();
		
		Scanner entrada = new Scanner(System.in);
		System.out.println("Pulse cualquier tecla para parar...");
		entrada.nextLine();
		
		th.interrupt();
		
		System.out.println("\nHilo interrumpido");
		entrada.close();
	}
	
}
