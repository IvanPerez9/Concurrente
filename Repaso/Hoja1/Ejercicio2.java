/**
 * 
 */
package Hoja1;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Ejercicio2 {

	/*
	 * Contar vocales concurrentemente -> Escribir por pantalla la frase
	 */
	
	private static volatile int contador = 0;
	private static ReentrantLock lock;
	
	public static void contarVocales (Character c) {
		if ((c == 'a') || (c == 'e') || (c == 'i') || (c == 'o') || (c == 'u') ) {
			lock.lock();
			contador++;;
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		Scanner entrada = new Scanner (System.in);
		lock = new ReentrantLock();
		StringBuilder frase = new StringBuilder(entrada.nextLine());
		
		LinkedList<Thread> ths = new LinkedList<Thread>();
		for (int j = 0; j < frase.length(); j++) {
			int id = j;
			Thread hilo = new Thread(() -> contarVocales(frase.charAt(id)));
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
