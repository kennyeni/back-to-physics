package graficas;

import java.util.ArrayList;

/**
 * Esta clase crea un objeto de coordenadas (x,y)
 * @author vero
 *
 */

public class Coordenadas {
	
	private float X,Y;
	
	/**
	 * A este método nada más se le asignan valores a X y Y
	 */
	public Coordenadas(){
		this.X=0;
		this.Y=0;
	}
	
	/**
	 * Este método recibe nuevos valores para X y Y
	 * @param X
	 * @param Y
	 */
	public Coordenadas(float x2, float y2){
		this.X=x2;
		this.Y=y2;
	}
	
	/**
	 * Obtine el valor de X
	 * @return x
	 */
	public float getX(){
		return X;
	}
	
	/**
	 * Obtine el valor de Y
	 * @return y
	 */
	
	public float getY(){
		return Y;
	}
	
	/**
	 * Clase que regresa un arreglo de X y Y
	 * @return pos[]
	 */
	
	public float[] getPos(){
		float pos[] = {X,Y};
		return pos;
	}

}
