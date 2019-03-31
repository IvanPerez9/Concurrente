/**
 * 
 */
package Tema6;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class ejerciciosStreams {
	
	private static final int SIZE = 10;

	public static Integer[] generateArray (int size) {
		Integer[] v = new Integer[size] ;
		Random rdn = new Random();
		for (int i = 0; i < size; i++) {
			v[i] = rdn.nextInt(size);
		}
		return v;
	}
	
	public static void main(String[] args) {
		
		Integer[] v = generateArray(SIZE);
		
		// Transformar un array a una lista
		
		List<Integer> lista = Arrays.stream(v)
				.collect(Collectors.toList());
		
		// Imprimir cada numero
		
		lista.stream()
			.forEach(a -> System.out.println("Numero: " + a));
		
		// Filtrar solo los que sean mayores de 7
		
		lista.stream()
			.filter(a -> a.intValue() > 7)
			.forEach(a -> System.out.println("Mayores que 7: " + a));
				
		// Obtener el menor numero -> Si no uso el get, se vuelve un Optional<Integer> 
		
		Integer numero = lista.stream()
			.min(Comparator.comparing(Integer::valueOf))
			.get();
		
		System.out.println("Minimo: " + numero);	
		
		// Imprimir ultimo numero
		
		Integer numeroUlti = lista.stream()
			.reduce((frist, second) -> second)
			.orElse(null);
		
		System.out.println("Ultimo: " + numeroUlti);
		
		// Obtener el primero
		
		Integer primero = lista.stream()
				.findFirst()
				.get();
		System.out.println("Primero: " + primero);
		
		// Obtener palabras que empiezen por "p"
		String[] arrayPalabras = {"Hola" , "que" , "tal" , "palabra" , "pepe" , "juan" , "dios" , "ivan" , "perez" };
		
		List<String> listaPalabras = Arrays.stream(arrayPalabras)
				.collect(Collectors.toList());
		
		listaPalabras.stream()
			.filter(p -> p.startsWith("p"))
			.forEach(s -> System.out.println("Empiezan por p: " + s));
		
//		listaPalabras.stream()
//			.filter(s -> {
//				System.out.println("Empieza con t: ");
//				return s.startsWith("t");
//			})
//			.forEach(s -> System.out.println(s));
		
		// Los que contengan la letra "a" 
		
		listaPalabras.stream()
			.filter(p -> p.contains("a"))
			.forEach(p -> System.out.println("Contiene la a: " + p));
	}
	
}
