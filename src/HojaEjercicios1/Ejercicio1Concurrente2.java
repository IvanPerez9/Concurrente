/**
 * 
 */
package HojaEjercicios1;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Ejercicio1Concurrente2 {

	public static int[][] multiplicarMatriz (int[][] matriz1, int[][] matriz2, int[][] matrizSalida, int fila, int columna ) {
		if (matriz1[0].length == matriz2.length) { // Si las columna de 1 es igual a las filas de 2
			for (int i=0;i<matriz2.length;i++) {
				matrizSalida[fila][columna]+=matriz1[fila][i]*matriz2[i][columna];
			}
		}
		return matrizSalida;
	}
	
	public static void main(String[] args) {
		long startTime = System.nanoTime();
		int[][] a = { { 1, 2, -3 }, { 4, 0, -2 } };
	    int[][] b = { { 3, 1 }, { 2, 4 }, { -1, 5 } };
	    
	    LinkedList<Thread> ths = new LinkedList<Thread>();
	    int filas = a.length;
	    int columnas = b[0].length;
	    int [][] resultado = new int[filas][columnas];

        for (int fila=0; fila<filas; fila++) {
        	int filaFinal = fila;
            for (int columna=0; columna<columnas; columna++)
            {
            	int columnaFinal = columna;
                Thread hilo = new Thread(() ->  multiplicarMatriz(a,b,resultado,filaFinal,columnaFinal));
                ths.add(hilo);
                hilo.start();
            }
        }
        
        for (Thread th : ths) {
			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
        
        System.out.println(Arrays.deepToString(resultado));
	    
	    long endTime = System.nanoTime() - startTime;
	    System.out.println("En milisegundos: " + (endTime)/1e6 );
	}
	
}
