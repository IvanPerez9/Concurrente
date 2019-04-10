/**
 * 
 */
package RepasoT3;

import java.util.concurrent.Semaphore;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class FilosofoClase extends Thread {

	// Cada filosofo será un hilo
	
	private int numFilosofo;
	private int palilloIzq;
	private int palilloDerch;
	private Semaphore[] semPalillos;
	
	private void sleep(int bound) {
        try {
            Thread.sleep(bound);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

	public FilosofoClase(int numFilosofo, Semaphore[] semPalillos , String nombre) {
		super(nombre);
		this.numFilosofo = numFilosofo;
		this.palilloIzq = numFilosofo;
		this.palilloDerch = (numFilosofo +1) % semPalillos.length;
		this.semPalillos = semPalillos;
	}
	
	public FilosofoClase(int numFilosofo, Semaphore[] semPalillos) {
		this.numFilosofo = numFilosofo;
		this.palilloIzq = numFilosofo;
		this.palilloDerch = (numFilosofo +1) % semPalillos.length;
		this.semPalillos = semPalillos;
	}
	
	private void comer() throws InterruptedException {
		semPalillos[palilloIzq].acquire();
		semPalillos[palilloDerch].acquire();
		System.out.println("Filosofo "+(numFilosofo+1)+" comiendo");
		sleep(1500);
		System.out.println("Filosofo "+(numFilosofo+1)+" terminado");
		semPalillos[palilloIzq].release();
		semPalillos[palilloDerch].release();
	}
	
	private void think() {
        System.out.println("Philosopher "+(numFilosofo+1)+" thinking");
        sleep(1500);
    }
	
	@Override
    public void run() {
        try {
            while(true) {
                comer();
                think();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	
}
