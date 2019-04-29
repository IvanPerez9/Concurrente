/**
 * 
 */
package RepasoT3;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class ProductorConsumidorBuffer {

	private static final int NPROD = 1;
    private static final int NCONS = 3;
    
    private static BufferSync buff;
    
    public static void productor() throws InterruptedException {
    	for (int i = 0; i < 20; i++) {
			Thread.sleep(200);
			buff.insert(i);
		}
    }
    
    public static void consumidor() throws InterruptedException {
    	while(true) {
    		int data = buff.extract();
    		Thread.sleep(500);
    		System.out.println("Data: " + data);
    	}
    }
    
    public static void main(String[] args) throws InterruptedException {
		buff = new BufferSync();
		List<Thread> ths = new ArrayList<>();
		for (int i = 0; i < NPROD; i++) {
			ths.add(new Thread(() -> {
				try {
					productor();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}));
		}
		
		for (int i = 0; i < NCONS; i++) {
			ths.add(new Thread(() -> {
				try {
					consumidor();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}));
		}
		
		
		for (Thread th : ths) {
			th.start();
		}
		
		for (Thread th : ths) {
			th.join();
		}
	}
	 
}
