/**
 * 
 */
package Tema5;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class ejercicioDivideVencerasMaximo {

	public static int BinarySearch (int[] array, int start, int end) {
		int max = Integer.MIN_VALUE;
		if (end - start <= 2) {
			if (array[0] > array[1] ) {
				max = array[0];
				return max;
			} else {
				max = array[1];
				return max;
			}
		} else {
			int mitad = (end+start) / 2 ;
			int m1 = BinarySearch(array, start, mitad);
			int m2 = BinarySearch(array, mitad+1, end);
			return Math.max(m1, m2);
		}
	}
	
	public static int[] generateRandom (int size) {
		int[] v = new int[size];
		Random rnd = new Random();
		for (int i = 0; i < v.length; i++) {
			v[i] = rnd.nextInt(size);
		}
		return v;
	}
	
	public static void main(String[] args) {
		int[] v = generateRandom(10);
		int m = BinarySearch(v, 0, v.length);
		System.out.println(Arrays.toString(v));
		System.out.println(m);
	}
	
}
