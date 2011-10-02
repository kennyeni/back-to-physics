package graficas;

import exceptions.NoContextProvidedException;
import mx.itesm.btp.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View.OnTouchListener;

public class Juego extends View{
	private Canvas cbm;
	private ImageView iv;
	private Bitmap bm;
	private Paint p;
	public Fondo fondo;
	private Bitmap imgMonito;
	private Bitmap imgRoca;
	private Bitmap imgbtn;
	private Bitmap arrow;
	private Bitmap mira;
	private Bitmap barra1;
	private Bitmap barra2;
	private Bitmap barra3;
	private Bitmap barra4;
	private Bitmap barra5;
	private Bitmap missile;
	private int x=0;
	private boolean disparo;
	int missileworiginal;
	int missilehoriginal;
	int contador;
	
	public Juego(Context context, Pantalla pantalla){
		super(context);
		p=new Paint();
		pantalla.setContext(context);
		fondo = new Fondo(mx.itesm.btp.R.drawable.valley, pantalla, getResources());
		
		arrow = BitmapFactory.decodeResource(getResources(), mx.itesm.btp.R.drawable.cpad);
		mira=BitmapFactory.decodeResource(getResources(), mx.itesm.btp.R.drawable.crosshair2);
		imgMonito = BitmapFactory.decodeResource(getResources(), mx.itesm.btp.R.drawable.cat1);
		imgRoca= BitmapFactory.decodeResource(getResources(), mx.itesm.btp.R.drawable.rock);
		imgbtn= BitmapFactory.decodeResource(getResources(), mx.itesm.btp.R.drawable.btnb);
		barra1= BitmapFactory.decodeResource(getResources(), mx.itesm.btp.R.drawable.barra1);
		barra2= BitmapFactory.decodeResource(getResources(), mx.itesm.btp.R.drawable.barra2);
		barra3= BitmapFactory.decodeResource(getResources(), mx.itesm.btp.R.drawable.barra3);
		barra4= BitmapFactory.decodeResource(getResources(), mx.itesm.btp.R.drawable.barra4);
		barra5= BitmapFactory.decodeResource(getResources(), mx.itesm.btp.R.drawable.barra5);
		missile= BitmapFactory.decodeResource(getResources(), mx.itesm.btp.R.drawable.cat2);
		contador = 0;
		
		
		int missilew = missile.getWidth();
		int missileh = missile.getHeight();
		missile=Bitmap.createScaledBitmap(missile,5*missilew, 5*missileh, false);
		
		missileworiginal = missile.getWidth();
		missilehoriginal = missile.getHeight();
		disparo = false;
		
		

		






	}
	
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawRGB(0,0,0); 
		mira = Bitmap.createScaledBitmap(mira, canvas.getWidth(), canvas.getHeight(), false);
		canvas.drawBitmap(fondo.getBitmap(), 0, 0, p);
		
		canvas.drawBitmap(mira, 0, 0, p);
		canvas.drawBitmap(imgbtn, canvas.getWidth()-850, canvas.getHeight()-150,p);
		
		
		///////////////////////////////////////////////////////
		////// Dibujar estos elementos
		///// Utilizando propiedades relativas a pantalla o a las propiedades internas de cada elemento (p.e. bala.getX())
		///// esta disponible pantalla.getWidth() y Height... para que se posicionen en todos los celulares bien
		///// no usar valores fijos :)
		//////////////////////////////////////////////////////
		
		//canvas.drawBitmap(imgMonito, 200, 500, p);
		//canvas.drawBitmap(imgbtn, x, 630,p);
		
		
		arrow=Bitmap.createScaledBitmap(arrow,canvas.getWidth()/4, canvas.getHeight()/3, false);
		canvas.drawBitmap(arrow, canvas.getWidth()-canvas.getWidth()/4, canvas.getHeight()- canvas.getHeight()/3,p);
		
		
		
		missile=Bitmap.createScaledBitmap(missile, missileworiginal, missilehoriginal, false);
		canvas.drawBitmap(missile, canvas.getWidth()/2-missile.getWidth()/2, canvas.getHeight()/2-missile.getHeight(),p);
		
		
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

	public Canvas getCbm(){
		if(cbm==null){
			cbm=new Canvas(getBm());
			cbm.drawRGB(0xFF, 0xFF, 0xFF);
		}
		return cbm;
	}
	
	public Bitmap getBm(){
		if(bm==null){
			bm=Bitmap.createBitmap(iv.getWidth(),iv.getHeight(),Bitmap.Config.ARGB_8888);
		}
		return bm;
	}
	
	
	
	public Bitmap getArrow(){
		return arrow;
	}
	
	
	public void disparar(){
		disparo=true;
		contador=3;
		
	}
	
	public Bitmap getBoton(){
		return imgbtn;
	}
	

	public void refresh() {
		try {
			fondo.center();
		} catch (NoContextProvidedException e) {
			e.printStackTrace();
		}
		
	}
	
	private void marcarVida(Bitmap enemigo, Bitmap bala,Canvas canvas) {
		
		
		if(enemigo.getHeight()==bala.getHeight()&&enemigo.getWidth()==bala.getWidth()){
		x=x++;
	}
	else{
		x=0;
	}
	switch(x){
	case 1: if(x==0){
		canvas.drawBitmap(barra1, 200, 100,p);
	}break;
	case 2:if(x==1){
		canvas.drawBitmap(barra2, 200, 100,p);
	}break;
	case 3: if(x==2){
		canvas.drawBitmap(barra3, 200, 100,p);
	}break;
	case 4: if(x==3){
		canvas.drawBitmap(barra4, 200, 100,p);
	}break;
	case 5: if(x==4){
		canvas.drawBitmap(barra5, 200, 100,p);
	}break;
		
	}
		

}


}
