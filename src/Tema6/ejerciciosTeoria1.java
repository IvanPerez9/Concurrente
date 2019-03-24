/**
 * 
 */
package Tema6;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class ejerciciosTeoria1 {
	
	/*
	 *  - Siempre filter primero ahorra muchas instrucciones
	 *  - poner p -> p.metodo es lo mismo que poner su tipo basico como Integer::metodo
	 *  - Expresión lambda: interfaz funcional que especifica el comportamiento de la operación
	 *  - Si no hay una operacion final, no se ejecutan las operaciones medias
	 *  - Si hay una operacion con estado, como sorted, se deja de ejecutar verticalmente, y pasa a horizontal
	 *  - Los Streams se cierran en cuanto ejecutamos una operacion final
	 *  - Si queremos más Stream, usar un Supplier. Usar 1 stream por cada operacion final que queramos
	 */

	public static void metodo1 () {
		List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");
		myList
			.stream()
			.filter(p -> p.startsWith("c"))
			.map(e -> e.toUpperCase())     // Lo mismo que poner String::toUpperCase
			.sorted()
			.forEach(System.out::println);
	}
	
	public static void metodo2 () {
		Stream.of("d2", "a2", "b1", "b3", "c")   // Forma de declarar un Stream directo
				.filter(s -> {
					System.out.println("filter: " + s);
					return true;
				})
				.forEach(s -> System.out.println("forEach: " + s));
	}
	
	public static void metodo3 () {
		// Ejecutado horizontal con estado
		Stream.of("d2", "a2", "b1", "b3", "a1")
			.filter(s -> s.startsWith("a"))
			.sorted((s1,s2) -> s1.compareTo(s2) )
			.map ( s -> s.toUpperCase())
			.forEach(s -> System.out.println("Metodo3 : " + s));
	}
	
	public static void main(String[] args) {
		metodo1();
		metodo2();
		System.out.println("----");
		metodo3();
	}
	
}
