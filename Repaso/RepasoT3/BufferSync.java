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
public class BufferSync {

    private static final int BUFFER_SIZE = 10;
    private int[] data = new int[BUFFER_SIZE];

    private int pInsert;
    private int pExtract;

    private Semaphore emPInsert;
    private Semaphore emPExtract;
    private Semaphore semNAvailable;
    private Semaphore semNProducts;

    public BufferSync() {
        for (int i = 0; i < BUFFER_SIZE; i++) {
            this.data[i] = -1;
        }
        emPExtract = new Semaphore(1);
        emPInsert = new Semaphore(1);
        semNAvailable = new Semaphore(BUFFER_SIZE); // Limite de productos que soporta
        semNProducts = new Semaphore(0); // Semaforo de que todos los productos salen y entran
    }

    public void insert(int d) {
        try {
            semNAvailable.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            emPInsert.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        data[pInsert] = d;
        pInsert = (pInsert+1) % BUFFER_SIZE;
        emPInsert.release();

        semNProducts.release();
    }

    public int extract() {
        try {
            semNProducts.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            emPExtract.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int d = data[pExtract];
        data[pExtract] = -1;
        pExtract = (pExtract+1) % BUFFER_SIZE;
        emPExtract.release();

        semNAvailable.release();
        return d;
    }
}
