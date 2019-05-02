/**
 * 
 */
package EjerciciosRepaso;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Hoja3Ejercicios {

	public static List<Hoja3Objeto> lista ;
	
	public Hoja3Ejercicios (String path) throws FileNotFoundException {
		lista = new ArrayList<>();
		
		try (BufferedReader lector = new BufferedReader(new FileReader(path))) {
			lista = lector.lines()
					.skip(1)
					.map (p -> p.trim().split(";"))
					.filter(p -> p.length == 13)
					.map (col -> new Hoja3Objeto(
							col[0],
                            col[1],
                            col[2],
                            col[3],
                            col[4],
                            col[5],
                            col[6],
                            col[7],
                            col[8],
                            Double.parseDouble(col[9]),
                            Double.parseDouble(col[10]),
                            Double.parseDouble(col[11]),
                            Double.parseDouble(col[12]))
					)
					.collect(Collectors.toList());
			
		} catch (Exception e) {
			System.err.println("Error lectura");
		}
	}
	
	/*
	 * Ejercicio1 -> nombre
	 */
	public void ejercicio1 () {
		lista.parallelStream()
			.map(m -> m.getName())
			.forEach(System.out::println);
	}
	
	// Ejercicio 2 -> Genero M o F con el nombre
	
	public void ejercicio2(String sex) {
		lista.parallelStream()
			.filter(p -> p.getSex().startsWith(sex))
			.map(m -> m.getName())
			.forEach(System.out::println);
	}
	
	//Ejercicio 3 -> Localizaciones sin repeticiones
	
	public void ejercicio3 () {
		lista.parallelStream()
			.map(m -> m.getCity())
			.distinct()
			.forEach(System.out::println);
	}

	// Ejercicio 4 -> Nacidos antes del año 0
	
	public void ejercicio4() {
		lista.parallelStream()
			.filter(p -> p.getBirht().startsWith("-"))
			.forEach(p -> {
				System.out.println(p.getName() + " Año: " + p.getBirht());
			});
	}
	
	// Ejercicio 5 -> Suma total de visitas
	
	public void ejercicio5() {
		Double suma = lista.parallelStream()
				.map(m -> m.getViews())
				.reduce(0.0 , Double::sum);
		
		System.out.println(suma);
	}
	
	// Ejercicio 6 -> Valor maximo de popularidad
	
	public void ejercicio6() {
		Double max = lista.parallelStream()
				.map(m->m.getPopularity())
				.reduce(0.0, Double::max);
		
		System.out.println(max);
	}
	
	// Ejercicio 7 -> Nombre y popu del max
	
	public void ejercicio7() {
		Double max = lista.parallelStream()
				.map(m->m.getPopularity())
				.reduce(0.0, Double::max);
		
		 lista.parallelStream()
			.filter(p-> p.getPopularity().equals(max))
			.map(m -> m.getName())
			.forEach(System.out::print);
		
		System.out.print( " popularidad: " + max);
	}
	
	// ejercicio 8 dominio
	
	public void ejercicio8(String domain) {
		List<String> nombres = lista.parallelStream()
				.filter(p -> p.getDomain().equals(domain))
				.map(p -> p.getName())
				.collect(Collectors.toList());
			
			System.out.println("En la base de datos: ");
			for (String s : nombres) {
				System.out.println(s);
			}
			System.out.println("pertenecen al dominio de " + domain);
	}
	
	// Ejercicio 9 - > por industria, que ocupaciones tienen
	
	public void ejercicio9 () {
		lista.parallelStream()
			.collect(Collectors.groupingBy(Hoja3Objeto::getIndustry , Collectors.mapping(Hoja3Objeto::getOccupation, Collectors.toSet())));
	}
	

	// Ordenar 
	
	public void ejercicio13() {
		List<String> lista2 = lista.parallelStream()
			.map(m -> m.getName())
			.sorted((a,b) -> Integer.compare(a.length(), b.length()))
			.sequential()
			.collect(Collectors.toList());
		
		Collections.reverse(lista2);
		for (String s : lista2) {
			System.out.println(s);
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Hoja3Ejercicios ejercicios = new Hoja3Ejercicios("Resources/database.csv") ;
		
		ejercicios.ejercicio13();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
