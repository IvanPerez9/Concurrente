/**
 * 
 */
package EjerciciosRepaso;

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
public class NumeroAleatoriosInterruptRepaso {

	/*
	 * Finalizacion de Hilos en Java -> Tema 4.1 (Ejercicio similar el 4.1) 
	 * 
	 * Para indicar que un hilo debe terminar cuando pueda se le llama como Thread.interrupt
	 * Libera los recursos el solo, y dejar los objetos en un estado estable
	 * 
	 * Escribir numeros aleatorios en un fichero
	 */
	
	public static void generarNumAleatorios () {
		try {
			PrintWriter escribirFichero = new PrintWriter("fichero.txt");
			while(true) {
				BigInteger aleatorio = BigInteger.probablePrime(1024, new Random());
				escribirFichero.println(aleatorio);
				if (Thread.interrupted()) {
					escribirFichero.append("Finalizando..");
					escribirFichero.close();
					return; // Importante le return vacio para poder salir del while true
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Thread th = new Thread (() -> generarNumAleatorios());
		th.start();
		
		// Le paro si pulso una tecla
		System.out.println("Pulse una tecla para interrumpir al hilo");
		Scanner entrada = new Scanner(System.in);
		entrada.nextLine();
		
		th.interrupt();
		System.out.println("Hilo interrumpido");
		entrada.close();
	}
	
}
