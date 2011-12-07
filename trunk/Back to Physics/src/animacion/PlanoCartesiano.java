package animacion;

import exceptions.NoContextProvidedException;
import graficas.Coordenadas;
import graficas.Pantalla;

import java.text.DecimalFormat;
import java.util.LinkedList;

import mx.itesm.btp.R;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Clase que genera un view que grafica respecto a 2 planos
 * 
 * @author Kenny
 * 
 */
public class PlanoCartesiano extends View {

	private static final float NUMERO_PUNTOS = 50;
	private static final float CORRECCION_ANGULAR = (float) (5 * (Math.PI / 180));
	private static final int MARGEN = 30;

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
	public boolean termino = false;
	private float distanciaDano;
	private boolean modoNyan;
	private double phi, theta;

	/**
	 * Constructor principal (y único)
	 * 
	 * @param context
	 * @param puntosAereos
	 * @param puntosLaterales
	 * @param pantalla
	 * @param enemigoY
	 * @param enemigoX
	 * @param modoNyan 
	 */

	public PlanoCartesiano(Context context,
			LinkedList<Coordenadas> puntosAereos,
			LinkedList<Coordenadas> puntosLaterales, double theta, double phi, Pantalla pantalla,
			float enemigoX, float enemigoY, boolean modoNyan) {
		super(context);
		this.modoNyan = modoNyan;
		p = new Paint();
		this.theta = theta;
		this.phi = phi;
		this.pantalla = pantalla;
		this.enemigoX = enemigoX;
		this.enemigoY = enemigoY;
		aereo = puntosAereos;
		lateral = puntosLaterales;
		try {
			width = (float) pantalla.getWidth();
			height = (float) pantalla.getHeight();
		} catch (NoContextProvidedException e) {
		}

		generarRelaciones();

	}

	@Override
	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);
		canvas.drawRGB(0, 0, 0);
		LinkedList<Coordenadas> copiaAereo = (LinkedList<Coordenadas>) aereo.clone();
		LinkedList<Coordenadas> copiaLateral = (LinkedList<Coordenadas>) lateral.clone();
		p.setARGB(200, 255, 255, 255);
		// ////////////////////////////////////////
		// /////////// Dibuja Puntos /////////////
		// ////////////////////////////////////////
		for (float i = 0; i < tiempo; i++) { // Aereo
			Coordenadas coor = copiaAereo.poll();
			float x = coor.getX() / relacionAVAereoX;
			float y = coor.getY() / relacionAVAereoY;
			if(modoNyan)
				p.setARGB(200, 251, 0, 0);
			else
				p.setARGB(200, 255, 255, 255);
			canvas.drawCircle(x + (width / 4) + MARGEN, height - y - MARGEN, 7, p);
			Log.i("Graficas Aereos", "X:" + x + "Y:" + y);

			coor = copiaLateral.poll();
			x = coor.getX() / relacionAVLateralX;
			y = coor.getY() / relacionAVLateralY;
			if(modoNyan){
				p.setARGB(200, 0, 36, 87);
				canvas.drawCircle(x + (2 * width / 4) + MARGEN, height - y - MARGEN+ 35, 7, p);
				p.setARGB(200, 8, 129, 255);
				canvas.drawCircle(x + (2 * width / 4) + MARGEN, height - y - MARGEN+ 28, 7, p);
				p.setARGB(200, 59, 255, 0);
				canvas.drawCircle(x + (2 * width / 4) + MARGEN, height - y - MARGEN+ 21, 7, p);
				p.setARGB(200, 255, 255, 0);
				canvas.drawCircle(x + (2 * width / 4) + MARGEN, height - y - MARGEN+ 14, 7, p);
				p.setARGB(200, 253, 136, 0);
				canvas.drawCircle(x + (2 * width / 4) + MARGEN, height - y - MARGEN+ 7, 7, p);
				p.setARGB(200, 251, 0, 0);
			}
			else{
				p.setARGB(200, 255, 255, 255);
			}
			canvas.drawCircle(x + (2 * width / 4) + MARGEN,height - y - MARGEN, 7, p);
			Log.i("Graficas Lateral", "X:" + x + "Y:" + y);

		}

		// ////////////////////////////////////////
		// /////////// Dibuja enemigo /////////////
		// ////////////////////////////////////////
		p.setARGB(200, 90, 0, 60);

		// Enemigo aereo (izquierda)
		float posEnemigoAereoX = (float) ((enemigoX / relacionAVAereoX)
				+ (width / 4) + MARGEN);
		float posEnemigoAereoY = (float) (height - (enemigoY)
				/ relacionAVAereoY)
				- MARGEN;
		canvas.drawCircle(posEnemigoAereoX, posEnemigoAereoY, 10, p);

		// Enemigo lateral (derecha)
		float anguloBeta = (float) Math.atan(aereo.getLast().getY()
				/ aereo.getLast().getX()); // Angulo que forma el tiro respecto
											// al eje
		float anguloAlpha = (float) Math.atan(enemigoY / enemigoX); // Angulo
																	// que forma
																	// el
																	// enemigo
																	// respecto
																	// al eje

		if (anguloAlpha > anguloBeta) { // Decide si vale la pena mostrar al
										// enemigo
			float distRelacional = (float) (aereo.getLast().getX() - enemigoX);
			float distanciaX = ((anguloBeta) * (distRelacional))
					/ (anguloAlpha);
			float posEnemigoLateralX = ((lateral.getLast().getX() - distanciaX) / relacionAVLateralX)
					+ (2 * width / 4) + MARGEN;
			float posEnemigoLateralY = height - MARGEN;
			canvas.drawCircle(posEnemigoLateralX, posEnemigoLateralY, 10, p);
		}

		// ////////////////////////////////////////
		// /////////// Linea Divisora /////////////
		// ////////////////////////////////////////
		p.setARGB(200, 255, 255, 255);
		p.setStrokeWidth(3);
		canvas.drawLine(width / 2, height, width / 2, 0, p);
		p.setStyle(Style.STROKE);
		p.setPathEffect(new DashPathEffect(new float[] { 15, 5 }, 0));// Divisora
																		// principal
		canvas.drawLine(0, MARGEN, width, MARGEN, p);
		p.setPathEffect(new DashPathEffect(new float[] { 3, 2 }, 0));// Lateral
		canvas.drawLine(MARGEN + width / 4, height - MARGEN,
				MARGEN + width / 4, MARGEN, p);
		p.reset();

		// ////////////////////////////////////////
		// ///////// Valores de control ///////////
		// ////////////////////////////////////////
		Bitmap ojo = BitmapFactory.decodeResource(getResources(),
				mx.itesm.btp.R.drawable.icono_ojo);
		// canvas.drawBitmap(ojo,
		// (enemigoX/2)/relacionAVAereoX+(width/4)+MARGEN+10 ,
		// height-(enemigoY/2)/relacionAVAereoY-10-MARGEN, p);
		p.setARGB(200, 255, 255, 255);
		DecimalFormat dec = new DecimalFormat("###.##");
		p.setTextSize(17);
		Typeface font = Typeface.createFromAsset(getContext().getAssets(),
				"fonts/spin.otf");
		p.setTypeface(font);
		canvas.drawText("t: " + dec.format(lateral.getLast().getX()), 10, 15, p);
		canvas.drawText("\"Vista Aerea\"", (width / 4) - 26, 15, p);
		float angulo = (float) Math.atan(aereo.getLast().getY()
				/ aereo.getLast().getX());
		canvas.drawText("θ: " + dec.format(theta),
				(width / 2) - 83, 15, p);

		canvas.drawText(
				"hMax: "
						+ dec.format(lateral.get((int) (NUMERO_PUNTOS / 2) + 1)
								.getY()), (width / 2) + 10, 15, p);
		canvas.drawText("\"Vista Lateral\"", (3 * width / 4) - 36, 15, p);
		float angulo2 = (float) Math.atan(lateral.get(20).getY()
				/ aereo.get(20).getX());
		canvas.drawText("φ: " + dec.format(phi),
				(width) - 83, 15, p);

		// ////////////////////////////////////////
		// ////////// Post-Graficación ////////////
		// ////////////////////////////////////////
		if (tiempo < NUMERO_PUNTOS + 1) {
			tiempo += 1;
			termino = false;
		} else {

			p.setStrokeWidth(2);
			float comienzaX = (width / 4) + MARGEN;
			float comienzaY = height - MARGEN;
			float finalX = (aereo.getLast().getX()) / relacionAVAereoX
					+ (width / 4) + MARGEN;
			float finalY = height - (aereo.getLast().getY()) / relacionAVAereoY
					- MARGEN;
			canvas.drawLine(comienzaX, comienzaY, finalX, comienzaY, p);
			canvas.drawLine(finalX, comienzaY, finalX, finalY, p);
			// Regresar distancia neta
			distanciaDano = (float) Math.abs(Math.hypot(aereo.getLast().getX()
					- enemigoX, aereo.getLast().getY() - enemigoY));
		}

	}


	public boolean hasEnded() {
		return (tiempo >= NUMERO_PUNTOS + 1)&&termino;
	}

	/**
	 * @return the distanciaDano
	 */
	public float getDistanciaDano() {
		return distanciaDano;
	}

	public void regenerar(LinkedList<Coordenadas> puntosA,
			LinkedList<Coordenadas> puntosL, double theta, double phi, float enemigoX, float enemigoY, boolean nyan) {
		this.enemigoX = enemigoX;
		this.enemigoY = enemigoY;
		this.theta = theta;
		this.phi = phi;
		aereo = puntosA;
		lateral = puntosL;
		generarRelaciones();
		tiempo = 0;
		termino = false;
		modoNyan = nyan;

	}

	private void generarRelaciones() {
		Coordenadas maxAereo = aereo.getLast();
		Coordenadas maxLateral = lateral.getLast();
		relacionAVAereoY = (float) ((enemigoY > maxAereo.getY() ? (((enemigoY) * 1.1) / (height - MARGEN * 2))
				: ((maxAereo.getY() * 1.1) / (height - MARGEN * 2))));
		relacionAVAereoX = (float) ((enemigoX > maxAereo.getX() ? (enemigoX * 1.1)
				/ ((width / 4) - MARGEN * 2)
				: ((maxAereo.getX() * 1.1) / ((width / 4) - MARGEN * 2))));

		relacionAVLateralX = (float) ((enemigoX) > maxLateral.getX() ? ((enemigoX * 1.1) / ((width / 2) - MARGEN * 2))
				: ((maxLateral.getX() * 1.1) / ((width / 2) - MARGEN * 2)));
		int lugar = Math.round(lateral.size() / 2);
		float tmp = (lateral.get(lugar)).getY();
		termino = false;
		relacionAVLateralY = (float) ((tmp * 1.1) / (height - MARGEN * 2));
	}

}
