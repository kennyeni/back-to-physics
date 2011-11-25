package menu;

import java.util.Timer;
import java.util.TimerTask;
import mx.itesm.btp.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends Activity {
	
	private static final int RESULT_CLOSE_ALL = 0;

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
		        startActivityForResult(intencion, RESULT_CLOSE_ALL);
				
			}
		};
		reloj.schedule(lanzaSplash, 2000);
		
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
