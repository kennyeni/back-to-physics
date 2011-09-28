package menu;

import mx.itesm.btp.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Principal extends Activity{
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
            setContentView(R.layout.main);
    
    
    
    Button newgame = (Button)findViewById(mx.itesm.btp.R.id.btnJuegoNuevo);
    newgame.setOnClickListener(new View.OnClickListener() {
        
        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            IniciarJuego();
            
        }
    });
    
    
    Button continuar = (Button)findViewById(mx.itesm.btp.R.id.btnContinuar);
    continuar.setOnClickListener(new View.OnClickListener() {
        
        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            //ContinuarJuego();
            
        }
    });

    
    
    Button Scores = (Button)findViewById(mx.itesm.btp.R.id.btnScores);
    Scores.setOnClickListener(new View.OnClickListener() {
        
        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            //Scores();
        }
    });
    
    
    Button Opciones = (Button)findViewById(mx.itesm.btp.R.id.btnOpciones);
    Opciones.setOnClickListener(new View.OnClickListener() {
        
        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            //MenuOpciones();     
        }
    });
    
    
    Button AcercaDe = (Button)findViewById(mx.itesm.btp.R.id.btnAcercaDe);
    AcercaDe.setOnClickListener(new View.OnClickListener() {
        
        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            //AcercaDe();     
        }
    });
    
    
    }

    
    
    
    private void  IniciarJuego()
    {
        Intent intencion = new Intent(Principal.this, PantallJuego.class);
        startActivity (intencion);
    }
    
    private void  ContinuarJuego()
    {
        Intent intencion = new Intent (Principal.this, PantallJuego.class); 
        startActivity (intencion);
    }
    
    private void  Scores()
    {
        Intent intencion = new Intent (Principal.this, PantallJuego.class); 
        startActivity (intencion);
    }
    
    private void  MenuOpciones()
    {
        Intent intencion = new Intent (Principal.this, PantallJuego.class); 
        startActivity (intencion);
    }
    
    private void  AcercaDe()
    {
        Intent intencion = new Intent (Principal.this, PantallJuego.class); 
        startActivity (intencion);
    }
    

}