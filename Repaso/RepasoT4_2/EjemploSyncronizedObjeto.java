/**
 * 
 */
package RepasoT4_2;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class EjemploSyncronizedObjeto {

	private int x = 0;
	
	public synchronized void incrementar() {
		x++;
	}
	
	public int getX() {
		return this.x;
	}
	
	public static void main(String[] args) {
        EjemploSyncronizedObjeto obj = new EjemploSyncronizedObjeto();
        for (int i = 0; i < 100; i++) {
        	obj.incrementar();
        }
        System.out.println(obj.getX());
    }
	
}
