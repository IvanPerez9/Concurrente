/**
 * 
 */
package Hoja1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Ejercicio4 {

	/*
	 * Una conocida marca de grandes almacenes está comenzando con su campaña 
	 * de rebajas y ha habilitado una fila única para realizar los cobros. Modela el 
	 * sistema de asignación de clientes de forma que sea capaz de gestionar una lista 
	 * de tamaño indefinido de clientes, y que cada cajero almacene cuántos clientes 
	 * ha procesado. Al finalizar los clientes, se deberá imprimir por pantalla los clientes 
	 * procesados por cada cajero, para saber qué comisión deberá cobrar
	 */
	
	
	private static Queue<Integer> cola ;
	private static int NCLIENTES = 12;
	private static int NCAJEROS = 3;
	private static Semaphore sem;
	private static int[][] cajeroCliente; // Almacen de cajero id , con cliente id
	
	
	public static void proceso (int id, Queue<Integer> cola) {
		while(!cola.isEmpty()) {
			try {
				sem.acquire();
				System.out.println("Cliente: " + cola.size() + " cajero " + id);
				cola.remove();
				cajeroCliente[id][0] +=  1 ;
				sem.release();
				Thread.sleep(new Random().nextInt(2000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		sem = new Semaphore(1);
		cajeroCliente = new int[NCAJEROS][2] ; // Pos 0 valor, pos 1 el id
		cola = new LinkedList<>();
		List<Thread> ths = new ArrayList<>();
		
		for (int i = 0; i < NCLIENTES; i++) {
			cola.add(i+1);
		}
		
		for (int i = 0; i < NCAJEROS; i++) {
			final int id = i;
			cajeroCliente[i][0] = 0;
			cajeroCliente[i][1] = id;
			
			Thread th = new Thread(() -> proceso(id, cola));
			ths.add(th);
		}
		
		for (Thread th : ths) {
			th.start();
		}
		for (Thread th : ths) {
			th.join();
		}
		
		for (int i = 0; i < NCAJEROS; i++) {
			System.out.println("El cajero: " + i + " ha cobrado: " + cajeroCliente[i][0]+ " clientes");
		}
	}
	
}
