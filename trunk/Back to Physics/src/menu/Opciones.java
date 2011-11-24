package menu;


import graficas.Pantalla;



import android.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.TextView;


/**
 * Clase que manda llamar  y crea al layout que muestra las opciones de configuraci—n que tiene el juego
 * @author vero
 *
 */
public class Opciones extends Activity implements OnTouchListener 
{
	
	protected void onCreate(Bundle savedInstanceState)
	{
		 super.onCreate(savedInstanceState);
		 setContentView(mx.itesm.btp.R.layout.opciones);
         Typeface font = Typeface.createFromAsset(getAssets(), "fonts/spin.otf");
         
         TextView Opciones = (TextView)findViewById(mx.itesm.btp.R.id.txtOpciones);
         Opciones.setTypeface(font);
         
         TextView Sonido = (TextView)findViewById(mx.itesm.btp.R.id.txtSonido);
         Sonido.setTypeface(font);
         
         TextView Musica = (TextView)findViewById(mx.itesm.btp.R.id.txtMusica);
         Musica.setTypeface(font);
         
         TextView botonAyuda = (TextView)findViewById(mx.itesm.btp.R.id.btnReglas);
         botonAyuda.setTypeface(font);
         botonAyuda.setOnTouchListener(this);
    
		
		
	}

	/**
	 * Recibe la acci—n que realiza el usuario al presionar el boton de ayuda
	 */
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (v.getId()==mx.itesm.btp.R.id.btnReglas) {
			Intent intention = new Intent(Opciones.this, Ayuda.class);
			startActivity(intention);
		} 	return false;
	}
}
