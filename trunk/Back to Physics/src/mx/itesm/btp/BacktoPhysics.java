package mx.itesm.btp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import animacion.Graficacion;

/**
 * Es la clase principal que manda llamar a todo el juego
 * @author vero
 *
 */
public class BacktoPhysics extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Intent intencion = new Intent (BacktoPhysics.this, menu.Logo.class); 
        startActivity (intencion);
        
        
    }
}