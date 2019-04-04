/**
 * 
 */
package HojaEjercicios3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class EjerciciosPersonajes {

	private List<Personajes> personajes;

	public List<Personajes> getPersonajes() {
		return personajes;
	}

	public void setPersonajes(List<Personajes> personajes) {
		this.personajes = personajes;
	}

	public EjerciciosPersonajes (String path) throws FileNotFoundException, IOException {
		this.personajes = new ArrayList<>();
		try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
			this.personajes = bf.lines()
					.skip(1)
					.map(line -> line.trim().split(";"))
					.filter(line -> line.length == 12)
					.map(col -> new Personajes (
									col[0],
									col[1],
									Integer.parseInt(col[2]),
									col[3],
									col[4],
									col[5],
									col[6],
									col[7],
									col[8],
									Integer.parseInt(col[9]),
									Integer.parseInt(col[10]),
									Integer.parseInt(col[11]),
									Integer.parseInt(col[12]))
					)
					.filter(Objects::nonNull)
					.collect(Collectors.toList());
		}
	}
	
	/*
	 * Ejercicio 1 -> Sacar el nombre de todos
	 */
	public void ejercicio1 () {
		this.personajes.parallelStream()
				.map(m -> m.getName())
				.forEach(System.out::println);
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String path = "database.csv" ;
		EjerciciosPersonajes ejPersonaje = new EjerciciosPersonajes(path);
		System.out.println(ejPersonaje.personajes);
		ejPersonaje.ejercicio1();
	}
	
	
}
