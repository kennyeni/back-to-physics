package mx.itesm.btp;

import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;

/**
 * Es la clase principal que manda llamar a todo el juego
 * @author vero
 *
 */
public class BacktoPhysics extends Activity {
    private static final int INICIO = 1000;
    private Handler handler = new Handler();
    private Runnable lanzaMemes = new Runnable() {
        @Override
        public void run() {
        	setContentView(R.layout.memes);
        }
    };
    private Runnable lanzaSplash = new Runnable() {
        @Override
        public void run() {
        	setContentView(R.layout.splashscreen);
        }
    };
    
    private Runnable lanzaJuego = new Runnable() {
        @Override
        public void run() {
        	Intent intencion = new Intent (BacktoPhysics.this, menu.Principal.class); 
	        startActivityForResult(intencion, INICIO);
	        BacktoPhysics.this.finish();
        }
    };
    
    
    
    

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantallalogo);
        handler.postDelayed(lanzaMemes, 2000);
        handler.postDelayed(lanzaSplash, 4000);
        handler.postDelayed(lanzaJuego, 6000);
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
		/*
		if(keyCode == KeyEvent.KEYCODE_BACK){
			setResult(RESULT_CLOSE_ALL);
			finish();
		}
		*/
		return true;
	}

}

