/**
 * 
 */
package Tema2;

import java.util.Scanner;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class CalculadoraConcurrenteFlags2 {

	// Ahora con flags para cada una de las operacion , y cada operacion en un hilo
	// flags tambien para el writter
	
	public static volatile int resultado1, resultado2, resultado3, resultado4 = 0;
	public static volatile boolean flag1, flag2, flag3, flag4;
	public static volatile boolean flagReader = false;
	static volatile int num1, num2;
	
	public static void main(String[] args) {
		
		flag1 = false;
		flag2 = false;
		flag3 = false;
		flag4 = false;
		
		Thread writter = new Thread(() -> {
			while (!flag1 && !flag2 && !flag3 && !flag4 ) {
				// Espera
			}
			System.out.println("Resultado Suma: " + resultado1);
			System.out.println("Resultado Resta: " + resultado2);
			System.out.println("Resultado Multiplicacion: " + resultado3);
			System.out.println("Resultado Division: " + resultado4);
		});
		
		Thread reader = new Thread(() -> {

			try {
				System.out.println("Primer operando: ");
				Scanner sc1 = new Scanner(System.in);
				num1 = sc1.nextInt();

				System.out.println("Segundo operando: ");
				Scanner sc2 = new Scanner(System.in);
				num2 = sc2.nextInt();
				
				sc1.close();
				sc2.close();

			} catch (ArithmeticException e) {
				e.printStackTrace();
			}
			
			flagReader = true;
			});
			
			Thread suma = new Thread(() -> {
				while (flagReader == false)
					; // Espera activa
				resultado1 = num1 + num2;
				flag1 = true;
			});

			Thread resta = new Thread(() -> {
				while (flagReader == false)
					; // Espera activa
				resultado2 = num1 - num2;
				flag2 = true;
			});

			Thread multiplicacion = new Thread(() -> {
				while (flagReader == false)
					; // Espera activa
				resultado3 = num1 * num2;
				flag3 = true;
			});

			Thread division = new Thread(() -> {
				while (flagReader == false)
					; // Espera activa
				resultado4 = num1 / num2;
				flag4 = true;
			});
			
		
		try {
			suma.start();
			resta.start();
			multiplicacion.start();
			division.start();
			reader.start();
			writter.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
