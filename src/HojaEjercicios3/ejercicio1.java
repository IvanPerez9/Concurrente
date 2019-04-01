/**
 * 
 */
package HojaEjercicios3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class ejercicio1 {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Path path = Paths.get("database.csv");
		List<String> filas = new ArrayList<String>();
		try (Stream<String> lines = Files.lines(path)) {
			filas = lines
					.skip(1)
					.map(line -> line.split(";"))
					.map (col -> col[1])
					.filter(c -> c.startsWith("M"))
					
					.collect(Collectors.toList());
			
			for (String string : filas) {
				System.out.println(string);
			}
			
			// Lee todo
//			List<List<String>> valores = lines
//					.map(line -> Arrays.asList(line.split(";")))
//					.collect(Collectors.toList());
//			
//			valores.forEach(valor -> System.out.println(valor));
			
			//Lee primera linea
//			columnas = lines
//					.parallel()
//					.findFirst()
//					.map(p -> Arrays.asList(p.split(";")))
//					.get();
//			
//			for (String string : columnas) {
//				System.out.println(string);
//			}
		}
	}
	
}
