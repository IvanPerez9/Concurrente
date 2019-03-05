/**
 * 
 */
package Tema4.EstructuraDatosConcurrente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @date 4 mar. 2019
 * 
 * @author Iván - IvanPerez9
 *
 */
public class SynchronizedCollectionsSample {
	
	private static List<String> l;
	
	/*
	 * Esta sincronizada cada llamada a cada metodo, pero no dentro -> Ejercicio 13 diapos
	 */

	public static void procesar (int id) {
		try {
			for (int i = 0; i < 5; i++) {
				Thread.sleep(1000);
				l.add("TH" + id + "_IT" + i);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		l = Collections.synchronizedList(new ArrayList<>());
		List<Thread> ths = new ArrayList<Thread>();
		for (int i = 0; i < 10; i++) {
			int id = i+1;
			ths.add(new Thread(() -> procesar(id)));
		}
		
		for (Thread thread : ths) {
			thread.start();
		}
		
		for (Thread thread : ths) {
			thread.join();
		}
		
		for (String s : l) {
			System.out.println(s);
		}
		
	}
	
	
}
