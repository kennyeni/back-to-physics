package graficas;

import java.util.ArrayList;

public class Coordenadas {
	
	private float X,Y;
	
	public Coordenadas(){
		this.X=0;
		this.Y=0;
	}
	
	public Coordenadas(float x2, float y2){
		this.X=x2;
		this.Y=y2;
	}
	
	/**
	 * @return x
	 */
	public float getX(){
		return X;
	}
	
	/**
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
