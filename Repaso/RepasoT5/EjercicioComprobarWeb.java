/**
 * 
 */
package RepasoT5;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class EjercicioComprobarWeb {

	public static void checkWeb (String web) {
		try {
            HttpURLConnection connection = (HttpURLConnection) new URL(web).openConnection();
            connection.setRequestMethod("HEAD");
            int response = connection.getResponseCode();
            System.out.println(web + ((response != 200) ? " NOT" : "") + " AVAILABLE");
        } catch (IOException e) {
            System.out.println(web+" NOT AVAILABLE");
        }
	}
	
	public static void main(String[] args) throws InterruptedException, IOException, ExecutionException {

        ScheduledExecutorService pool = Executors.newScheduledThreadPool(4);

        pool.scheduleAtFixedRate(() -> checkWeb("https://www.inventadaasdasdasd.es"), 0, 5, TimeUnit.SECONDS);
        pool.scheduleAtFixedRate(() -> checkWeb("https://www.marca.com"), 0, 5, TimeUnit.SECONDS);
        pool.scheduleAtFixedRate(() -> checkWeb("https://www.urjc.es"), 0, 5, TimeUnit.SECONDS);
    }
}
