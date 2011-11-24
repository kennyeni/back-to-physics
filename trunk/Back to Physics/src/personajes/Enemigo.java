package personajes;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import exceptions.NoContextProvidedException;
import graficas.Pantalla;
import graficas.Posicion;

public class Enemigo {
	private Bitmap grafico;
	private Posicion pos = new Posicion();
	private Pantalla pantalla;
	
	/**
	 * Esta calse es la creaci�n del fondo de los niveles.
	 * @param idAImagen
	 * @param pantalla
	 * @param resources
	 */
	
	
	/**
	 * Este m�todo  dibuja el fondo con la imagen determinada
	 * @param idAImagen
	 * @param pantalla
	 * @param resources
	 */
	public Enemigo(int idAImagen, Pantalla pantalla, Resources resources){
		grafico = BitmapFactory.decodeResource(resources, idAImagen); // Se crea el grafico con la imagen de parametro
		this.pantalla = pantalla;
		
	}
	
	/**
	 * Obtine la imagen que se selecciono para el fondo
	 * @return fondo
	 */
	public Bitmap getBitmap(){ //regresa el fondo cortado a la medida y pos
		try {
			return Bitmap.createBitmap(grafico, pos.x, pos.y, pantalla.getWidth(), pantalla.getHeight());
		} catch (NoContextProvidedException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	/**
	 * Pinta la mira cuando se mueve la pantala en X
	 * @param X
	 * @return
	 * @throws NoContextProvidedException
	 */
	public boolean mueveX(double X) throws NoContextProvidedException{
		boolean exitoso;
		pos.x+=X;
		if(pos.x<grafico.getWidth()-pantalla.getWidth()-10)
			if(pos.x>0)
				exitoso = true;
			else{
				pos.x=0;
				exitoso = false;
			}
		else{
			pos.x = grafico.getWidth()-pantalla.getWidth()-10;
			exitoso = false;
		}
		
		return exitoso;
	}
	
	/**
	 * Pinta la mira cuando se mueve la pantala en Y
	 * @param Y
	 * @return
	 * @throws NoContextProvidedException
	 */
	
	public boolean mueveY(double Y) throws NoContextProvidedException{
		boolean exitoso;
		pos.y+=Y;
		if(pos.y<(grafico.getHeight()-pantalla.getHeight())-10)
			if(pos.y>0)
				exitoso = true;
			else{
				pos.y =0;
				exitoso = false;
			}
		else{
			pos.y = grafico.getHeight()-pantalla.getHeight()-10;
			exitoso = false;
		}
		
		return exitoso;
	}

}
