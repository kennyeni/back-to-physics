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
	
	public Juego(Context context, Pantalla pantalla){
		super(context);
		p=new Paint();
		pantalla.setContext(context);
		fondo = new Fondo(mx.itesm.btp.R.drawable.valley, pantalla, getResources());
		
		arrow = BitmapFactory.decodeResource(getResources(), mx.itesm.btp.R.drawable.arrow);
		mira=BitmapFactory.decodeResource(getResources(), mx.itesm.btp.R.drawable.crosshair2);
		imgMonito = BitmapFactory.decodeResource(getResources(), mx.itesm.btp.R.drawable.cat1);
		imgRoca= BitmapFactory.decodeResource(getResources(), mx.itesm.btp.R.drawable.rock);
		imgbtn= BitmapFactory.decodeResource(getResources(), mx.itesm.btp.R.drawable.btn1);

	}
	
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawRGB(0,0,0); 
		mira = Bitmap.createScaledBitmap(mira, canvas.getWidth(), canvas.getHeight(), false);
		canvas.drawBitmap(fondo.getBitmap(), 0, 0, p);
		
		canvas.drawBitmap(mira, 0, 0, p);
		
		
		///////////////////////////////////////////////////////
		////// Dibujar estos elementos
		///// Utilizando propiedades relativas a pantalla o a las propiedades internas de cada elemento (p.e. bala.getX())
		///// esta disponible pantalla.getWidth() y Height... para que se posicionen en todos los celulares bien
		///// no usar valores fijos :)
		//////////////////////////////////////////////////////
		
		//canvas.drawBitmap(imgMonito, 200, 500, p);
		//canvas.drawBitmap(imgbtn, x, 630,p);
		//canvas.drawBitmap(arrow, canvas.getWidth()-140,630 ,p);
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

	public void refresh() {
		try {
			fondo.center();
		} catch (NoContextProvidedException e) {
			e.printStackTrace();
		}
		
	}
}
