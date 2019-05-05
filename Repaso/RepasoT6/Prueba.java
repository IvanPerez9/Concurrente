/**
 * 
 */
package RepasoT6;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Prueba {

	
	public static void main(String[] args) {
		Stream.of(1.0, 2.0, 3.0)
			.mapToInt(Double::intValue)
			.mapToObj(i -> "a" + i)
			.forEach(System.out::println);
		
		Supplier<Stream<String>> streamSupplier = () -> Stream.of("d2", "a2", "b1", "b3", "c")
				.filter(s -> s.startsWith("a"));
		
		streamSupplier.get().anyMatch(s -> true); // ok
		streamSupplier.get().noneMatch(s -> true); // ok
	}
}
