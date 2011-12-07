package graficas;

import personajes.Enemigo;
import personajes.Principal;
import exceptions.NoContextProvidedException;


import menu.PantallaJuego;
import mx.itesm.btp.BacktoPhysics;
import mx.itesm.btp.R;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
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
			Log.d("ERROR", "No se cre— correctamente una pantalla");
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
		
		canvas.drawRGB(0,0,0); 
		mira = Bitmap.createScaledBitmap(mira, canvas.getWidth(), canvas.getHeight(), false);
		canvas.drawBitmap(fondo.getBitmap(), 0, 0, p);
		canvas.drawBitmap(vehiculoEnemigo.getBitmap(), 100,100, p);
		canvas.drawBitmap(mira, 0, 0, p);
		
		
		canvas.drawBitmap(imgbtn,canvas.getWidth()/60,(5)*canvas.getHeight()/7,p);
		
		
		///////////////////////////////////////////////////////
		////// Dibujar estos elementos
		///// Utilizando propiedades relativas a pantalla o a las propiedades internas de cada elemento (p.e. bala.getX())
		///// esta disponible pantalla.getWidth() y Height... para que se posicionen en todos los celulares bien
		///// no usar valores fijos :)
		//////////////////////////////////////////////////////
		
		//canvas.drawBitmap(imgMonito, 200, 500, p);
		//canvas.drawBitmap(imgbtn, x, 630,p);
		canvas.drawBitmap(pausebtn, 0,0, p);
		
		arrow=Bitmap.createScaledBitmap(arrow,canvas.getWidth()/4, canvas.getHeight()/3, false);
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
		
//		missile=Bitmap.createScaledBitmap(missile, missileworiginal, missilehoriginal, false);
//		canvas.drawBitmap(missile, canvas.getWidth()/2-missile.getWidth()/2, canvas.getHeight()/2-missile.getHeight()/2,p);

		canvas.drawText("AX: "+X+"\nAY: "+Y, 20, 20, p);
		
		
		//if(disparo==true){
			
			//if(contador!=0){
				//missile=Bitmap.createScaledBitmap(missile, (int).9*missile.getWidth(), (int).9*missile.getHeight(), false);
				//canvas.drawBitmap(missile, canvas.getWidth()/2-missile.getWidth()/2, canvas.getHeight()/2-missile.getHeight(),p);
				//contador --;
			//}else{
			//	disparo = false;
		//	}
			
			
		//}else{
			
			//missile=Bitmap.createScaledBitmap(missile, missileworiginal, missilehoriginal, false);
			//canvas.drawBitmap(missile, canvas.getWidth()/2-missile.getWidth()/2, canvas.getHeight()/2-missile.getHeight(),p);
		//}
		
		//canvas.drawBitmap(imgRoca, 230, -100,p);
	}
	
	
	
		///////////////////////////////////////////////////////
		////// Pasar estos a municion
		///// Utilizando propiedades relativas a pantalla
		//////////////////////////////////////////////////////
	
	/*
	protected void dibujarRoca(Canvas canvas,int y, Paint p){
		super.onDraw(canvas);
		canvas.drawBitmap(imgRoca, y, 230,p);
		
	}
	
	
	
	protected void lanzarRoca(Canvas canvas){
			super.onDraw(canvas);
			dibujarRoca(canvas, y, p);
			y = (y+20)%canvas.getWidth();
		
	}
	
	*/
	
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
		//disparo=true;
		//contador=3;
//		missile= BitmapFactory.decodeResource(getResources(), mx.itesm.btp.R.drawable.cat2);
//		int tmp1 = getCbm().getWidth();
//		int tmp2 = missile.getWidth();
//		int x=  (tmp1-tmp2)/2;
//		int y= getCbm().getHeight()/2-missile.getHeight()/2;
//		for(int i=1;i<=3;i++){
//		          missile = Bitmap.createScaledBitmap(missile, ((int) missile.getWidth()/i), ((int) missile.getHeight()/i), true);
//		          this.invalidate();
//		          try{
//		              Thread.sleep(32);
//		          } catch(Exception e) {}
		
		disparo=true;
		}
		
		/*
		
		Context context = this.getContext();
		CharSequence text = "Hello toast!";
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		*/
		
	
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
			Log.d("ERROR", "No se cre— correctamente una pantalla");
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

	
	public void infringirDano(float distanciaParaDano) {
		vehiculoEnemigo.bajarVida(poderDeProyectil, distanciaParaDano);
		Log.d("DEP", "Se bajo:"+distanciaParaDano);
	}
	


}
