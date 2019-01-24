/**
 * 
 */
package Introduccion;

/**
 * @date 24 ene. 2019
 * 
 * @author Iván - IvanPerez9
 *
 */
public class Introduccion2  {
	
	static int n = 10;

	public static void metodo1()  throws InterruptedException {
		Thread th1 = new Thread(new Runnable() {
			@Override
			public void run() {
				n += 1;
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
