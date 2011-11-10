package animacion;

import exceptions.NoContextProvidedException;
import graficas.Coordenadas;
import graficas.Pantalla;

import java.util.LinkedList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


public class PlanoCartesiano extends View {
	
	private final int NUMERO_PUNTOS = 30;
	private final int MARGEN = 80;
	
	private LinkedList<Coordenadas> aereo = null;
	private LinkedList<Coordenadas> lateral = null;
	private Paint p = null;
	private Pantalla pantalla = null;
	private float tiempo = 0;
	private float maxTiempo = 0;
	private float maxYAereo = 0;
	private float maxYLateral = 0;
	private int maxPuntos = 0;
	private float relacion = 0;
	private boolean termino = false;

	public PlanoCartesiano(Context context) {
		super(context);
	}

	public PlanoCartesiano(Context context, LinkedList<Coordenadas> puntosAereos, LinkedList<Coordenadas> puntosLaterales, Pantalla pantalla, float maxT, float maxH, float maxZ) {
		super(context);
		p = new Paint();
		this.pantalla = pantalla;
		aereo = puntosAereos;
		lateral = puntosLaterales;
		maxTiempo = aereo.getLast().getX();
		maxYAereo = aereo.getLast().getY();
		maxYLateral = lateral.getLast().getY();
		relacion = maxTiempo/NUMERO_PUNTOS;
		
		
	}


	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(!termino){
			canvas.drawRGB(0, 0, 0);
			LinkedList<Coordenadas> copiaAereo = (LinkedList<Coordenadas>) aereo.clone();
			float factorX = 2, factorY=25;
			try {
				p.setARGB(200, 255, 255, 255);
				p.setStrokeWidth(3);
				canvas.drawLine(pantalla.getWidth()/2, pantalla.getHeight(), pantalla.getWidth()/2, 0, p);
				//factorX = ((pantalla.getWidth()/2)-MARGEN)/maxTiempo;
				//factorY = (pantalla.getHeight()-MARGEN)/maxYAereo;
			} catch (NoContextProvidedException e) {}
			// 30/width/2 - i/x
			Log.i("Graficas", "Draw");
			
			
			for(float i = 0; i < tiempo; i+=relacion){
				Coordenadas coor = null;
				float x = 0;
				float y = 0;
				try{
					coor = copiaAereo.poll();
					x= coor.getX()*factorX;
					y = pantalla.getHeight()-coor.getY()*factorY;
				} catch(Exception e){}
				canvas.drawCircle(x+MARGEN/2, y-MARGEN/2, 7, p);
				Log.d("Graficas", "X:"+x+"Y:"+y);
			}
			
			if(copiaAereo.size()<1){
				canvas.drawText("MaxT: "+aereo.getLast().getX(), 200, 200, p);
				p.setARGB(125, 100, 100, 100);
				try {
					
					canvas.drawLine(aereo.getFirst().getX()*factorX+MARGEN/2, 
							pantalla.getHeight()-aereo.getFirst().getY()*factorY-MARGEN/2, aereo.getLast().getX()*factorX+MARGEN/2, pantalla.getHeight()-aereo.getFirst().getY()*factorY-MARGEN/2, p);
				} catch (NoContextProvidedException e) {}
			}
			
			//if(tiempo <= relacion)
				//termino = true;
			
			tiempo+=relacion;
		}
		
		
	}





}
