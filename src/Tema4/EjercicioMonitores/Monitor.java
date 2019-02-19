/**
 * 
 */
package Tema4.EjercicioMonitores;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Monitor {

	/*
	 * Gestionar la venta de entradas
	 * 
	 * Un monitor que contenga una unica lista como recurso compartido
	 * 
	 * Tres metodos en exclusion mutua:
	 * 		- Comprar : Añade una persona a la lista de solicitudes
	 * 		- Vender: Asigna la entrada y saca de la lista
	 * 		- Mostrar: Muestra el recurso y el dinero ganado
	 * 
	 * Implementar 2 clases y un progrmaa principal. Cada clase un hilo que usando el monitor compre y venda
	 * Actualizar el recurso compartido
	 */
	
	String[] listaEntradas = {"Entrada1" , "Entrada2" , "Entrada3" , "Entrada4" , "Entrada5"};
	private boolean condicion = false;
	private volatile int numeroEntrada = 0; // Esta variable ... 
	
	public synchronized String getEntrada () {
		while (!condicion) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		condicion = true;
		notifyAll();
		String entrada =listaEntradas[numeroEntrada];
		numeroEntrada++;
		return entrada;
	}
	
	public static void main(String[] args) {
	}
}
