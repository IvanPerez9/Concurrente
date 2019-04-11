/**
 * 
 */
package RepasoT4_1;

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
public class GeneradorNumeroPrimosInterrupt {

	/*
	 * Crear una clase que genere numeros primos y los guarde en un fichero.
	 * Hacer que le interrumpa otro hilo, pero no deje ningun numero a la mitad
	 */
	
	public static void generateNum () {
		try {
			PrintWriter ficheroSalida = new PrintWriter("Resources/ficheroSalida.txt");
			while(true) {
				BigInteger num = BigInteger.probablePrime(1024, new Random());
				ficheroSalida.println("-" + num + "\n");
				if(Thread.interrupted()) { // Sin cortar el numero primo a la mitad
					ficheroSalida.append("Fin\n");
					ficheroSalida.close();
					return;
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Thread proceso = new Thread(() -> generateNum());
		proceso.start();
		
		// Interrumpir
		Scanner entrada = new Scanner(System.in);
		System.out.println("Pulse tecla para interrumpir");
		entrada.nextLine();
		proceso.interrupt();
		System.out.println("Interrumpido");
		entrada.close();
	}
	
}
