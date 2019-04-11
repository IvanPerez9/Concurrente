/**
 * 
 */
package RepasoT4_1;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Ejercicio4_1_HiloMensajes {

	/*
	 * Hilo ppal e hilo de mensajes, el ppal imprime "esperando" cada vez que comprueba que el otro no ha terminado
	 * A los 5 segundos, se cansa y lo interrumpe.
	 * 
	 * Cada hilo debe indicar su nombre cada vez que hace algo 
	 * 
	 * ACORDARSE DEL RETURN
	 */
	
	public static void threadMsg () {
		String[] mensajes = {
				"La vida es bella",
				"O no",
				"Los pajaritos cantan",
				"Y molestan"};
		for (String string : mensajes) {
			try {
				Thread.sleep(1000);
				System.out.println(string);
			} catch (InterruptedException e) {
				System.out.println("Se acabó");
				return;
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread hilo1 = new Thread(() -> threadMsg());
		hilo1.start();
		
		boolean continuar = true;
		int contador = 0;
		
		while(continuar) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (contador < 5) {
				System.out.println("Todavia esperando");
				contador++;
			} else {
				System.out.println("Cansado de esperar");
				hilo1.interrupt();
				hilo1.join();
				continuar=false;
			}
		}
		
	}
}
