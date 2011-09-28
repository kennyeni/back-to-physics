package graficas;

import java.util.LinkedList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Juego extends View {
	
	private float t;
	private Paint p;
	private LinkedList<Coordenadas> lista = new LinkedList<Coordenadas>();
	
	public Juego(Context contexto){
		super(contexto);
		p = new Paint();
	}
	
	private int getY(float tp, Canvas canvas){
		t=tp;
		int formula = ((int)Math.round((.5*9.8*tp*tp)));
		
		return formula;
	}
	
	private int getX(float tp, Canvas canvas){
		t=tp;
		int formula = (int) (tp*25);
		return formula;
	}
	
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		int X= getX(t,canvas);
		int Y= getY(t,canvas);
		String info = "Pos X: "+X+" Pos Y: "+Y+" T: "+t+" Width: "+canvas.getWidth()+" Height: "+canvas.getHeight();
		canvas.drawRGB(0,0,0);
		t +=.09;
		
		if(t%.4<=.09){
			lista.add(new Coordenadas(X,Y));
		}
		LinkedList<Coordenadas> listaTMP = new LinkedList<Coordenadas>(lista);
		while(!listaTMP.isEmpty()){
			Coordenadas tmp = listaTMP.poll();
			p.setColor(Color.rgb(79,148,205));
			canvas.drawCircle(tmp.getX(),tmp.getY(),10,p);
			canvas.drawText("X: "+tmp.getX()+" Y: "+tmp.getY(), tmp.getX()+11 , tmp.getY(), p);
		}
		
		p.setColor(Color.GREEN);
		if(X>canvas.getWidth()||Y>canvas.getHeight()){
			t=0;
			lista.clear();
		}
		canvas.drawCircle(X,Y,10,p);
		p.setColor(Color.WHITE); 
		p.setTextSize(20); 
		canvas.drawText(info, 10, canvas.getHeight()-100, p);
		
		super.onDraw(canvas);
		
	}

}
