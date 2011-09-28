package graficas;

import java.util.ArrayList;

public class Coordenadas {
	
	private float X,Y;
	
	public Coordenadas(){
		this.X=0;
		this.Y=0;
	}
	
	public Coordenadas(int X, int Y){
		this.X=X;
		this.Y=Y;
	}
	
	public float getX(){
		return X;
	}
	
	public float getY(){
		return Y;
	}
	
	public float[] getPos(){
		float pos[] = {X,Y};
		return pos;
	}

}
