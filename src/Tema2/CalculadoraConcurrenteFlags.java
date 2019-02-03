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
public class CalculadoraConcurrenteFlags {

	// Ahora con flags. sincronizacion condicional, espera a que ocurra una condicion
	// Flag para el writter solo
	
	// Un hilo para escribir otro para operaciones
	
	public static volatile int resultado = 0;
	public static volatile boolean flag;
	
	public static void main(String[] args) {
		
		flag = false;
		
		Thread writter = new Thread(() -> {
			while (!flag) {
				// Espera
			}
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
			default:
				System.out.println("Error");
			}
			flag = true; // realizada ya la operacion 
			entrada.close();
		});
		
		try {
			calculator.start();
			writter.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
