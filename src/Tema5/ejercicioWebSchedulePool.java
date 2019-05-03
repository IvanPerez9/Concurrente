/**
 * 
 */
package Tema5;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;


/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class ejercicioWebSchedulePool {

	public static void tarea (String url) {
		String mensajeSalida = url + " ";
		try {
			Connection conex = Jsoup.connect(url);
			Response resp = conex.execute();
			mensajeSalida += resp.statusMessage();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(mensajeSalida);
	}

	
	public static void main(String[] args) {
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
		
		pool.scheduleWithFixedDelay(() -> tarea("https://www.google.com/") , 0, 5, TimeUnit.SECONDS);
		pool.scheduleWithFixedDelay(() -> tarea("https://www.noexistw94082.es/") , 0, 5, TimeUnit.SECONDS);
		pool.shutdown();
	}
}
