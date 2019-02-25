/**
 * 
 */
package Tema4;

import java.util.concurrent.CountDownLatch;

/**
 * @date 25 feb. 2019
 * 
 * @author Iv�n - IvanPerez9
 *
 */
public class Ejercicio4_7 {

	/*
	 * Ejercicio de CountDownLatch , diagrama de precedencia 
	 */
	
	private static CountDownLatch latchD = new CountDownLatch(2);
	private static CountDownLatch latchBG = new CountDownLatch(1);
	private static CountDownLatch latchH = new CountDownLatch(1);
	private static CountDownLatch latchE = new CountDownLatch(1); // Solo con esperar H vale, porque viene de B
	private static CountDownLatch latchC = new CountDownLatch(1);
	
	// desde B no estoy esperando a A y desde G no espero a F, pero eso es del mismo proceso es secuencial no le afecta
	
	public static void p1 () {
		try {
			System.out.println("A");
			latchD.countDown();
			latchBG.await();
			System.out.println("B");
			latchH.countDown();
			latchE.countDown();
			latchC.await();
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
			latchE.await();
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
			latchH.await();
			System.out.println("H");
			latchE.countDown();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Thread (() -> p1()).start();
		new Thread (() -> p2()).start();
		new Thread (() -> p3()).start();
	}
	
}
