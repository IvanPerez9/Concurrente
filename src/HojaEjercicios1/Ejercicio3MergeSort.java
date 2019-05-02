/**
 * 
 */
package HojaEjercicios1;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9 TODO
 */
public class Ejercicio3MergeSort {

	private static final int SIZE = 10;
	private int[] array;
	
	public static int[] generateRandomArray(int size) {
		int[] aux = new int[size];
		Random rdn = new Random();
		for (int i = 0; i < aux.length; i++) {
			aux[i] = rdn.nextInt(size);
		}
		return aux;
	}
	
	public static void merge (int[] array , int[] izq , int[] der) {
		int i = 0;
		int j = 0;
		int k = 0;
		
		if (i < izq.length && j < der.length) {
			if (izq[i] < der[j]) {
				array[k++] = izq[i++];
			}else {
				array[k++] = der[j++];
			}
		}
		while (i < izq.length) {
			array[k++] = izq[i++];
		}
		while (j < der.length) {
			array[k++] = der[j++];
		}
	}
	
	public static void mergeSort (int[] array) {
		if (array.length < 2) {
			return ;
		}
		
		int mid = array.length / 2;
		int[] izq = Arrays.copyOfRange(array, 0, mid);
		int[] der = Arrays.copyOfRange(array, mid+1, array.length);
		
		mergeSort(izq);
		mergeSort(der);
		merge(array, izq, der);
	}
	
}
