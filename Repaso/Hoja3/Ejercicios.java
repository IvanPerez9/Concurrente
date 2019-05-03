/**
 * 
 */
package Hoja3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Ejercicios {

	private List<Objeto> personajes; // Lista donde voy a guardar cada linea que lea
	
	public Ejercicios(String path) throws FileNotFoundException, IOException {
		personajes = new ArrayList<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			personajes = br.lines()
					.skip(1)
					.map(m -> m.trim().split(";"))
					.filter(p -> p.length == 13)
					.map(col ->
						new Objeto(
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
			}
	}
	
	/*
	 * Ejercicio 1 -> Mostrar el contenido de los nombres
	 */
	public void ejercicio1 () {
		this.personajes.parallelStream()
			.map(m -> m.getName())
			.forEach(System.out::println);
	}
	
	public void ejercicio2(String genero) {
		this.personajes.parallelStream()
			.map(m -> m.getSex())
			.filter(m -> m.toUpperCase().startsWith(genero))
			.forEach(System.out::println);
	}
	
	/*
	 * Localizaciones de forma ascendente
	 */
	public void ejercicio3 () {
		List<String> localizaciones = this.personajes.parallelStream()
			.map(m -> m.getContinent())
			.distinct()
			.collect(Collectors.toList());
			
		Collections.sort(localizaciones);
		for (String s : localizaciones) {
			System.out.println(s);
		}
	}
	
	public void ejercicio4() {
		this.personajes.parallelStream()
			.filter(p -> !p.getBirht().startsWith("-"))
			.filter(p -> p.getContinent().equals("Europe"))
			.forEach(p -> {
				System.out.println("Nombre: " + p.getName() + " Año: " + p.getBirht() );
			});
					
	}
	
	public void ejercicio5() {
		Double n = this.personajes.parallelStream()
			.map(m -> m.getViews())
			.reduce(0.0, Double::sum);
		
		System.out.println(n);
	}
	
	public void ejercicio6(String ocupacion) {
		Double n = this.personajes.parallelStream()
				.filter(m -> m.getOccupation().equals(ocupacion))
				.map(m -> m.getPopularity())
				.reduce(0.0, Double::max);
		
		System.out.println(n);
	}
	
	public void ejercicio7() {
		double valor = this.personajes.parallelStream()
				.map(m -> m.getPopularity())
				.reduce(0.0, Double::max);
			
		this.personajes.parallelStream()
			.filter(m -> m.getPopularity().equals(valor))
			.map(m -> m.getName())
			.forEach(System.out::println);
	}
	
	public void ejercicio8(String domain) {
		List<String> nombres = this.personajes.parallelStream()
				.filter(p -> p.getDomain().equals(domain))
				.map(p -> p.getName())
				.collect(Collectors.toList());
			
			System.out.println("En la base de datos: ");
			for (String s : nombres) {
				System.out.println(s);
			}
			System.out.println("pertenecen al dominio de " + domain);
	}
	
	public void ejercicio9 () {
		Map<String, Set<String>> mapa = this.personajes.parallelStream()
				.collect(Collectors.groupingBy(Objeto::getIndustry , Collectors.mapping(Objeto::getOccupation, Collectors.toSet())));
		
		System.out.println(mapa);
			
	}
	
	public void ejercicios10 (String att) {
		this.personajes.parallelStream()
			.filter(p -> p.getName().contains(att))
			.forEach(p -> System.out.println(p.getName()));
	}
	
	public void ejercicio11(int n) {
		this.personajes.parallelStream()
			.filter(m -> m.getName().length() == n)
			.forEach( m -> {
				System.out.println("Nombre: " + m.getName() + " Occupation: " + m.getOccupation());
			});
	}
	
	public void ejercicio13( ) {
		this.personajes.parallelStream()
			.map(m -> m.getName())
			.sorted((a,b) -> Integer.compare(a.length(), b.length()))
			.sequential()
			.forEach(System.out::println);
		
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String path = "Resources/database.csv" ;
		Ejercicios ejercicios = new Ejercicios(path);
		
		ejercicios.ejercicios10("ab");
	}
	
}
