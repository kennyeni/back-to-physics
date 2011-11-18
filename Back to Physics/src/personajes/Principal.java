package personajes;

import exceptions.NoContextProvidedException;
import graficas.Pantalla;
import graficas.Posicion;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Principal {
	
	private Bitmap grafico;
	private Posicion pos = new Posicion();
	private Pantalla pantalla;
	
	public Principal(int idAImagen, Pantalla pantalla, Resources resources){
		grafico = BitmapFactory.decodeResource(resources, idAImagen); 
		this.pantalla = pantalla;
		
	}
	public Bitmap getBitmap(){ //regresa el fondo cortado a la medida y pos
		try {
			return Bitmap.createBitmap(grafico, pos.x, pos.y, pantalla.getWidth(), pantalla.getHeight());
		} catch (NoContextProvidedException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public void setBitmap(Bitmap newprinc){
		this.grafico=newprinc;
		
		
	}

}
