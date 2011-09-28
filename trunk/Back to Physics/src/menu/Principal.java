package menu;

import mx.itesm.btp.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;


public class Principal extends Activity{
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
            setContentView(mx.itesm.btp.R.layout.main);
            
    ((Button)findViewById(mx.itesm.btp.R.id.btnJuegoNuevo)).setOnClickListener(new OnClickListener() {
	        
				
				public void onClick(View v) {
					Intent intencion = new Intent(Principal.this,PantallaJuego.class);
					startActivity(intencion);
				}
			});
    
    
    
    
    ((Button)findViewById(mx.itesm.btp.R.id.btnAcercaDe)).setOnClickListener(new OnClickListener() {
        
		
				public void onClick(View v) {
					Intent intencion = new Intent(Principal.this,acercaDe.class);
					startActivity(intencion);
				}
			});
	    }

    
    
    

}