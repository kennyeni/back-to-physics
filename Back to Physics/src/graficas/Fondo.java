package graficas;

import exceptions.NoContextProvidedException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Fondo {
	
	private Bitmap grafico;
	private Posicion pos;
	private Pantalla pantalla;
	
	
	public Fondo(int idAImagen, Pantalla pantalla, Resources resources){
		grafico = BitmapFactory.decodeResource(resources, idAImagen); // Se crea el grafico con la imagen de parametro
		this.pantalla = pantalla;
		try {
			pos.Y = (grafico.getHeight()/2-pantalla.getHeight()/2); //La imagen se centra
			pos.X = (grafico.getWidth()/2-pantalla.getWidth()/2);
		} catch (NoContextProvidedException e) {
			e.printStackTrace();
		}
	}
	
	public Bitmap getBitmap(){ //regresa el fondo cortado a la medida y pos
		try {
			return Bitmap.createBitmap(grafico, pos.X, pos.Y, pantalla.getWidth(), pantalla.getHeight());
		} catch (NoContextProvidedException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public boolean mueveX(int X){
		boolean exitoso;
		pos.X+=X;
		if(pos.X<grafico.getWidth())
			if(pos.X>0)
				exitoso = true;
			else
				exitoso = false;
		else{
			pos.X = grafico.getWidth();
			exitoso = false;
		}
		
		return exitoso;
	}
	
	public boolean mueveY(int Y){
		boolean exitoso;
		pos.Y+=Y;
		if(pos.Y<grafico.getHeight())
			if(pos.Y>0)
				exitoso = true;
			else
				exitoso = false;
		else{
			pos.Y = grafico.getHeight();
			exitoso = false;
		}
		
		return exitoso;
	}
	

}
