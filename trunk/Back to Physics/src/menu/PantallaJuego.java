package menu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;



import logica.Fisica;
import logica.HighScore;
import mx.itesm.btp.R;

import entrada.Acelerometro;
import exceptions.NoContextProvidedException;
import graficas.Coordenadas;
import graficas.Juego;
import graficas.Pantalla;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ToggleButton;
import animacion.PlanoCartesiano;

/**
 * Clase que recibe todas las acciones que ser‡n realizadas en la pantalla de juego nuevo.
 * @author vero
 *
 */
public class PantallaJuego  extends Activity implements Runnable, OnTouchListener, OnSharedPreferenceChangeListener{
	private static final String MENU_PAUSE = null;
	private static final String MENU_RESUME = null;
	private static final int GRAFICAS = 1;
	private static final int JUEGO = 0;
	private static final int DIALOGO_GRAFICAS = 40;
	private static final int DIALOGO_CALIBRACION = 10;
	private Juego juego;
	private boolean corriendo;
	private MediaPlayer player;
	private MediaPlayer player2;
	private Pantalla pantalla;
	private Coordenadas coordenadas;
	private Acelerometro acel;
	private int notifSonido=0;
	private final int DIALOGO_SIMPLE =0;
	private final int DIALOGO_SIMPLE2 =1;
	public double v=20, theta=1, phi=1, g=9.81;
	private float enemigoX, enemigoY;
	private int yinicial, xinicial;
	private int modoDeJuego = 0;
	private PlanoCartesiano plano = null;
	private float widthArrow;
	private float heightArrow;
	private SharedPreferences preferenceSuena;
	private SharedPreferences preferenceSonido;
	private boolean musica;
	private boolean sonido;
	private boolean acelerometro;
	private Thread th = null;
	private boolean modoNyan = false;
	public final int NIVEL_1 =1;
	public final int NIVEL_2 =2;
	private final String PNivel = "nivel";
	private int nivel;
	private int calibracionX;
	private int calibracionY;
	public final int NIVEL_3 =3;
	private int selectorNivel=1;
	private boolean juegoViejo;
	private long tiempoInicio;
	private int score =0, misiles=5;
	private final String FILENAME = "btpPrefs";
	private final String highscores = "hs";

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		musica = (getSharedPreferences("musica", Context.MODE_PRIVATE)).getBoolean("musica", true);
		sonido = (getSharedPreferences("sonido", Context.MODE_PRIVATE)).getBoolean("sonido", true);
		acelerometro = (getSharedPreferences("acelerometro", Context.MODE_PRIVATE)).getBoolean("acelerometro", true);
		calibracionX = (getSharedPreferences("calibracionX",Context.MODE_PRIVATE)).getInt("calibracionX", 0);
		calibracionY = (getSharedPreferences("calibracionY",Context.MODE_PRIVATE)).getInt("calibracionY", 0);
		
		(getSharedPreferences(PNivel, Context.MODE_PRIVATE)).registerOnSharedPreferenceChangeListener(this); 
		//Cada ves que se cambie una propiedad se llama

		if (calibracionX==0) {
			showDialog(DIALOGO_CALIBRACION);
		}
		juegoViejo =getIntent().getBooleanExtra("CONTINUAR", true);
		if(juegoViejo){
	         SharedPreferences preferenceNivel = getSharedPreferences(PNivel, Context.MODE_PRIVATE);
	         selectorNivel = preferenceNivel.getInt(PNivel, 1);
		}
		
		pantalla = new Pantalla();
		switch(selectorNivel){
		case NIVEL_2:
			juego = new Juego(this, pantalla,NIVEL_2);
			break;
		case NIVEL_3:
			juego= new Juego(this,pantalla,NIVEL_3);
			break;
			default:
				juego = new Juego(this,pantalla, NIVEL_1);
				break;
		}
		
		Date inicio = new Date();
		tiempoInicio = inicio.getTime();
		
		juego.setOnTouchListener(this);
		FrameLayout layoutPrincipal = new FrameLayout(getBaseContext());
		
		layoutPrincipal.addView(juego);
		
		LinkedList<Coordenadas> puntosA = Fisica.puntosAereos(v, theta, phi, g);
		LinkedList<Coordenadas> puntosL = Fisica.puntosLaterales(v, theta, phi, g);
		plano = new PlanoCartesiano(this, puntosA, puntosL, theta, phi, pantalla, enemigoX, enemigoY, modoNyan);
		layoutPrincipal.addView(plano);
		plano.setVisibility(View.INVISIBLE);
		
		setContentView(layoutPrincipal);
		
		reproducirAudio(selectorNivel);
		
		widthArrow = juego.getArrow().getWidth();
		heightArrow =juego.getArrow().getHeight();
		
		acel = new Acelerometro(this);
		xinicial = juego.getWidth();
		yinicial = juego.getHeight();
	}
	

	/**
	 * Listener de cuando un nivel ha cambiado
	 * @param pref
	 * @param key
	 */
	public void onSharedPreferenceChanged(SharedPreferences pref, String key) {
		if(key==PNivel){
			juego.setNivel(pref.getInt(PNivel, 1));
		}
		
	}
	
	private void detenerSonido(){
		if(sonido){
			if(player2!=null&&player2.isPlaying()){
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
			if (player!=null&&player.isPlaying()) {
				player.stop();
				player.release();			
			}
		}	
	}
	
	private void reproducirAudio(int selectorNivel){
		switch(selectorNivel){
		case NIVEL_1:
			if (musica) {
				if(player!=null){
					player.release();
				}
				player=MediaPlayer.create(this,mx.itesm.btp.R.raw.pkmn);
				player.start();
			}break;
		case NIVEL_2:
			if (musica) {
				if(player!=null){
					player.release();
				}
				player=MediaPlayer.create(this,mx.itesm.btp.R.raw.metalslug);
				player.start();
			}break;
		case NIVEL_3:
			if (musica) {
				if(player!=null){
					player.release();
				}
				player=MediaPlayer.create(this,mx.itesm.btp.R.raw.sonidoespacio);
				player.start();
			}break;
		}

	}

	/**
	 * Inicia la actividad de empezar el juego.
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK&&modoDeJuego==GRAFICAS){
			//startActivity(new Intent(PantallaJuego.this, Principal.class));
			showDialog(DIALOGO_GRAFICAS);
		}else{
			return super.onKeyDown(keyCode, event);
		}
		return false;
	}

	protected void onResume() {
		super.onResume();
		juego.refresh();
		corriendo=true;
		th = new Thread(this);
		th.start();	
	}
	
	protected void onStop() {
		super.onStop();
		detenerSonido();
		detenerAudio();
		juego.refresh();
		corriendo = false;
	   	 super.onStop();
		

	}

	protected void onPause(){
		super.onPause();
		onCreateDialog(DIALOGO_SIMPLE);
		
	}

	protected Dialog onCreateDialog(int id){
		if(id==DIALOGO_SIMPLE2){
			Dialog cuadroDialogo2=null;

			AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
			builder2.setMessage("         Seguro?           ");
			builder2.setPositiveButton("Si", new DialogInterface.OnClickListener() {


				public void onClick(DialogInterface dialog, int which) {
					finish();
					//startActivity(new Intent(PantallaJuego.this, Principal.class));
				}
			});
			builder2.setNegativeButton("No", new DialogInterface.OnClickListener() {


				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();

				}
			});cuadroDialogo2 = builder2.create();
			return cuadroDialogo2;
		}

		else if(id==DIALOGO_SIMPLE){
			Dialog cuadroDialogo=null;

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("         Pausa           ");
			builder.setPositiveButton("Reanudar", new DialogInterface.OnClickListener() {


				public void onClick(DialogInterface dialogo, int which) {
					dialogo.dismiss();

				}
			});
			builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {


				public void onClick(DialogInterface dialog, int which) {
					showDialog(DIALOGO_SIMPLE2);
				}	
			});
			cuadroDialogo = builder.create();
			return cuadroDialogo;
		}else if(id==DIALOGO_GRAFICAS){
			Dialog dialogoGraficas=null;

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("ÀAnalizar grafica?");
			builder.setPositiveButton("Analizar", new DialogInterface.OnClickListener() {


				@Override
				public void onClick(DialogInterface dialogo, int which) {
					dialogo.dismiss();

				}
			});
			builder.setNegativeButton("Jugar", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					plano.termino = true;
				}	
			});
			dialogoGraficas = builder.create();
			return dialogoGraficas;

		}else if(id==DIALOGO_CALIBRACION){
			Dialog dialogoCalibracion=null;

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Pon tu dispositivo en un ‡ngulo c—modo");
			builder.setPositiveButton("Calibrar", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialogo, int which) {
					acel.calibracion();	
				}
			});
			builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}	
			});
			dialogoCalibracion = builder.create();
			return dialogoCalibracion;
		}
		return null;

	}
	
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
				juego.vehiculoEnemigo.mueveY(10.5);
			}
			
			
			
			if( (posx > (4*widthPantalla/5)  && posx < (4*widthPantalla/5)+ 2*widthArrow/5 ) &&
					(posy > (4*heightPantalla/5)  && posy < (4*heightPantalla/5)+2*heightArrow/5)		 
					){
				juego.fondo.mueveY(10.5);
				juego.vehiculoEnemigo.mueveY(-10.5);
			}
			
			
			

			if (((posx > 7*widthPantalla/10)&&(posx < 7*widthPantalla/10 + 2*widthArrow/5))&&((posy > 3*heightPantalla/5+2*heightArrow/5 )&&(posy < 3*heightPantalla/5+4*heightArrow/5))){
				juego.fondo.mueveX(-10);
				juego.vehiculoEnemigo.mueveX(10);
			}
			
			
			if (((posx > (4*widthPantalla/5)+ 2*widthArrow/5)&&(posx < (4*widthPantalla/5)+ 4*widthArrow/5))&&((posy > 3*heightPantalla/5+2*heightArrow/5 )&&(posy < 3*heightPantalla/5+4*heightArrow/5))){
				juego.fondo.mueveX(10);
				juego.vehiculoEnemigo.mueveX(-10);
			}
			
			
			if (((posx > (1/60)*widthPantalla)&&(posx < imgbtn.getWidth()))&&((posy > (5)*heightPantalla/7 )&&(posy < heightPantalla))){
				notifSonido=1;
				if (sonido) {
					reproducirGato();
				}
				juego.disparar();
			
				
				
				int x = juego.getWidth();
				int y = juego.getHeight();
				
				theta = 140-((x*100)/juego.fondo.getXRespectoAFondo());
				phi= 100-((y*80)/juego.fondo.getYRespectoAFondo());
				

				juego.phi = phi;
				juego.theta = theta;
				
				
			}if(posx>= widthPantalla-25 && posy >=20){
				onPause();
				showDialog(DIALOGO_SIMPLE);
			}
					
		} catch (NoContextProvidedException e){}
		
		return true;
	}


	/**
	 * Empieza la accion de mover X y Y en la pantalla del juego
	 */
	public void run() {
		while (corriendo) {
			if(modoDeJuego==JUEGO){
				moverJuego();
			} else if(modoDeJuego==GRAFICAS){
				if(plano!=null||!plano.hasEnded()){
					//plano.bringToFront();
					graficarJuego();
				} else {
					modoDeJuego = JUEGO;
					juego.graficaFlag = false;
				}
			}
			try {Thread.sleep(34);}
			catch (Exception e) { Log.d("ERROR!", "Sucedi— lo impensable!, se interrumpio el thread principal.");}
		}
	}
	
	private void cambiarView(int id){
		//Log.d("DEP", "Se cambio el view a:"+id);
		if(id==GRAFICAS){
			modoDeJuego = GRAFICAS;
			juego.post(new Runnable() {
			    public void run() {
			    	juego.setVisibility(View.INVISIBLE);
			    }
			});
			plano = getPlano();
			plano.post(new Runnable() {
			    public void run() {
			    	plano.setVisibility(View.VISIBLE);
			    }
			});
			
		} else if(id==JUEGO){
			modoDeJuego = JUEGO;
			juego.post(new Runnable() {
			    public void run() {
			    	juego.setVisibility(View.VISIBLE);
			    }
			});
			plano.post(new Runnable() {
			    public void run() {
			    	plano.setVisibility(View.INVISIBLE);
			    }
			});
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//refrescarGrafica();			
		}
	}
	
	private PlanoCartesiano getPlano(){
		if(plano==null){
			//Log.d("DEP", "Regenerando graficas");
			LinkedList<Coordenadas> puntosA = Fisica.puntosAereos(v, theta, phi, g);
			LinkedList<Coordenadas> puntosL = Fisica.puntosLaterales(v, theta, phi, g);
			plano = new PlanoCartesiano(this, puntosA, puntosL, theta, phi, pantalla, enemigoX, enemigoY, modoNyan);
		}
		return plano;
	}
	
	private void graficarJuego(){
		getPlano().postInvalidate();
		//Log.d("DEP", "Graficando graficas");
		if(plano.hasEnded()){ //acabo la graficacion
			modoDeJuego = JUEGO;
			float dis = plano.getDistanciaDano();
			juego.infringirDano(dis);
			if(juego.isOver()){
				generarScore();
				if(juego.selectorNivel==1){
					setContentView(R.layout.nivel2);
					juego.setNivel(2);
				}
			}
			juego.graficaFlag = false;
			cambiarView(JUEGO);
			
			
			
			
		}
		
	}
	
	private void generarScore(){
		Date t = new Date();
		double tiempo = (t.getTime()-tiempoInicio)*1000;
		score = (int) ((1/tiempo)*misiles*10);
		escribirScore(score);
	}
	
	private void escribirScore(int score){
		boolean existe=false;
		String files[] = fileList();
		for(int i=0; i<files.length; i++){
			if(files[i].toString()=="scores"){
				existe=true;
			}
		}
		
		Scanner scores = null;
		if(existe){
			try {
				scores = new Scanner(openFileInput("scores"));
			} catch (FileNotFoundException e) {Log.i("ERROR", "No sirve la busqueda :S");}
		}
		ArrayList<HighScore> scoreList = new ArrayList<HighScore>();
		while(scores!=null&&scores.hasNext()){
			HighScore tmp = new HighScore(scores.next(), scores.nextInt());
			scoreList.add(tmp);
		}
		String nombre = (getSharedPreferences("usuario",Context.MODE_PRIVATE)).getString("usuario", "anonymous");
		scoreList.add(new HighScore(nombre, score));
		Collections.sort(scoreList);
		if(scores!=null)
			scores.close();

		Object[] hs = scoreList.toArray();
		String[] str = new String[5];
		FileOutputStream fos = null;
		try {
			fos = openFileOutput("scores", Context.MODE_PRIVATE);
			for(int i=0;i<5;i++){
				str[i] = hs[i].toString();
				fos.write(str[i].getBytes());
			}
			fos.close();
		} catch (IOException e) {
			Log.i("ERROR","No se pudieron guardar los hs");
		}
		
		
	}
	
	private void refrescarGrafica(){
		//Log.d("DEP", "Borrando graficas");
		plano = null;
	}
	
	
	 
	
	private void moverJuego() {
		//Log.d("DEP", "Graficando Juego");
		juego.bringToFront();
		if (acelerometro) {
			juego.mueveY(acel.getY());
			juego.mueveX(acel.getX());
			juego.setY(acel.Y);
			juego.setX(acel.X);
			//arrow.realease();
			//}
		//coso que dibuja el acelerometro= dibuja (this, mx.itesm.btpn.R.NOMBRE);
		//COSO QUE DIBUJA.start();
			
		}
		
		juego.postInvalidate();
		
		if(juego.graficaFlag){
			modoDeJuego = GRAFICAS;
			cambiarView(GRAFICAS);
			LinkedList<Coordenadas> puntosA = Fisica.puntosAereos(v, theta, phi, g);
			LinkedList<Coordenadas> puntosL = Fisica.puntosLaterales(v, theta, phi, g);
			plano.regenerar(puntosA, puntosL, theta, phi, enemigoX, enemigoY, modoNyan);
		}
		
		
	}

}

