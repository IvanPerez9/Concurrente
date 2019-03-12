/**
 * 
 */
package Tema5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class ejercicioTareasInvoke {

	/*
	 * Comparar resultados de invokeAll e invokeAny
	 * 
	 * Invocar 1000 tareas, devolviendo un string. Y ejecutarlas con ambos metodos
	 */
	
	public static final int VALOR = 1000;
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newCachedThreadPool();
		
		List<Callable<String>> lista = new ArrayList<>();
		for (int i = 0; i <VALOR; i++) {
			int id = i+1;
			lista.add(() -> "TASK " + id  );
		}
		
		// Hasta aqui lista de tareas, ahora las ejecuto
		
		List<Future<String>> tareas = pool.invokeAll(lista);
		for (Future<String> future : tareas) {
			System.out.println(future.get());
		}
		
		pool.shutdown();
		
	}
	
}
