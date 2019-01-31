/**
 * 
 */
package Tema2;

/**
 * @date 31 ene. 2019
 * 
 * @author Iván - IvanPerez9
 *
 */
public class MiCalculadoraConcurrente {

	// Hilo para hacer calculos y otro hilo que pinta por pantalla
	
		static volatile int resultado;
		
		public static void calcular(int num1,int  num2, String operacion) {
			new Thread(() -> { 
				if (operacion == "+") {
					resultado = num1 + num2;
				}else if (operacion == "-") {
					resultado = num1 - num2;
				}else if (operacion == "/") {
					resultado = num1 / num2;
				} else {
					resultado = num1 * num2;
				}
		}).start();
		}
		
		public static void writter() {
			new Thread(() -> System.out.println(resultado)).start();
		}
		
		public static void main(String[] args) {
			calcular(2,1, "+");
			writter();
		}
		
	}

	// Llamar a los thread por nombres, asi llamar luego a start y join 
