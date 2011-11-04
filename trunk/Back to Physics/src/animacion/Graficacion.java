package animacion;

import mx.itesm.btp.R;
import graficas.Pantalla;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

public class Graficacion extends Activity {
	
	Pantalla pantalla = null;
	boolean corriendo = true;
	ImageView graficaAerea = null;
	ImageView graficaLateral = null;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		pantalla = new Pantalla(this);
		//setContentView(R.layout.grafica);
	}
	
	
	public void run() {
		while (corriendo) {
			graficaAerea.postInvalidate();
			graficaLateral.postInvalidate();
			try {
				Thread.sleep(34);
			} catch (Exception e) {}
		}
	}
	

}
