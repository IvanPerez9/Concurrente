/**
 * 
 */
package Tema4;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class CerrarHilos {

	public static void generateNumbers() {
		try {
			PrintWriter writer = new PrintWriter("Resources/output.txt");
			while (true) {
				// numero primo aleatorio
				BigInteger prime = BigInteger.probablePrime(1024,new Random());
				writer.println(prime.toString());
				// Si aqui le pongo un sleep tengo que controlar que no me lo bloqueen mientras duerme
				if(Thread.interrupted()){ // Aqui miro si me interrumpen
					writer.append("Fin fichero");
					writer.close();
					return;
					// Return vacio para que termine de ejecutar el codigo aqui
				}
			}
		} catch (IOException e) {
			System.err.println("Exception using file");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/*
	 * Metodo interrupted, si me han interrumpido libero los recursos.
	 * Señal que le mando.
	 * 
	 * El hilo que es interrumpido es el que decide que hace cuando le interrumpen
	 */
	public static void main(String[] args) {
		Thread t = new Thread(() -> {
			generateNumbers();
		});
		t.start();
		Scanner teclado = new Scanner(System.in);
		System.out.print("Pulse ENTER para finalizar...");
		teclado.nextLine();
		t.interrupt();
		System.out.println("Hilo interrumpido.");
		teclado.close();
	}
}
