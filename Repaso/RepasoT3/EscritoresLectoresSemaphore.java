/**
 * 
 */
package RepasoT3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class EscritoresLectoresSemaphore {

	private static Semaphore resourceSem;
	private static Semaphore lectoresSem; // No leer si hay escritores escribiendo, u otro lector
	
	private static int contadorLectores;
	
	public static void escritor () {
		try {
			while(true) {
				resourceSem.acquire();
				System.err.println("Escritor escribe");
				Thread.sleep(200);
				resourceSem.release();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Cojo el recurso y leo, mientras leo, EM para que solo lea 1 lector, y no varios a la vez
	 */
	public static void lector() {
		try {
			while(true) {
				lectoresSem.acquire();
				contadorLectores++;
				if (contadorLectores == 1) { // Es el primero
					resourceSem.acquire();
				}
				lectoresSem.release();
				
				System.out.println("Leyendo");
				Thread.sleep(1000);
				// Liberar el recurso
				
				lectoresSem.acquire();
				contadorLectores--;
				if (contadorLectores == 0) {
					resourceSem.release();
				}
				lectoresSem.release();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		resourceSem = new Semaphore(1);
		lectoresSem = new Semaphore(1);
		
		List<Thread> ths = new ArrayList<>();
		
		Thread escritor1 = new Thread(() -> escritor());
		Thread escritor2 = new Thread(() -> escritor());
		Thread lector1 = new Thread(() -> lector());
		Thread lector2 = new Thread(() -> lector());
		
		ths.add(escritor1);
		ths.add(escritor2);
		ths.add(lector2);
		ths.add(lector1);
		
		for (Thread t : ths) {
			t.start();
		}
		
		for (Thread thread : ths) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * private static Semaphore resourceSem;
    private static Semaphore readerSem;
    private static int readerCount;

    private static void sleep(int bound) {
        try {
            Thread.sleep(bound);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void writer() {
        try {
            while (true) {
                resourceSem.acquire();
                System.out.println("Escritor escribiendo");
                sleep(1000);
                resourceSem.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void reader() {
        try {
            while (true) {
                readerSem.acquire();
                readerCount++;
                if (readerCount == 1) {
                    // Es el primer lector, cojo el recurso
                    resourceSem.acquire();
                }
                readerSem.release();

                System.out.println("Lector leyendo");
                sleep(1000);

                readerSem.acquire();
                readerCount--;
                if (readerCount==0) {
                    // Soy el último lector, libero el recurso
                    resourceSem.release();
                }
                readerSem.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        readerSem = new Semaphore(1);
        resourceSem = new Semaphore(1);
        List<Thread> ths = new ArrayList<Thread>();
        ths.add(new Thread(() -> reader(), "Lector 1"));
        ths.add(new Thread(() -> reader(), "Lector 2"));
        ths.add(new Thread(() -> writer(), "Escritor 1"));
        ths.add(new Thread(() -> writer(), "Escritor 2"));
        // Arrancar hilos
        for (int i = 0; i < ths.size(); i++) {
            ths.get(i).start();
        }
        // Esperar a que todos los hilos hayan terminado
        for (int i = 0; i < ths.size(); i++) {
            try {
                ths.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
	 */
	
}
