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
	
	public static int n;

	
	public static void main(String[] args) throws InterruptedException {
		
		n = 10;
		
		Thread th1 = new Thread(new Runnable() {
			@Override
			public void run() {
				n += 1;
				System.out.println(n);
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		th1.start();
		
		new Thread(() -> n-= 1).start();
		System.out.println(n);
		
	} 	
}
