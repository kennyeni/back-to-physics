package animacion;

import exceptions.NoContextProvidedException;
import graficas.Coordenadas;
import graficas.Pantalla;

import java.util.LinkedList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class PlanoCartesiano extends View {
	
	private final int NUMERO_PUNTOS = 30;
	
	private LinkedList<Coordenadas> aereo = null;
	private LinkedList<Coordenadas> lateral = null;
	private Paint p = null;
	private Pantalla pantalla = null;
	private float tiempo = 0;
	private float maxTiempo = 0;
	private float maxYAereo = 0;
	private float maxYLateral = 0;
	private int maxPuntos = 0;
	private float relacion = maxTiempo/NUMERO_PUNTOS;

	public PlanoCartesiano(Context context) {
		super(context);
	}

	public PlanoCartesiano(Graficacion graficacion, LinkedList<Coordenadas> puntosAereos, LinkedList<Coordenadas> puntosLaterales, Pantalla pantalla) {
		this(graficacion);
		p = new Paint();
		aereo = puntosAereos;
		maxTiempo = aereo.getLast().getX();
		maxYAereo = aereo.getLast().getY();
		maxYLateral = lateral.getLast().getY();
		lateral = puntosLaterales;
	}


	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawRGB(0, 0, 0);
		LinkedList<Coordenadas> copiaAereo = (LinkedList<Coordenadas>) aereo.clone();
		float factorX = 1, factorY=1;
		try {
			canvas.drawLine(pantalla.getWidth()/2, pantalla.getHeight(), pantalla.getWidth()/2, 0, p);
			factorX = maxTiempo/pantalla.getWidth()/2;
			factorY = maxYAereo/pantalla.getHeight();
		} catch (NoContextProvidedException e) {}
		// 30/width/2 - i/x
		
		for(float i = 0; i < tiempo; i+=relacion){
			Coordenadas coor = copiaAereo.poll();
			float x = coor.getX()*factorX;
			float y = coor.getY()*factorY;
			canvas.drawCircle(x, y, 2, p);
		}
		
		tiempo+=relacion;
		
	}





}
