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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class ejercicios {

	public static List<String[]> leerFichero(Path path) throws FileNotFoundException, IOException {
		
		List<String[]> filas = new ArrayList<String[]>();
		try (Stream<String> lines = Files.lines(path)) {
			filas = lines
					.skip(1)
					.map(line -> line.split(";"))
					.collect(Collectors.toList());
		}
		
		return filas;
	}
	
	/*
	 * Ejercicio 1 -> Sacar el nombre de todos
	 */
	public static void ejercicio1 (List<String[]> file) {
		file.parallelStream()
				.skip(1)
				.map(p -> p[0])
				.forEach(p -> System.out.println(p));
	}
	
	/*
	 * Ejercicio 2 -> Sacar el nombre segun el sexo
	 */
	public static void ejercicio2(List<String[]> file , String sex) throws FileNotFoundException, IOException {
		List<String> filas = new ArrayList<>();
		
			filas = file.parallelStream()
					.skip(1)
					.filter(col -> col[1].startsWith(sex))
					.map (col -> col[0])
					.collect(Collectors.toList());
			
			for (String string : filas) {
				System.out.println(string);
			}
	}
	
	/*
	 * Ejercicio 3 -> Devolver todas las localizaciones sin repetir y ordenadas de forma ascendente
	 */
	public static void ejercicio3 (List<String[]> file) {
		file.parallelStream()
			.skip(1)
			.map (col -> col[5])
			.distinct()
			.sorted((o1 ,o2) -> o1.compareTo(o2))
			.forEach(p -> System.out.println(p));
	}
	
	/*
	 * Ejercicio 4 -> imprimir nombre y año de nacimiento de todos los Europeos que nacieron antes del año 0
	 * 
	 * DUDA -> SI NO LO HAGO CON EL "-" COMO ? 
	 */
	public static void ejercicio4 (List<String[]> file) {
		file.parallelStream()
			.skip(1)
			.filter(col -> col[2].startsWith("-"))
			.forEach(p -> {
				System.out.println(p[0] + " " + p[2]);
			});
	}
	
	/*
	 * Ejercicio 5 -> suma total de visitas de todos
	 */
	public static void ejercicio5 (List<String[]> file) {
		int numero = file.parallelStream()
			.skip(1)
			.map(m -> m[11])
			.map(Integer::valueOf)
			.reduce(0 , (sum, p) -> sum += p , (sum1,sum2) -> sum1 + sum2);
		
		System.out.println(numero);
	}
	
	/*
	 * Ejercicio 6 -> Valor maximo de popularidad dada una ocupacion dada por parametro
	 */
	public static void ejercicio6 (List<String[]> file, String ocupacion) {
		file.parallelStream()
			.skip(1)
			.filter(p -> p.equals(ocupacion))
			.map(m -> m[12]);
	}
	
	/*
	 * Ejercicio 7 -> Nombre y popularidad del más popular
	 */
	public static void ejercicio7 (List<String[]> file) {
		List<Integer> popuL = file.parallelStream()
			.skip(1)
			.map (c -> c[12])
			.map(Integer::valueOf)
			.collect(Collectors.toList());
		
		Integer popu = popuL.stream()
				.max(Comparator.comparing(Integer::valueOf)).get();

		Stream<String[]> name = file.parallelStream()
				.skip(1)
				.filter(p -> p[12].equals(popu));
				
		System.out.println("Name: " + name + " Popularidad: " + popuL);
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Path path = Paths.get("database.csv");
		List<String[]> fichero = leerFichero(path);
		
		//ejercicio1(fichero);
		//ejercicio2(fichero , "F");
		//ejercicio3 (fichero);
		//ejercicio4 (fichero);
		//ejercicio5 (fichero); -> Ver como funciona bien
		//ejercicio6(fichero, "Philosopher"); -> MAL
		ejercicio7(fichero);
	}
	
}
