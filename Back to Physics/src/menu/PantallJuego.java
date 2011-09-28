package menu;

import graficas.Juego;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class PantallJuego extends Activity implements Runnable
{
	private Juego juego;
	private boolean corriendo;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	
		juego = new Juego (this);
		setContentView(juego);
	}
	
	@Override
	protected void onResume() 
	{
		super.onResume();
		//Iniciar animacion
		Thread th = new Thread(this);
		th.start();
		Toast.makeText(this, "Iniciando animacion", Toast.LENGTH_SHORT).show();
;	}
	
	@Override
	protected void onStop() 
	{
		super.onStop();
		corriendo = false;
	}
	
	
	
	@Override
	public void run() 
	{
		corriendo = true;
		while (corriendo)
		{
			juego.leerEntrada(); // Leer la entrada, Detectar eventos
			juego.actualizar(); //ctualizar juego
			juego.postInvalidate();      //Dibuja un juego   //Ejecuta de manera indirecta el método onDraw
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
			}
		}
	}
}
