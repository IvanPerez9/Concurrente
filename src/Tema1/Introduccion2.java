/**
 * 
 */
package Tema1;

/**
 * @date 24 ene. 2019
 * 
 * @author Iván - IvanPerez9
 *
 */
public class Introduccion2  {
	
	public static volatile int x;

	
	public static void main(String[] args) throws InterruptedException {
		
		x = 10;
		
		Thread th1 = new Thread(new Runnable() {
			@Override
			public void run() {
				x += 1;
				System.out.println(x);
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		th1.start();
		
		new Thread(() -> x-= 1).start();
		System.out.println(x);
		
		System.out.println("---");
		
		otro();
		
	} 

    public static void otro() throws InterruptedException {
        Thread inc = new Thread(() -> {
            x = x + 1;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread dec = new Thread(() -> {
            x = x - 1;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        inc.start();
        dec.start();
        inc.join();
        dec.join();
        System.out.println("X = "+x);
    }
}
