package menu;


import java.io.FileOutputStream;

import entrada.Acelerometro;

import graficas.Pantalla;



import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;


/**
 * Clase que manda llamar  y crea al layout que muestra las opciones de configuraci—n que tiene el juego
 * @author vero
 *
 */
public class Opciones extends Activity implements OnTouchListener 
{
	private int calibracionX;
	private int calibracionY;
	private int id;
	private Acelerometro acel;
	private static final int DIALOGO_CALIBRACION = 10;
	
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
                  
         ToggleButton tgBtnMusica = (ToggleButton) findViewById(mx.itesm.btp.R.id.tgBtnMusica);
         SharedPreferences preferenceMusica = getSharedPreferences("sonido", Context.MODE_PRIVATE);
         boolean escucha = preferenceMusica.getBoolean("musica", true);
		 tgBtnMusica.setChecked(escucha);
		 if (tgBtnMusica.isChecked()) {
				tgBtnMusica.setBackgroundResource(mx.itesm.btp.R.drawable.quieromusica);
			}else{
				tgBtnMusica.setBackgroundResource(mx.itesm.btp.R.drawable.noquieromusica);
			}
         tgBtnMusica.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
         {
         
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				SharedPreferences preferenceMusica = getSharedPreferences("musica", Context.MODE_PRIVATE);
				SharedPreferences.Editor editorMusica = preferenceMusica.edit();
				editorMusica.putBoolean("musica", isChecked);
				editorMusica.commit();
				if (isChecked) {
					buttonView.setBackgroundResource(mx.itesm.btp.R.drawable.quieromusica);
				}else{
				buttonView.setBackgroundResource(mx.itesm.btp.R.drawable.noquieromusica);
				}
			}
		});
         
         
         ToggleButton tgBtnSonido = (ToggleButton) findViewById(mx.itesm.btp.R.id.tgBtnSonido);
         SharedPreferences preferenceSonido = getSharedPreferences("sonido", Context.MODE_PRIVATE);
         boolean suena = preferenceSonido.getBoolean("sonido", true);
         tgBtnSonido.setChecked(suena);
         if (tgBtnSonido.isChecked()) {
				tgBtnSonido.setBackgroundResource(mx.itesm.btp.R.drawable.sisonido);
		}else{
				tgBtnSonido.setBackgroundResource(mx.itesm.btp.R.drawable.nosonido);
		}
         tgBtnSonido.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				SharedPreferences preferenceSonido = getSharedPreferences("sonido", Context.MODE_PRIVATE);
				SharedPreferences.Editor editorSonido = preferenceSonido.edit();
				editorSonido.putBoolean("sonido", isChecked);
				editorSonido.commit();
				if (isChecked) {
					buttonView.setBackgroundResource(mx.itesm.btp.R.drawable.sisonido);
				}else{
					buttonView.setBackgroundResource(mx.itesm.btp.R.drawable.nosonido);
				}
				
			}
         });
        
        
         
         ToggleButton tgBtAcelerometro = (ToggleButton) findViewById(mx.itesm.btp.R.id.tgBtAcelerometro);
         SharedPreferences preferenceAcelerometro = getSharedPreferences("acelerometro", Context.MODE_PRIVATE);
         boolean acelerometro = preferenceAcelerometro.getBoolean("acelerometro", true);
         tgBtAcelerometro.setChecked(acelerometro);
         tgBtAcelerometro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				SharedPreferences preferenceAcelerometro = getSharedPreferences("acelerometro", Context.MODE_PRIVATE);
				SharedPreferences.Editor editorAcelerometro = preferenceAcelerometro.edit();
				editorAcelerometro.putBoolean("acelerometro", isChecked);
				editorAcelerometro.commit();
			}
         });
	}
	

	/**
	 * Recibe la acci—n que realiza el usuario al presionar el boton de ayuda
	 */
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (v.getId()==mx.itesm.btp.R.id.btnReglas) {
			Intent intention = new Intent(Opciones.this, Ayuda.class);
			startActivity(intention);
			return false;
		} 	else if (v.getId()==mx.itesm.btp.R.id.btnReglas) {
			showDialog(DIALOGO_CALIBRACION);
			return false;
		}

		return true;
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialogoCalibracion=null;

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Pon tu dispositivo en un ‡ngulo c—modo");
		builder.setPositiveButton("Calibrar", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialogo, int which) {
				acel.calibracion();	
			}
		});
		builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}	
		});

		dialogoCalibracion = builder.create();
		showDialog(DIALOGO_CALIBRACION);

		return dialogoCalibracion;
	}
}
