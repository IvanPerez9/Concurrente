/**
 * 
 */
package RepasoT5;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class DyVmaximoArray {

	public static int[] generateRandomArray (int size) {
		int[] array = new int[size] ;
		Random rdn = new Random();
		for (int i = 0; i < array.length; i++) {
			array[i] = rdn.nextInt(size);
		}
		return array;
	}
	
	public static int BinarySearch (int[] array , int start , int end) {
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
	
	public static void main(String[] args) {
		int[] v = generateRandomArray(10);
		int m = BinarySearch(v, 0, v.length);
		System.out.println(Arrays.toString(v));
		System.out.println(m);
	}
	
}
