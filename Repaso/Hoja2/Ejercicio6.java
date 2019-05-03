/**
 * 
 */
package Hoja2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Ejercicio6 {

	/*
	 * Contar longitud de cadenas salvo las vacias
	 */
	
	public static int contarLong (String palabra) {
		return palabra.length();
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newCachedThreadPool();
		List<Future<Integer>> lista = new ArrayList<>();
		String[] palabras = {"", "", "cse", "rox", "", "homework", "", "7", ""};
		List<Integer> aux = new ArrayList<>();
		
		for (int i = 0; i < palabras.length; i++) {
			final int id = i;
			Future<Integer> valor = pool.submit(() -> contarLong(palabras[id]));
			lista.add(valor);
		}
		
		for (Future<Integer> s : lista) {
			int valor = s.get();
			if (valor != 0) {
				aux.add(valor);
			}
		}
		
		System.out.print("[ ");
		for (Integer i : aux) {
			System.out.print(i + " ");
		}
		System.out.print("]");

		pool.shutdown();
		
	}
}
