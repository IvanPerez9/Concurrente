/**
 * 
 */
package Tema2;

import java.util.Scanner;

/**
 * @date 31 ene. 2019
 * 
 * @author Iván - IvanPerez9
 *
 */
public class CalculadoraConcurrente {

	public static volatile int resultado = 0; // Volatile para poder acceder desde distintos hilos 
	
	public static void main(String[] args) {
		
		Thread writter = new Thread(() -> {
			System.out.println("Resultado: " + resultado);
		});
		
		Thread calculator = new Thread(() ->  {
			Scanner entrada = new Scanner(System.in);
			int num1 = Integer.parseInt(entrada.nextLine());
			int num2 = Integer.parseInt(entrada.nextLine());
			String operador = entrada.nextLine();
			
			switch (operador) {
			case "+":
				resultado = num1 + num2;
				break;
			case "-":
				resultado = num1 - num2;
				break;
			case "*":
				resultado = num1 * num2;
				break;
			case "/":
				resultado = num1 / num2;
				break;
			}
			entrada.close();
		});
		
		try {
			calculator.start();
			calculator.join(); // El siguiente thread (writter) esper que calculator esté muerto
			writter.start();
			writter.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
}
