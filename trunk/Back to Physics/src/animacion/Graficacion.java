package animacion;

import java.util.LinkedList;

import logica.Fisica;
import mx.itesm.btp.R;
import graficas.Coordenadas;
import graficas.Pantalla;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class Graficacion extends Activity implements Runnable {
	
	Pantalla pantalla = null;
	boolean corriendo = true;
	PlanoCartesiano plano = null;
	//Bundle extras = getIntent().getExtras(); 
	
	
	/**
	 * Esta clase crea una pantalla que grafica las trayectorias
	 * @author Kenny
	 * @param savedInstanceState
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		pantalla = new Pantalla(this);
		double v = 40; //extras.getDouble("v");
		double theta = 20; //extras.getDouble("theta");
		double phi = 45; //extras.getDouble("phi");
		double g = 10; //extras.getDouble("g");
		float maxT = 100 //(float) (4*(v/g)) 
				, maxH = 0, 
				maxZ = 400; //(float) (v*maxT);
		LinkedList<Coordenadas> puntosA = Fisica.puntosAereos(v, theta, phi, g);
		LinkedList<Coordenadas> puntosL = Fisica.puntosLaterales(v, theta, phi, g);
		plano = new PlanoCartesiano(this, puntosA, puntosL, pantalla, maxT, maxH, maxZ);
		
		setContentView(plano);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Thread th = new Thread(this);
		th.start();
	}
	
	
	public void run() {
		while (true) {
			plano.postInvalidate();
			try {
				Thread.sleep(34);
			} catch (Exception e) {}
		}
	}
	

}
