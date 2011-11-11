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
				Intent intencion = new Intent (PantallaMemes.this, menu.Principal.class); 
		        startActivity(intencion);
				
			}
		};
		reloj.schedule(lanzaMemes, 4000);
		
	}
	
	
}
