/**
 * 
 */
package RepasoT4_2;

import java.util.concurrent.CountDownLatch;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Ejercicio4_7_CountDownLatch {

	/*
	 * Diagrama de precedencia
	 * Await en los propios procesos, y countdown en lo que se activan
	 */

	private static CountDownLatch latchD = new CountDownLatch(2);
	private static CountDownLatch latchBG = new CountDownLatch(1);
	private static CountDownLatch latchH = new CountDownLatch(1);
	private static CountDownLatch latchE = new CountDownLatch(1);
	private static CountDownLatch latchC = new CountDownLatch(1);
	
	
	public static void p1() {
		try {
			System.out.println("A");
			latchD.countDown();
			latchBG.await();
			System.out.println("B");
			latchH.countDown();
			latchE.countDown();
			latchC.await();
			System.out.println("C");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void p2() {
		try {
			latchD.await();
			System.out.println("D");
			latchBG.countDown();
			latchE.await();
			System.out.println("E");
			latchC.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	public static void p3() {
		try {
			System.out.println("F");
			latchD.countDown();
			latchBG.await();
			System.out.println("G");
			latchH.await();
			System.out.println("H");
			latchE.countDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new Thread(() -> p1()).start();
		new Thread(() -> p2()).start();
		new Thread(() -> p3()).start();
	}
	
	
}
