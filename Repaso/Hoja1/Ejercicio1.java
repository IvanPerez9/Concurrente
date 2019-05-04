/**
 * 
 */
package Hoja1;

import java.util.Arrays;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Ejercicio1 {

	/*
	 * Multiplicar matrices
	 */
	
	// Numero de columnas de la primera, igual al numero de filas de la segunda
	// Su orden, es fila de la primera, y columnas de la segunda
	// Fila 1 de la 1 , por columna1 de la 2 
	public static int[][] multiplicar (int[][] matriz1 , int[][] matriz2, int[][] salida, int fila, int col ){
		// Le paso a cada hilo 1 fila y 1 columna
		int aux = 0;
		int sum = 0;
		for (int i = 0; i < matriz2.length; i++) {
			aux = matriz1[fila][i] * matriz2[i][col] ;
			sum += aux;
		}
		salida[fila][col] = sum;
		return salida ;
	}
	
	public static void main(String[] args) {
		int[][] a = { { 1, 2, -3 }, { 4, 0, -2 } };
	    int[][] b = { { 3, 1 }, { 2, 4 }, { -1, 5 } };
	    
	    int[][] salida = new int[a.length][b[0].length] ;
	    
	    for (int i = 0; i < salida.length; i++) {
			int x = i;
			for (int j = 0; j < salida.length; j++) {
				int y = j;
				new Thread(() -> multiplicar(a, b, salida, x, y));
			}
		}
	    
	    System.out.println(Arrays.deepToString(salida));
	}
	
}
