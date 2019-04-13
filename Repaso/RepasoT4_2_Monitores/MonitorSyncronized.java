/**
 * 
 */
package RepasoT4_2_Monitores;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class MonitorSyncronized {

	/*
	 * Gestion de venta de entradas online
	 */
	
	/*
	 * IMPORTANTE ACORDARSE DE SYNCRONIZED EN LOS METODOS Y DE WAIT/NOTIFYALL
	 * Y OJO CON EL WAIT -> ACTIVACIONES INESPERADAS
	 * 					 -> Realizarlas dentro de un while siempre !!!!!!! 
	 */
	
	private static final int PRIZE = 100;
	private static int dinero ;
	private static Queue<Integer> lista;

	public MonitorSyncronized() {
		lista = new LinkedList<>();
	}
	
	public synchronized void comprar (int customer) throws InterruptedException {
		Thread.sleep(500);
		lista.add(customer);
		System.out.println("Cliente: " + customer + " ha llegado");
	}
	
	public synchronized void vender (int ventanilla) {
		while(lista.isEmpty()) {
			try {
				this.wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		int cliente = lista.poll();
		System.out.println("El cliente: " + cliente + " será atendido por la ventanilla: " + ventanilla);
		dinero += PRIZE;
		show();
		this.notifyAll();
	}
	
	public synchronized void show() {
		System.out.println("Clientes esperando");
		for (Integer i : lista) {
			System.out.println(i + " ");
		}
		System.out.println();
		System.out.println("Dinero recaudado: " + dinero);
	}
	
}
