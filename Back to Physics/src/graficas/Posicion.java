package graficas;


/**
 * Esta clase obtiene las posiciones de X y Y
 * @author vero
 *
 */

public class Posicion {
	public int x;
	public int y;
	
	/**
	 * Se asignan valores iniciales para X y Y
	 */
	public Posicion(){
		x=0;
		y=0;
	}
	
	/**
	 *Se re-asignan los valores a las variables X  y y
	 * @param x
	 * @param y
	 */
	public Posicion(int x, int y){
		this.x=x;
		this.y=y;
	}

}
