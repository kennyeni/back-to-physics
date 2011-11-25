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
    private static final int RESULT_CLOSE_ALL = 0;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Intent intencion = new Intent (BacktoPhysics.this, menu.Logo.class); 
        startActivityForResult(intencion, RESULT_CLOSE_ALL);
        
        
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(resultCode)
        {
        case RESULT_CLOSE_ALL:
            //setResult(RESULT_CLOSE_ALL);
            finish();
            android.os.Process.killProcess(android.os.Process.myPid());
            //System.exit(0);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

