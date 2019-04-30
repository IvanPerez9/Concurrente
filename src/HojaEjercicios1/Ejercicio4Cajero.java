/**
 * 
 */
package HojaEjercicios1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Ejercicio4Cajero {
	
	/*
	 * Una conocida marca de grandes almacenes est� comenzando con su campa�a 
	 * de rebajas y ha habilitado una fila �nica para realizar los cobros. Modela el 
	 * sistema de asignaci�n de clientes de forma que sea capaz de gestionar una lista 
	 * de tama�o indefinido de clientes, y que cada cajero almacene cu�ntos clientes 
	 * ha procesado. Al finalizar los clientes, se deber� imprimir por pantalla los clientes 
	 * procesados por cada cajero, para saber qu� comisi�n deber� cobrar
	 */
	
	private static Queue<Integer> cola; 
	private static int [][] cajeroCliente; // Relacionar cliente con cajero
	private static final int NPROC = 10;
	private static Semaphore sem;
	private static final int NCAJEROS = 3;
	
	/*
	 * Proceso de obtener un cliente de la cola
	 */
	public static void proceso (int id , Queue<Integer> cola) throws InterruptedException {
		while (cola.size() >= 1) {
			sem.acquire();
			System.out.println("Cliente: " + cola.size() + " proceso: " + id);
			cola.remove();
			cajeroCliente[id][0] += cajeroCliente[id][0] ;
			sem.release();
			Thread.sleep(1000);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		cola = new LinkedList<>();
		sem = new Semaphore(1);
		List<Thread> lista = new ArrayList<>();
		cajeroCliente = new int [3][2] ; // posicion 0 valor, posicion 1 el id cajero
		
		for (int i = 0; i < NPROC; i++) {
			cola.add(i+1);
		}
		
		for (int i = 0; i < NCAJEROS; i++) {
			cajeroCliente[i][0] = 0;
			cajeroCliente[i][1] = i;
			
			final int id = i;
			Thread th = new Thread(() -> {
				try {
					proceso(id, cola);
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
		
		for (int i = 0; i < NCAJEROS; i++) {
			System.out.println("El cajero " + i + " ha cobrado: " + cajeroCliente[i][0] + " clientes.");
		}
		
	}
	
	
}
