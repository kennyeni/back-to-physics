package menu;

import mx.itesm.btp.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;

/**
 * Clase que manda llamar  y crea al layout que muestra la pantalla se "sabias qué" mientras se carga el juego 
 *  * @author vero
 *
 */
public class Loading extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.loading);
		ProgressBar barra = (ProgressBar) findViewById(R.id.progressBar1);
		
		
	}

}
