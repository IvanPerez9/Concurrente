/**
 * 
 */
package HojaEjercicios1;

import java.util.Arrays;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Ejercicio1 {

	public static int[][] multiplicarMatriz (int[][] matriz1, int[][] matriz2) {
		int[][] matrizSalida = new int[matriz1.length][matriz2[0].length] ;
		if (matriz1[0].length == matriz2.length) { // Si las columna de 1 es igual a las filas de 2
			for (int i = 0; i < matriz1.length; i++) {
				for (int j = 0; j < matriz2[0].length; j++) {
					for (int k = 0; k < matriz1[0].length; k++) {
						matrizSalida[i][j] += matriz1[i][k] * matriz2[k][j];
					}
				}
			}
		}
		return matrizSalida;
	}
	
	public static void main(String[] args) {
		long startTime = System.nanoTime();
		int[][] a = { { 1, 2, -3 }, { 4, 0, -2 } };
	    int[][] b = { { 3, 1 }, { 2, 4 }, { -1, 5 } };
	    int[][] c = multiplicarMatriz(a, b);
	    
	    System.out.println(Arrays.deepToString(c));
	    long endTime = System.nanoTime() - startTime;
	    System.out.println("En milisegundos: " + (endTime)/1e6 );
	}
	
}
