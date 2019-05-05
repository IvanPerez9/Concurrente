/**
 * 
 */
package Hoja1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Ejercicio6 {

	/*
	 * Implementa un gestor de descarga de ficheros asíncrona. El gestor recibirá por 
	 * parámetro el número de procesos (NPROC) que se encargaran de realizar la 
	 * descarga y el tamaño del fichero a descargar (SIZE). Los fragmentos para 
	 * descargar por cada proceso serán simulados mediante el método download que 
	 * dado una longitud devuelve un array que representa un fragmento del fichero
	 * 
	 * Una vez descargados todos los fragmentos, se implementará una función print 
	 * que imprime el contenido del fichero por pantalla. 
	 */
	
	private static final int NPROC = 3;
	private static final int SIZE = 120;
	private static int contador = 0;
	
	private static Semaphore semDownload ;
	private static Semaphore semGestor;
	private static Semaphore semSalida; // Comprobar si hay algo descargando
	private static Semaphore semImprimir;
	
	
	public static int[] download (int longitud) {
		try {
			semDownload.acquire();
			int[] array = new int[longitud] ;
			for (int i = 0; i < array.length; i++) {
				array[i] = i;
			}
			semDownload.release();
			Thread.sleep(new Random().nextInt(2000));
			return array;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void gestorDescargas (int nproc , int size , int id) {
		try {
			System.out.println("Gestor: " + id);
			int longitud = size/nproc;
			int[] download = download(longitud);
			semGestor.acquire();
			contador += 1;
			if (contador == NPROC) {
				System.out.println("Ya no hay mas procesos descartgando");
				semGestor.release();
				for (int i = 0; i < NPROC; i++) {
					semSalida.release();
				}
			}
			semGestor.release();
			semSalida.acquire();
			System.out.println("Imprimir contenido de " + id);
			semImprimir.acquire();
			imprimirFichero(download);
			semImprimir.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void imprimirFichero (int[] fichero) {
		for (int i = 0; i < fichero.length; i++) {
			System.out.println(fichero[i]);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		semDownload = new Semaphore(1);
		semGestor = new Semaphore(1);
		semSalida = new Semaphore(0);
		semImprimir = new Semaphore(1);
		
		System.out.println("Main descarga");
		List<Thread> lista = new ArrayList<>();
		
		for (int i = 0; i < NPROC; i++) {
			final int id = i+1;
			Thread th = new Thread(() -> gestorDescargas(NPROC, SIZE, id));
			lista.add(th);
		}
		
		for (Thread th : lista) {
			th.start();
		}
		
		for (Thread th : lista) {
			th.join();
		}
	}
}
