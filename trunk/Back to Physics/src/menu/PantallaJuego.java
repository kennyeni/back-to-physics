package menu;

import exceptions.NoContextProvidedException;
import graficas.Coordenadas;
import graficas.Juego;
import graficas.Pantalla;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;


public class PantallaJuego  extends Activity implements Runnable, OnTouchListener{
	private Juego juego;
	private boolean corriendo;
	private MediaPlayer player;
	private Pantalla pantalla;
	private Coordenadas coordenadas;
	
	float widthArrow;
	float heightArrow;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		pantalla = new Pantalla();
		juego = new Juego(this, pantalla);
		juego.setOnTouchListener(this);
		setContentView(juego);
		reproducirAudio();
		
		widthArrow = juego.getArrow().getWidth();
		heightArrow =juego.getArrow().getHeight();
		

		

	}
	private void reproducirAudio(){
   	 if(player!=null){
   		 player.release();
   	 }
   	 player=MediaPlayer.create(this,mx.itesm.btp.R.raw.pkmn);
   	 player.start();
    }
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			startActivity(new Intent(PantallaJuego.this, Principal.class));
		}
		return true;
	}


	protected void onResume() {
		super.onResume();
		juego.refresh();
		Thread th = new Thread(this);
		th.start();
		
	}
	
	protected void onStop() {
		super.onStop();
		juego.refresh();
		corriendo = false;
		if(player!=null){
	   		 if(player.isPlaying()){
	   			 player.stop();
	   			 player.release();
	   		 }
	   	 }
	   	 super.onStop();
		

	}
	
	//////////////////////////////////////////////////
	//////////// Manejar aqui eventos
	/////////// Para todo lo que suceda :)
	/////////////////////////////////////////////////
	
	
	
	public Coordenadas getPos(int x, int y){
		coordenadas = new Coordenadas(x,y);
		
		return coordenadas;
	}
	
	
	
	
	public boolean onTouch(View v, MotionEvent event) {
		
		try {
			
			float posx = event.getX();
			float posy = event.getY();
			int widthPantalla = pantalla.getWidth();
			int heightPantalla = pantalla.getHeight();
			
			


			
			if( (posx > (4*widthPantalla/5)  && posx < (4*widthPantalla/5)+ 2*widthArrow/5 ) &&
					(posy > (3*heightPantalla/5)  && posy < (3*heightPantalla/5)+2*heightArrow/5)		 
					){
				juego.fondo.mueveY(-10);
			}
			
			
			
			if( (posx > (4*widthPantalla/5)  && posx < (4*widthPantalla/5)+ 2*widthArrow/5 ) &&
					(posy > (4*heightPantalla/5)  && posy < (4*heightPantalla/5)+2*heightArrow/5)		 
					){
				juego.fondo.mueveY(10);
			}
			
			
			

			if (((posx > 7*widthPantalla/10)&&(posx < 7*widthPantalla/10 + 2*widthArrow/5))&&((posy > 3*heightPantalla/5+2*heightArrow/5 )&&(posy < 3*heightPantalla/5+4*heightArrow/5))){
				juego.fondo.mueveX(-10);
			}
			
			
			if (((posx > (4*widthPantalla/5)+ 2*widthArrow/5)&&(posx < (4*widthPantalla/5)+ 4*widthArrow/5))&&((posy > 3*heightPantalla/5+2*heightArrow/5 )&&(posy < 3*heightPantalla/5+4*heightArrow/5))){
				juego.fondo.mueveX(10);
			}
			
			
			if (((posx > widthPantalla-850)&&(posx < widthPantalla-750))&&((posy > heightPantalla-150 )&&(posy < heightPantalla-50))){
				juego.disparar();
			}
			
			
			
			
			

			
			

			
			// juego.fondo.mueveX(10);
			 //juego.fondo.mueveY(10);
		} catch (NoContextProvidedException e){}
		
		return true;
	}
	

	
	public void run() {
		corriendo = true;
		while (corriendo) {
			juego.postInvalidate();
			try {
				Thread.sleep(34);
			} catch (InterruptedException e) {

			}
		}
	}

}
