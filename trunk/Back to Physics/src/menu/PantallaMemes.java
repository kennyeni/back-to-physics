package menu;

import java.util.Timer;

import java.util.TimerTask;

import mx.itesm.btp.BacktoPhysics;
import mx.itesm.btp.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

public class PantallaMemes extends Activity {
	
	protected static final int RESULT_CLOSE_ALL = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.memes); 
	}
	
	
	
	@Override
	protected void onResume() {
		super.onResume();
		Timer reloj = new Timer();
		TimerTask lanzaMemes = new TimerTask() {
			
			@Override
			public void run() {
				Intent intencion = new Intent (PantallaMemes.this, menu.SplashScreen.class); 
				startActivityForResult(intencion, RESULT_CLOSE_ALL);
				
			}
		};
		reloj.schedule(lanzaMemes, 2000);
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    switch(resultCode)
	    {
	    case RESULT_CLOSE_ALL:
	        setResult(RESULT_CLOSE_ALL);
	        finish();
	    }
	    super.onActivityResult(requestCode, resultCode, data);
	}
	
	
}
