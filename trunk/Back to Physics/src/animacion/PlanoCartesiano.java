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


/**
 * Clase que genera un view que grafica respecto a 2 planos
 * @author Kenny
 *
 */
public class PlanoCartesiano extends View {
	
	private static final float NUMERO_Puntos = 30;

	private final int MARGEN = 25;
	
	private LinkedList<Coordenadas> aereo = null;
	private LinkedList<Coordenadas> lateral = null;
	private Paint p = null;
	private Pantalla pantalla = null;
	private float tiempo = 0;
	private float relacionAVAereoY = 0;
	private float relacionAVAereoX = 0;
	private float relacionAVLateralY = 0;
	private float relacionAVLateralX = 0;
	private float width = 0;
	private float height = 0;
	private float enemigoX = 0;
	private float enemigoY = 0;
	private boolean termino = false;
	

	/**
	 * Constructor principal (y œnico)
	 * @param context
	 * @param puntosAereos
	 * @param puntosLaterales
	 * @param pantalla
	 * @param enemigoY
	 * @param enemigoX
	 */

	public PlanoCartesiano(Context context, LinkedList<Coordenadas> puntosAereos, LinkedList<Coordenadas> puntosLaterales, Pantalla pantalla, float enemigoX, float enemigoY) {
		super(context);
		p = new Paint();
		this.pantalla = pantalla;
		this.enemigoX = enemigoX;
		this.enemigoY = enemigoY;
		aereo = puntosAereos;
		lateral = puntosLaterales;
		try {
			width = (float)pantalla.getWidth();
			height  = (float)pantalla.getHeight();
			relacionAVAereoY = (float) ((enemigoY*1.1)/(height-MARGEN));
			relacionAVAereoX = (float) (aereo.getLast().getX())/((width/2)-MARGEN);//(((.75)*relacionAVAereoY)/(4/2));
			relacionAVLateralX = (float) (enemigoX*1.3)/(width/2);
			int lugar = Math.round(lateral.size()/2);
			float tmp = (lateral.get(lugar)).getY();
			relacionAVLateralY = tmp/(height-MARGEN);
		} catch (NoContextProvidedException e) {}
		
		
		
	}


	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(!termino){
			canvas.drawRGB(0, 0, 0);
			LinkedList<Coordenadas> copiaAereo = (LinkedList<Coordenadas>) aereo.clone();
			
			p.setStrokeWidth(3);
			canvas.drawLine(width/2, height, width/2, 0, p);
			
			p.setARGB(200, 90, 0, 60);
			canvas.drawCircle((width/2)-(enemigoX/relacionAVAereoX), height-(enemigoY/relacionAVAereoY), 10, p);
			canvas.drawCircle((width/4), width-MARGEN-10, 20, p);
			
			p.setARGB(200, 255, 255, 255);
			for(float i = 0; i < tiempo; i++){
				Coordenadas coor = copiaAereo.poll();
				float x= coor.getX()/relacionAVAereoX;
				float y = coor.getY()/relacionAVAereoY;
				canvas.drawCircle(x+(width/4), height-y-MARGEN, 7, p);
				Log.i("Graficas", "X:"+coor.getX()+"Y:"+coor.getY());
			}
			
			
			canvas.drawText("MaxT: "+aereo.getLast().getX(), 200, 200, p);
			canvas.drawText("MaxY: "+aereo.getLast().getY(), 200, 250, p);
			
			if(copiaAereo.size()<1){
				
				p.setARGB(125, 100, 100, 100);
					//canvas.drawLine(aereo.getFirst().getX()*relacionAVAereoX+MARGEN/2, 
						//	height-aereo.getFirst().getY()*relacionAVAereoY-MARGEN/2, aereo.getLast().getX()*relacionAVLateralX+MARGEN/2, height-aereo.getFirst().getY()*relacionAVAereoY-MARGEN/2, p);
			}
			
			
			
			if(tiempo<NUMERO_Puntos)
				tiempo+=1;
			else
				termino = false;
		}
		
		
	}





}
