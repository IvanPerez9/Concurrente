/**
 * 
 */
package EjerciciosRepaso;

import java.util.concurrent.CountDownLatch;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Ejercicio4_7_Repaso {

	/*
	 * Ejercicio de CountDownLatch , diagrama de precedencia 
	 * 
	 * Los hilos tiene que esperar a que el contador este a 0 para continuar 
	 * Se bloquean con await y se deja seguir con countDown -> Tantas veces como se especifique en el constructor del objeto
	 */
	
	static CountDownLatch latchD = new CountDownLatch(2);
	static CountDownLatch latchBG = new CountDownLatch(1);
	static CountDownLatch latchC = new CountDownLatch(1);
	static CountDownLatch latchE = new CountDownLatch(1);
	static CountDownLatch latchH = new CountDownLatch(1);
	
	
	public static void p1 () {
		try {
			System.out.println("A");
			latchD.countDown();
			latchBG.await();
			System.out.println("B");
			latchC.await();
			latchE.countDown();
			latchH.countDown();
			System.out.println("C");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void p2 () {
		try {
			latchD.await();
			System.out.println("D");
			latchBG.countDown();
			latchE.await(); // el E aqui se espera a que B,G y H se ejecuten
			System.out.println("E");
			latchC.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void p3 () {
		try {
			System.out.println("F");
			latchD.countDown();
			latchBG.await();
			System.out.println("G");
			latchH.countDown();
			System.out.println("H");
			latchE.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Thread p1 = new Thread (() -> p1() );
		Thread p2 = new Thread (() -> p2() );
		Thread p3 = new Thread (() -> p3() );
		
		p1.start();
		p2.start();
		p3.start();
	}
	
}
