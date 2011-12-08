package animacion;

import java.util.LinkedList;

import personajes.Enemigo;

import logica.Fisica;
import mx.itesm.btp.R;
import graficas.Coordenadas;
import graficas.Pantalla;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

public class Graficacion extends Activity implements Runnable {
	
	private static final int DANO = 42;
	Pantalla pantalla = null;
	boolean corriendo = true;
	PlanoCartesiano plano = null;
	
	
	/**
	 * Esta clase crea una pantalla que grafica las trayectorias
	 * @author Kenny
	 * @param savedInstanceState
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		pantalla = new Pantalla(this);
		double v = intent.getDoubleExtra("v", 0);
		double theta = intent.getDoubleExtra("theta", 45);
		double phi = intent.getDoubleExtra("phi", 45);
		double g = intent.getDoubleExtra("g", 9.81);
		float enemigoX = (float) intent.getDoubleExtra("enemigoX", 10);
		float enemigoY = (float) intent.getDoubleExtra("enemigoY", 10);

		LinkedList<Coordenadas> puntosA = Fisica.puntosAereos(v, theta, phi, g);
		LinkedList<Coordenadas> puntosL = Fisica.puntosLaterales(v, theta, phi, g);
		//plano = new PlanoCartesiano(this, puntosA, puntosL, pantalla, enemigoX, enemigoY);
		
		setContentView(plano);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Thread th = new Thread(this);
		th.start();
	}
	
	/**
	 * Refresca la pantalla
	 */
	public void run() {
		while (!plano.hasEnded()) {
			plano.postInvalidate();
			try {
				Thread.sleep(34);
			} catch (Exception e) {}
		}
		if(plano.hasEnded()){ //acabo la graficacion
			//Avisar que ya acabo
			Intent res = new Intent();
			//res.putExtra("distancia", plano.distanciaDano);
			setResult(DANO, res);
			finish();
			//this.
		}
	}
	

}
