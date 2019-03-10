/**
 * 
 */
package Tema4.EstructuraDatosConcurrente;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class EjercicioMapaEDConcurrentes {
	
	/*
	 * Crear tantos hilos como carpetas 
	 */

	private static ConcurrentMap<String,String> duplicates= new ConcurrentHashMap<String,String>();
	
	public static void findDuplicates(File root) {
		if (root.isDirectory()) {
			for (File file : root.listFiles()) {
				if (file.isDirectory()) {
					findDuplicates(file);
				} else {
					String path = duplicates.get(file.getName());
					if(path == null){
						duplicates.putIfAbsent(file.getName(), file.getAbsolutePath());
					} else {
						System.out.println("Found duplicate file: "+file.getName());
						System.out.println(" "+path);
						System.out.println(" "+file.getAbsolutePath());
					}
				}
			}
		}
	}
		
	public static void main(String[] args) {
		
		List<Thread> ths = new ArrayList<Thread> ();
		
		for (int i = 0; i < args.length; i++) {
			// Hasta el numero de carpetas ..
		}
		
		
		findDuplicates(new File("X:\\Dir"));
	}
}
