package indicador;
import android.graphics.Bitmap;
	import android.graphics.BitmapFactory;
	import android.graphics.Canvas;
public class Vida {
	public int x=0;
	
	private int marcarVida(Bitmap enemigo, Bitmap bala) {
		if(enemigo.getHeight()==bala.getHeight()&&enemigo.getWidth()==bala.getWidth()){
			x=x++;
		}
		else{
			x=0;
		}
		return x;

	}
	
	
	
	

}
