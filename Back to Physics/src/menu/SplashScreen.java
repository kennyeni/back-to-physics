package menu;

import java.util.Timer;
import java.util.TimerTask;
import mx.itesm.btp.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen); 
	}
	
	
	
	@Override
	protected void onResume() {
		super.onResume();
		Timer reloj = new Timer();
		TimerTask lanzaSplash = new TimerTask() {
			
			@Override
			public void run() {
				Intent intencion = new Intent (SplashScreen.this, menu.Principal.class); 
		        startActivity(intencion);
				
			}
		};
		reloj.schedule(lanzaSplash, 2000);
		
	}
	

}
