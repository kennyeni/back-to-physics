package animacion;

import java.util.LinkedList;

import logica.Fisica;
import mx.itesm.btp.R;
import graficas.Coordenadas;
import graficas.Pantalla;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

public class Graficacion extends Activity {
	
	Pantalla pantalla = null;
	boolean corriendo = true;
	PlanoCartesiano plano = null;
	Bundle extras = getIntent().getExtras(); 
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		pantalla = new Pantalla(this);
		double v = extras.getDouble("v");
		double theta = extras.getDouble("theta");
		double phi = extras.getDouble("phi");
		double g = extras.getDouble("g");
		plano = new PlanoCartesiano(this, Fisica.puntosAereos(v, theta, phi, g), Fisica.puntosLaterales(v, theta, phi, g), pantalla);
		
		setContentView(plano);
	}
	
	
	public void run() {
		while (corriendo) {
			plano.postInvalidate();
			try {
				Thread.sleep(34);
			} catch (Exception e) {}
		}
	}
	

}
