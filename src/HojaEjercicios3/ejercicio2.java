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
public class ejercicio2 {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Path path = Paths.get("database.csv");
		List<String> filas = new ArrayList<String>();
		try (Stream<String> lines = Files.lines(path)) {
			filas = lines
					.skip(1)
					.map(line -> line.split(";"))
					.filter(col -> col[1].startsWith("F"))
					.map (col -> col[0])
					.collect(Collectors.toList());
			
			for (String string : filas) {
				System.out.println(string);
			}
		}
	}
	
}
