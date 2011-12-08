package graficas;

import personajes.Enemigo;
import exceptions.NoContextProvidedException;


import menu.PantallaJuego;
import mx.itesm.btp.BacktoPhysics;
import mx.itesm.btp.R;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.Paint.Style;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.view.View.OnTouchListener;
import animacion.Graficacion;

public class Juego extends View{
	private Canvas cbm;
	private ImageView iv;
	private Bitmap bm;
	private int xp,xd,offset;
	private Paint p;
	private MediaPlayer player;
	public Fondo fondo;
	private Bitmap imgbtn;
	private Bitmap arrow;
	private Bitmap mira;
	private Bitmap barra1;
	private Bitmap barra2;
	private Bitmap barra3;
	private Bitmap barra4;
	private Bitmap barra5;
	private Bitmap missile;
	private Bitmap pausebtn;
	private int x=0;
	private boolean disparo;
	private int missileworiginal;
	private int missilehoriginal;
	private int contador;
	private double X,Y;
	private double i=1.0;
	public Enemigo vehiculoEnemigo;
	public boolean graficaFlag = false;
	private float poderDeProyectil;
	public final int NIVEL_1 =1;
	public final int NIVEL_2 =2;
	public final int NIVEL_3 =3;
	private int Indicadorvida;
	public int selectorNivel;
	private Pantalla pantalla;
	public double theta, phi;
	private boolean resized=false;
	private int hmax_enemigo = 0, xmax_enemigo = 0, xenemigo = 0, yenemigo = 0;
	



	/**
	 * En esta clase esta el desarrollo completo de las acciones del juego
	 * @param context
	 * @param pantalla
	 */

	/**
	 * Dibuja en pantalla todos los recursos que se utilizan. 
	 * @param context
	 * @param pantalla
	 */
	public Juego(Context context, Pantalla pantalla, int selectorNivel){
		super(context);
		p=new Paint();
		pantalla.setContext(context);
		this.pantalla=pantalla;
		float vida = (float) 100.0;
		switch(selectorNivel){
		case NIVEL_1:
			vehiculoEnemigo= new Enemigo(mx.itesm.btp.R.drawable.cat1, vida, pantalla, getResources());
			fondo = new Fondo(mx.itesm.btp.R.drawable.valley, pantalla, getResources());
			break;
		case NIVEL_2:
			vehiculoEnemigo= new Enemigo(mx.itesm.btp.R.drawable.enemigo2, vida, pantalla, getResources());
			fondo = new Fondo(mx.itesm.btp.R.drawable.fondobn, pantalla, getResources());

		case NIVEL_3:
			vehiculoEnemigo= new Enemigo(mx.itesm.btp.R.drawable.nave1, vida, pantalla, getResources());
			fondo = new Fondo(mx.itesm.btp.R.drawable.fondo3bn, pantalla, getResources());

		}

		graficaFlag = false;

		arrow = BitmapFactory.decodeResource(getResources(), mx.itesm.btp.R.drawable.cpad);
		mira=BitmapFactory.decodeResource(getResources(), mx.itesm.btp.R.drawable.crosshair2);
		pausebtn= BitmapFactory.decodeResource(getResources(), mx.itesm.btp.R.drawable.pause);

		imgbtn= BitmapFactory.decodeResource(getResources(), mx.itesm.btp.R.drawable.btnb);
		barra1= BitmapFactory.decodeResource(getResources(), mx.itesm.btp.R.drawable.barra1);
		barra2= BitmapFactory.decodeResource(getResources(), mx.itesm.btp.R.drawable.barra2);
		barra3= BitmapFactory.decodeResource(getResources(), mx.itesm.btp.R.drawable.barra3);
		barra4= BitmapFactory.decodeResource(getResources(), mx.itesm.btp.R.drawable.barra4);
		barra5= BitmapFactory.decodeResource(getResources(), mx.itesm.btp.R.drawable.barra5);
		missile=BitmapFactory.decodeResource(getResources(), mx.itesm.btp.R.drawable.rockbn);
		contador = 0;


		int missilew = missile.getWidth();
		int missileh = missile.getHeight();
		missile=Bitmap.createScaledBitmap(missile,5*missilew, 5*missileh, false);


		missileworiginal = missile.getWidth();
		missilehoriginal = missile.getHeight();
		disparo = false;

		
		
			hmax_enemigo = (int)fondo.getHeight()/2;
			xmax_enemigo = (int)fondo.getWidth();

		
		
		
		
		
		do {
			xenemigo = (int) (Math.random()*1000); 
			} while (xenemigo>0 && xenemigo< xmax_enemigo);
		
		do {
			yenemigo = (int) (Math.random()*1000); 
			} while (yenemigo>0 && yenemigo< hmax_enemigo);
		
		
		
	}

	/**
	 * Metodo que se encarga de hacer el cambio entre niveles
	 * @param nivel
	 */
	public void setNivel(int nivel){
		selectorNivel = nivel;
		
		SharedPreferences preferenceNivel = getContext().getSharedPreferences("nivel", Context.MODE_PRIVATE);
		SharedPreferences.Editor editorMusica = preferenceNivel.edit();
		editorMusica.putInt("nivel", nivel);
		editorMusica.commit();

		float vida = (float) 100.0;


		switch(selectorNivel){
		case NIVEL_1:
			vehiculoEnemigo= new Enemigo(mx.itesm.btp.R.drawable.cat1, vida, pantalla, getResources());
			fondo = new Fondo(mx.itesm.btp.R.drawable.valley, pantalla, getResources());
			break;

		case NIVEL_2:
			vehiculoEnemigo= new Enemigo(mx.itesm.btp.R.drawable.enemigo2, vida, pantalla, getResources());
			fondo = new Fondo(mx.itesm.btp.R.drawable.fondobn, pantalla, getResources());

		}

	}


	/**
	 * Mueve el fondo en X
	 * @param x
	 */
	public void mueveX(double x){
		X=x;
		try {
			fondo.mueveX((int)x);
		} catch (NoContextProvidedException e) {
			// TODO Auto-generated catch block
			Log.d("ERROR", "No se cre� correctamente una pantalla");
		}
	}

	/**
	 * Mueve el fondo en Y
	 * @param y
	 */
	public void mueveY(double y){

		try{
			fondo.mueveY((int)y);
		}
		catch(Exception e){}
	}

	/**
	 * Asigna valores a la instancia de Y
	 * @param y
	 */
	public void setY(double y){
		Y=y;
	}

	/**
	 * Asigna valores a la instancia en X
	 * @param x
	 */
	public void setX(double x){
		X=x;
	}
	
	private void calcularXdOffset() {
		int ancho=getWidth();
		if(xp<ancho/2){
			xd=xp;
			offset=0;
		}else if(xd>fondo.getBitmap().getWidth()-ancho/2){
			xd=xp-(fondo.getBitmap().getWidth()-ancho);
			offset=fondo.getBitmap().getWidth()-ancho;
		}else{
			xd=ancho/2;
			offset=xp-ancho/2;
		}

	}

	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		cbm = canvas;
		if(!resized){
			mira = Bitmap.createScaledBitmap(mira, getCbm().getWidth(), getCbm().getHeight(), false);
			arrow=Bitmap.createScaledBitmap(arrow,getCbm().getWidth()/4, getCbm().getHeight()/3, false);
			resized=true;
		}
		canvas.drawRGB(0,0,0); 

		canvas.drawBitmap(fondo.getBitmap(), 0, 0, p);
		
		canvas.drawBitmap(mira, 0, 0, p);


		canvas.drawBitmap(imgbtn,canvas.getWidth()/60,(5)*canvas.getHeight()/7,p);
		getCbm().drawBitmap(vehiculoEnemigo.getBitmap(), xenemigo,yenemigo, p);


		///////////////////////////////////////////////////////
		////// Dibujar estos elementos
		///// Utilizando propiedades relativas a pantalla o a las propiedades internas de cada elemento (p.e. bala.getX())
		///// esta disponible pantalla.getWidth() y Height... para que se posicionen en todos los celulares bien
		///// no usar valores fijos :)
		//////////////////////////////////////////////////////

		//canvas.drawBitmap(imgMonito, 200, 500, p);
		//canvas.drawBitmap(imgbtn, x, 630,p);
		canvas.drawBitmap(pausebtn, 725,20, p);


		canvas.drawBitmap(arrow, canvas.getWidth()-canvas.getWidth()/4, canvas.getHeight()- canvas.getHeight()/3,p);
		marcarVida(vehiculoEnemigo, missile, canvas);
		if(Indicadorvida==0){
			canvas.drawBitmap(barra1, 0,0, p);
		}
		else if(Indicadorvida==1){
			canvas.drawBitmap(barra2, 0,0, p);
		}
		else if(Indicadorvida==2){
			canvas.drawBitmap(barra3, 0,0, p);
		}
		else if(Indicadorvida==3){
			canvas.drawBitmap(barra4, 0,0, p);
		}
		else if(Indicadorvida==4){
			canvas.drawBitmap(barra5, 0,0, p);
		}


		if(disparo==true){
			missile=BitmapFactory.decodeResource(getResources(), mx.itesm.btp.R.drawable.rockbn);
			missile=Bitmap.createScaledBitmap(missile, (int)Math.round(missile.getWidth()/i), (int)Math.round(missile.getHeight()/i), true);
			canvas.drawBitmap(missile, canvas.getWidth()/2-missile.getWidth()/2, canvas.getHeight()/2-missile.getHeight()/2,p);
			i+=.3;
			if(i==2.5){
				disparo=false;
				i=1.0;
				graficaFlag = true;
			}

		}

		canvas.drawText("AX: "+X+"\nAY: "+Y, 20, 20, p);


	}




	/**Obtiene el canvas
	 * @return canvas
	 */
	public Canvas getCbm(){
		if(cbm==null){
			cbm=new Canvas(getBm());
			cbm.drawRGB(0xFF, 0xFF, 0xFF);
		}
		return cbm;
	}

	/**
	 * Obtiene un ImageView
	 * @return iImageView
	 */
	public ImageView getIv(){
		if(iv==null){
			if(iv==null){
				iv = new ImageView(getContext());

			}
		}
		return iv;
	}

	/**
	 * Obtiene un Bitmap
	 * @return bitmap
	 */
	public Bitmap getBm(){
		if(bm==null){
			bm=Bitmap.createBitmap(getIv().getWidth(),getIv().getHeight(),Bitmap.Config.ARGB_8888);
		}
		return bm;
	}

	/**
	 * Ontiene un bitmap del crosspad
	 * @return imagen del crosspad
	 */
	public Bitmap getArrow(){
		return arrow;
	}

	/**
	 * Realiza el disparo de la bala.
	 */
	public void disparar(){
	
		disparo=true;
	}



	/**
	 * Obtiene el bitmap del boton de disparo
	 * @return imagen de boton
	 */
	public Bitmap getBoton(){
		return imgbtn;
	}

	/**
	 * Vuelve a pintar la pantalla
	 */
	public void refresh() {
		try {
			fondo.center();
		} catch (NoContextProvidedException e) {
			Log.d("ERROR", "No se cre� correctamente una pantalla");
		}

	}


	private void marcarVida(Enemigo enemigo, Bitmap bala,Canvas canvas) {


		if(canvas.getHeight()-enemigo.getBitmap().getHeight()==canvas.getHeight()-bala.getHeight()&&canvas.getWidth()-enemigo.getBitmap().getHeight()==canvas.getHeight()-bala.getWidth()){
			x=x++;
		}
		else{
			x=0;
		}
		switch(x){
		case 1: if(x==0){
			Indicadorvida=0;;
		}break;
		case 2:if(x==1){
			Indicadorvida=1;
		}break;
		case 3: if(x==2){
			Indicadorvida=2;
		}break;
		case 4: if(x==3){
			Indicadorvida=3;
		}break;
		case 5: if(x==4){
			Indicadorvida=4;
		}break;

		}


	}

	/**
	 * Metodo que baja vida al enemigo
	 * @param distanciaParaDano
	 */
	public void infringirDano(float distanciaParaDano) {
		vehiculoEnemigo.bajarVida(poderDeProyectil, distanciaParaDano);
		Log.d("DEP", "Se bajo:"+distanciaParaDano);
	}

	/**
	 * Metodo que nos regresa si el nivel ha terminado
	 * @return
	 */
	public boolean isOver() {
		return vehiculoEnemigo.isDead();
	}



}
