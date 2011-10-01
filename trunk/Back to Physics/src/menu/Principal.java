package menu;

import mx.itesm.btp.*;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
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
		Intent intencion = null;
		switch (v.getId()) {
		case R.id.btnJuegoNuevo:intencion = new Intent(Principal.this,PantallaJuego.class); break;
		case R.id.btnOpciones:intencion = new Intent(Principal.this,Opciones.class); break;
		case R.id.btnAcercaDe:intencion = new Intent(Principal.this,acercaDe.class); break;
		case R.id.btnContinuar:intencion = new Intent(Principal.this,Principal.class); break; //Implementar
		case R.id.btnScores:intencion = new Intent(Principal.this, Principal.class); break;
		}
		
		startActivity(intencion);
		return false;
	}

    
    
    
    

}