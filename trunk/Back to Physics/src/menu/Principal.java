package menu;

import graficas.Juego;
import mx.itesm.btp.*;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;


public class Principal extends Activity implements OnTouchListener{
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(mx.itesm.btp.R.layout.main);   
         Typeface font = Typeface.createFromAsset(getAssets(), "fonts/spin.otf");
         TextView texto = (TextView)findViewById(R.id.txtTitulo);
         texto.setTypeface(font);
            
         TextView botonJuegoNuevo = (TextView)findViewById(mx.itesm.btp.R.id.btnJuegoNuevo);
         botonJuegoNuevo.setTypeface(font);
         botonJuegoNuevo.setOnTouchListener(this);
         
         TextView botonAcercaDe = (TextView)findViewById(mx.itesm.btp.R.id.btnAcercaDe);
         botonAcercaDe.setTypeface(font);
         botonAcercaDe.setOnTouchListener(this);
         
         TextView botonOpciones = (TextView)findViewById(mx.itesm.btp.R.id.btnOpciones);
         botonOpciones.setTypeface(font);
         botonOpciones.setOnTouchListener(this);
         
         TextView botonContinuar = (TextView)findViewById(mx.itesm.btp.R.id.btnContinuar);
         botonContinuar.setTypeface(font);
         botonContinuar.setOnTouchListener(this);
         
         TextView botonHighscores = (TextView)findViewById(mx.itesm.btp.R.id.btnScores);
         botonHighscores.setTypeface(font);
         botonHighscores.setOnTouchListener(this);
         
    }

	@Override
	public boolean onTouch(View v, MotionEvent event) {		
		if(v.getId()==R.id.btnJuegoNuevo){
			ejecutaJuego();
		}
		else{
			if(v.getId()==R.id.btnOpciones){
				Intent intencion = new Intent(Principal.this,Opciones.class); 
				startActivity(intencion); 
				return false;
			}
			else{
				if(v.getId()==R.id.btnAcercaDe){
					Intent intencion = new Intent(Principal.this,acercaDe.class); 
					startActivity(intencion); 
					return false;
				}
			}
		}
		return false;
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			super.onDestroy();
		}
		return true;
	}

	private void ejecutaJuego() {
		
		Intent intencion = new Intent(Principal.this,Loading.class);
		startActivity(intencion);
		
		AsyncTask<Context, Integer, Intent> sync = new AsyncTask<Context, Integer, Intent>() {
			@Override
			protected Intent doInBackground(Context... params) {
				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Intent intencion = new Intent(Principal.this,PantallaJuego.class);
				return intencion;
			}
			
			@Override
			protected void onPostExecute(Intent result) {
				super.onPostExecute(result);
				startActivity(result);
			}
		}; 
		
		sync.execute(this);
	}

    
    
    
    

}