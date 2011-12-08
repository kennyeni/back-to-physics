package personajes;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import exceptions.NoContextProvidedException;
import graficas.Pantalla;
import graficas.Posicion;

public class Enemigo {
	private Bitmap grafico;
	private Posicion pos = null;
	private Pantalla pantalla;
	private float vidaMax =100;
	private float vida=100;
	
	/**
	 * Esta calse es la creación del fondo de los niveles.
	 * @param idAImagen
	 * @param pantalla
	 * @param resources
	 */
	
	
	/**
	 * Este método  dibuja el fondo con la imagen determinada
	 * @param idAImagen
	 * @param vida 
	 * @param pantalla
	 * @param resources
	 */
	public Enemigo(int idAImagen, float vida, Pantalla pantalla, Resources resources){
		grafico = BitmapFactory.decodeResource(resources, idAImagen); // Se crea el grafico con la imagen de parametro
		this.pantalla = pantalla;
		this.vida = vidaMax = -5;
		pos = new Posicion();
		pos.x = 10;
		pos.y = 10;
		
	}
	
	/**
	 * Obtine la imagen que se selecciono para el fondo
	 * @return fondo
	 */
	public Bitmap getBitmap(){ //regresa el fondo cortado a la medida y pos
		return grafico;
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
	public void setX(int x){
		this.pos.x=x;
	}
	
	public void setY(int y){
		this.pos.y=y;
	}
	
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

	/**
	 * Se baja vida del enemigo proporcional
	 * @param poderDeProyectil
	 * @param distanciaParaDano
	 */
	public void  bajarVida(float poderDeProyectil, float distanciaParaDano) {
		vida -= poderDeProyectil/distanciaParaDano;
		
	}

	/**
	 * Indica si el enemigo esta muerto
	 * @return
	 */
	public boolean isDead() {
		// TODO Auto-generated method stub
		return (vida<=0);
	}
	

}
