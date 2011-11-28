package menu;

import entrada.Acelerometro;
import exceptions.NoContextProvidedException;
import graficas.Coordenadas;
import graficas.Juego;
import graficas.Pantalla;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;

/**
 * Clase que recibe todas las acciones que ser‡n realizadas en la pantalla de juego nuevo.
 * @author vero
 *
 */
public class PantallaJuego  extends Activity implements Runnable, OnTouchListener{
	private static final String MENU_PAUSE = null;
	private static final String MENU_RESUME = null;
	private Juego juego;
	private boolean corriendo;
	private MediaPlayer player;
	private MediaPlayer player2;
	private Pantalla pantalla;
	private Coordenadas coordenadas;
	private Acelerometro acel;
	private int notifSonido=0;
	private final int DIALOGO_SIMPLE =0;
	private int xinicial, yinicial;
	private double theta,phi;
	
	
	//private Bitmap imgbtn = BitmapFactory.decodeResource(getResources(), mx.itesm.btp.R.drawable.btnb);

	
	float widthArrow;
	float heightArrow;
	
	public SharedPreferences preferenceSuena;
	public SharedPreferences preferenceSonido;
	boolean musica;
	boolean sonido;
	

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		preferenceSonido = getSharedPreferences("musica", Context.MODE_PRIVATE);
		musica = preferenceSonido.getBoolean("musica", true);
		
		preferenceSuena =  getSharedPreferences("sonido", Context.MODE_PRIVATE);
		sonido= preferenceSuena.getBoolean("sonido", true);
		
		
		pantalla = new Pantalla();
		juego = new Juego(this, pantalla);
		juego.setOnTouchListener(this);
		setContentView(juego);
		reproducirAudio();
		
		widthArrow = juego.getArrow().getWidth();
		heightArrow =juego.getArrow().getHeight();
		
		acel = new Acelerometro(this);
		xinicial = juego.getWidth();
		yinicial = juego.getHeight();
	}
	private void detenerSonido(){
		if(sonido){
			if(player2.isPlaying()){
				player2.stop();
				player2.release();
			}
		}
	}
	
	private void reproducirGato(){
				 if(notifSonido==1 && player2!=null){
					 player2.release();
				 }
				 player2=MediaPlayer.create(this, mx.itesm.btp.R.raw.explosion);
				 player2.start();
	}
	
	
	
	private void detenerAudio(){
		if (musica) {
			if (player.isPlaying()) {
				player.stop();
				player.release();			
			}
		}	
	}
	

	private void reproducirAudio(){
   	 if (musica) {
   		if(player!=null){
      		 player.release();
      	 }
      	 player=MediaPlayer.create(this,mx.itesm.btp.R.raw.pkmn);
      	 player.start();
   	 	}
    }

	
	
	/**
	 * Inicia la actividad de empezar el juego.
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			startActivity(new Intent(PantallaJuego.this, Principal.class));
		}
		return false;
	}


	protected void onResume() {
		super.onResume();
		juego.refresh();
		Thread th = new Thread(this);
		th.start();
		
	}
	
	protected void onStop() {
		super.onStop();
		detenerSonido();
		detenerAudio();
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
	protected void onPause(){
		super.onPause();
		onCreateDialog(DIALOGO_SIMPLE);
		
	}
	protected Dialog onCreateDialog(int id){
    	
		if(id==DIALOGO_SIMPLE){
			Dialog cuadroDialogo=null;
		
    	AlertDialog.Builder simpleBuilder = new AlertDialog.Builder(this);
		simpleBuilder.setMessage("         Pausa           ");
		simpleBuilder.setPositiveButton("Reanudar", new DialogInterface.OnClickListener() {
			
			
			public void onClick(DialogInterface dialogo, int which) {
				dialogo.dismiss();
				
			}
		});
		cuadroDialogo = simpleBuilder.create();
		return cuadroDialogo;
		}return null;
		
	}
	
	//////////////////////////////////////////////////
	//////////// Manejar aqui eventos
	/////////// Para todo lo que suceda :)
	/////////////////////////////////////////////////
	
	
	/**
	 * Obtiene las coordenadas X y Y  de la pantalla
	 * @param x
	 * @param y
	 * @return
	 */
	public Coordenadas getPos(int x, int y){
		coordenadas = new Coordenadas(x,y);
		
		return coordenadas;
	}
	
	
	
	/**
	 * Recibe la acci—n de tocar el bot—n y comenzar la acci—n
	 */
	public boolean onTouch(View v, MotionEvent event) {
		
		try {
			
			float posx = event.getX();
			float posy = event.getY();
			int widthPantalla = pantalla.getWidth();
			int heightPantalla = pantalla.getHeight();
			Bitmap imgbtn = BitmapFactory.decodeResource(getResources(), mx.itesm.btp.R.drawable.btnb);
			
			


			
			if( (posx > (4*widthPantalla/5)  && posx < (4*widthPantalla/5)+ 2*widthArrow/5 ) &&
					(posy > (3*heightPantalla/5)  && posy < (3*heightPantalla/5)+2*heightArrow/5)		 
					){
				juego.fondo.mueveY(-10.5);
				juego.catapulta.mueveY(-10.5);
			}
			
			
			
			if( (posx > (4*widthPantalla/5)  && posx < (4*widthPantalla/5)+ 2*widthArrow/5 ) &&
					(posy > (4*heightPantalla/5)  && posy < (4*heightPantalla/5)+2*heightArrow/5)		 
					){
				juego.fondo.mueveY(10.5);
				juego.catapulta.mueveY(10.5);
			}
			
			
			

			if (((posx > 7*widthPantalla/10)&&(posx < 7*widthPantalla/10 + 2*widthArrow/5))&&((posy > 3*heightPantalla/5+2*heightArrow/5 )&&(posy < 3*heightPantalla/5+4*heightArrow/5))){
				juego.fondo.mueveX(-10);
				juego.catapulta.mueveX(-10);
			}
			
			
			if (((posx > (4*widthPantalla/5)+ 2*widthArrow/5)&&(posx < (4*widthPantalla/5)+ 4*widthArrow/5))&&((posy > 3*heightPantalla/5+2*heightArrow/5 )&&(posy < 3*heightPantalla/5+4*heightArrow/5))){
				juego.fondo.mueveX(10);
				juego.catapulta.mueveX(10);
			}
			
			
			if (((posx > (1/60)*widthPantalla)&&(posx < imgbtn.getWidth()))&&((posy > (5)*heightPantalla/7 )&&(posy < heightPantalla))){
				notifSonido=1;
				if (sonido) {
					reproducirGato();
				}
				juego.disparar();
//				onPause();
//				showDialog(DIALOGO_SIMPLE);
				
				
				int x = juego.getWidth();
				int y = juego.getHeight();
				
				if(xinicial == x){
					theta= 90.0;
				}else if (x>xinicial){
					theta = x*50/(pantalla.getWidth() - xinicial);
				}else{
					theta = x*50/xinicial;
				}
				
				if(yinicial == y){
					phi= 30.0;
				}else if (y>yinicial){
					phi = y*50/(pantalla.getHeight() - yinicial);
				}else{
					phi = y*30/yinicial;
				}
				
				
				
			}
			
			
			
			
			

			
			

			
		
		} catch (NoContextProvidedException e){}
		
		return true;
	}
	

	

	/**
	 * Empieza la accion de mover X y Y en la pantalla del juego
	 */
	public void run() {
		corriendo = true;
		while (corriendo) {
			juego.mueveY(acel.getY());
			juego.mueveX(acel.getX());
			juego.setY(acel.Y);
			juego.setX(acel.X);
			juego.postInvalidate();
			
			try {
				
				Thread.sleep(34);
			} catch (Exception e) {

			}
		}
	}

}
