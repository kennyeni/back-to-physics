package graficas;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class Juego extends View 
{
	private int x;
	private Paint p;
	private Bitmap imgicon;
	private Bitmap imgDibujin; 
	
	public Juego(Context contexto)
	{
		super (contexto);
		p= new Paint();
		
		//imgicon = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
		//imgDibujin = BitmapFactory.decodeResource(getResources(), R.drawable.dibujin);
	}

	
	
	@Override
	protected void onDraw(Canvas canvas) 
	{
		super.onDraw(canvas);
		//dibujar lo que se utilzar‡ en la pantalla
		//personaje.dibujar(canvas);
		//enemigo.dibujar(canvas);
		//marcador.dibjar(canvas)
		
		canvas.drawRGB((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256)); //borramos y pintamos el fondo de azul
		//Dibujar iMAGENES
		canvas.drawBitmap(imgicon, 0, 0, p);
		canvas.drawBitmap(imgDibujin, x, 200, p);
		
		//Dibujar pelota
		////String color = (int)(Math.random()*256)+(int)(Math.random()*256)+(int)(Math.random()*256);
		
		Integer a = (int)(Math.random()*256);
		Integer b = (int)(Math.random()*256);
		Integer c = (int)(Math.random()*256);
		String colorr = a.toString()+b.toString()+c.toString();
		//Integer colorrr= (int)colorr;
		
		
		//p.setColor(colorr.);
		canvas.drawCircle(x, (int)(Math.random()*100), (int)(Math.random()*256), p);
		x= (x+1)%canvas.getWidth();
	}



	public void leerEntrada() {
		// lectura del teclado, pantalla. //Guarda lo que el ususario hizo.
		
		
		
	}



	public void actualizar() {
		// Actualiza los valores de los objetos
		
		//if (comando==disparar) (proyectiles.agregar(new.proyectil)
		
		//proyectil.actulizar();
		//if (proectil.revisarlimites()==Salto)(preoyecti.actualizar();
		//if (personaje.colisionar(proyectil)) (personaje.quitarVidas();)
		
	}
	
}
