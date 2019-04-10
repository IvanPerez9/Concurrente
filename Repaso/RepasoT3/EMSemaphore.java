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
public class EMSemaphore {

    private static Semaphore semEM;

    public static void sleep() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void p1() {
        for (int i = 0; i < 5; i++) {

            try {
                semEM.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("P1_SC1");
            sleep();
            System.out.println("P1_SC2");
            sleep();

            semEM.release();

            System.out.println("P1_SNC1");
            sleep();
            System.out.println("P1_SNC2");
            sleep();
        }
    }

    public static void p2() {
        for (int i = 0; i < 5; i++) {

            try {
                semEM.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("\t\t\tP2_SC1");
            sleep();
            System.out.println("\t\t\tP2_SC2");
            sleep();

            semEM.release();

            System.out.println("\t\t\tP2_SNC1");
            sleep();
            System.out.println("\t\t\tP2_SNC2");
            sleep();
        }
    }

    public static void main(String[] args) {
        semEM = new Semaphore(1);
        new Thread(() -> p1(), "Proceso 1").start();
        new Thread(() -> p2(), "Proceso 2").start();
    }
}