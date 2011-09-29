package menu;

import mx.itesm.btp.*;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;


public class Principal extends Activity{
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
            setContentView(mx.itesm.btp.R.layout.main);
            
            Typeface font = Typeface.createFromAsset(getAssets(), "fonts/spin.otf");
            TextView texto = (TextView)findViewById(R.id.txtTitulo);
            texto.setTypeface(font);
            
            ((TextView)findViewById(mx.itesm.btp.R.id.btnJuegoNuevo)).setTypeface(font);
            
            
            
    ((TextView)findViewById(mx.itesm.btp.R.id.btnJuegoNuevo)).setOnClickListener(new OnClickListener() {
	        
				
				public void onClick(View v) {
					Intent intencion = new Intent(Principal.this,PantallaJuego.class);
					startActivity(intencion);
				}
			});
    
    
    
    
    ((TextView)findViewById(mx.itesm.btp.R.id.btnAcercaDe)).setOnClickListener(new OnClickListener() {
        
		
				public void onClick(View v) {
					Intent intencion = new Intent(Principal.this,acercaDe.class);
					startActivity(intencion);
				}
			});
    
    
    
    ((TextView)findViewById(mx.itesm.btp.R.id.btnOpciones)).setOnClickListener(new OnClickListener() {
        
		
		public void onClick(View v) {
			Intent intencion = new Intent(Principal.this,Opciones.class);
			startActivity(intencion);
		}
	});
    
    
	    }

    
    
    
    

}