package menu;

import graficas.Juego;

import graficas.Pantalla;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Toast;


public class PantallaJuego  extends Activity implements Runnable{
	private Juego juego;
	private boolean corriendo;
	private MediaPlayer player;
	private Pantalla pantalla;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		pantalla = new Pantalla();
		juego = new Juego(this, pantalla);
		setContentView(juego);
		reproducirAudio();

	}
	private void reproducirAudio(){
   	 if(player!=null){
   		 player.release();
   	 }
   	 player=MediaPlayer.create(this,mx.itesm.btp.R.raw.pkmn);
   	 player.start();
    }


	protected void onResume() {
		super.onResume();
		Thread th = new Thread(this);
		th.start();
		
	}
	
	protected void onStop() {
		super.onStop();
		corriendo = false;
		if(player!=null){
	   		 if(player.isPlaying()){
	   			 player.stop();
	   			 player.release();
	   		 }
	   	 }
	   	 super.onStop();
		

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
