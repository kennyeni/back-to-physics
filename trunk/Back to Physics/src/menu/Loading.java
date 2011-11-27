package menu;

import mx.itesm.btp.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

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
		TextView texto = (TextView) findViewById(R.id.textoSabias);
		ProgressBar barra = (ProgressBar) findViewById(R.id.progressBar1);
		
		int res = (int) Math.round(Math.random()*8);
		String[] texto2 = getResources().getStringArray(R.array.txtSabiasque);
		texto.setText(texto2[res]);
		
		
	}

}
