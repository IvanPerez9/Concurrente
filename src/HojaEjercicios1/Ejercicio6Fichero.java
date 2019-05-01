/**
 * 
 */
package HojaEjercicios1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Ejercicio6Fichero {

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
	
	private static final int NPROC = 4;
	private static final int SIZE = 12;
	private static int contadorDescargas = 0;
	
	private static Semaphore semArray;
	private static Semaphore semGestor;
	private static Semaphore semCondicionSalida;
	private static Semaphore semEMImprimir;
	
	public static int[] download (int longitud) throws InterruptedException {
		semArray.acquire();
		int[] array = new int[longitud] ;
		for (int i = 0; i < longitud; i++) {
			array[i] = i;
		}
		semArray.release();
		Thread.sleep(new Random().nextInt(2000));
		return array;
	}
	
	public static void gestorDescargas (int nproc , int size ,int id ) throws InterruptedException {
		System.out.println("Gestor de descargas " + id);
		int longitud = size / nproc ;
		int[] download = download(longitud);
		
		// Ir descargando
		semGestor.acquire();
		contadorDescargas += 1;
		if (contadorDescargas == NPROC) {
			System.out.println("No quedan procesos descargandose");
			semGestor.release();
			for (int i = 0; i < NPROC; i++) {
				semCondicionSalida.release();
			}
		}
		semGestor.release();
		semCondicionSalida.acquire();
		System.out.println("Proceso " + id + " descargado");
		semEMImprimir.acquire();
		System.out.println("Fichero: " + id + " ");
		imprimir(download);
		semEMImprimir.release();
	}
	
	public static void imprimir (int [] fichero) {
		for (int i : fichero) {
			System.out.println(i);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		semArray = new Semaphore(1);
		semGestor = new Semaphore(1);
		semCondicionSalida = new Semaphore(0);
		semEMImprimir = new Semaphore(1);
		System.out.println("Inicio descarga");
		List<Thread> lista = new ArrayList<>();
		
		for (int i = 0; i < NPROC; i++) {
			final int id = i;
			Thread th = new Thread(() -> {
				try {
					gestorDescargas(NPROC, SIZE, id);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
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
