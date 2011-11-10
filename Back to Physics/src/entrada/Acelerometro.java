package entrada;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


public class Acelerometro {
	//Variables de instancia
	private float factorCorreccion[] = new float[2];
	private float promedio[][] = new float[3][2];
	public double X, Y;
	private int tiempos[] = new int[3];
	
	
	//Constantes del juego
	private final int refreshRate = 70; //Cada cuando se hace una lectura, se hace una lectura
	
	private final int sVel = 2, mVel = 5, hVel = 9;
	
	private final double upSlowBottomLimit = 0;
	private final double upSlowUpperLimit = -2;
	private final double upMedBottomLimit = -2;
	private final double upMedUpperLimit = -4;
	private final double upHiBottomLimit = -4;
	private final double upHiUpperLimit = -6;
	
	private final double downSlowBottomLimit = 0;
	private final double downSlowUpperLimit = 1.3;
	private final double downMedBottomLimit = 1.3;
	private final double downMedUpperLimit = 2.6;
	private final double downHiBottomLimit = 2.6;
	private final double downHiUpperLimit = 4;
	
	
	
	private final double leftSlowBottomLimit = -0.3;
	private final double leftSlowUpperLimit = -2.6;
	private final double leftMedBottomLimit = -2.2;
	private final double leftMedUpperLimit = -5.2;
	private final double leftHiBottomLimit = -5.2;
	private final double leftHiUpperLimit = -8;
	
	private final double rightSlowBottomLimit = 0.3;
	private final double rightSlowUpperLimit = 2.1;
	private final double rightMedBottomLimit = 2.1;
	private final double rightMedUpperLimit = 4.1;
	private final double rightHiBottomLimit = 4.1;
	private final double rightHiUpperLimit = 6.3;

	
	Sensor acelerometro = null;
	SensorManager sensorManager = null;
	
	/**
	 * @author Kenny
	 * Clase que lee los registros del acelerometro, lo calibra, y devuelve los valores de cambio adecuados
	 * cuando se les solicita a traves de getMovimiento();
	 */
	
	
	
	/**
	 * Se inicializa el sensor y se registra el listener				
	 * @param act
	 */
	public Acelerometro(Activity act){
		sensorManager = (SensorManager) act.getSystemService(Context.SENSOR_SERVICE);
		acelerometro = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
		sensorManager.registerListener(listener, acelerometro, SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	
	
	SensorEventListener listener = new SensorEventListener() {
		
		/**
		 * MŽtodo que refresca la pantalla al momento de mover el aceler—metro
		 */
		
		@Override
		public void onSensorChanged(SensorEvent event) {
			float valores[] = event.values;
			int tiempo = (int) (event.timestamp/1000000);
			
			if(tiempo > tiempos[0]+(3*refreshRate)){
				tiempos[0] = tiempo;
				promedio[0][0] = valores[1];
				promedio[0][1] = valores[2];
			} else if(tiempo > tiempos[1]+(2*refreshRate)){
				tiempos[1] = tiempo;
				promedio[1][0] = valores[1];
				promedio[1][1] = valores[2];
			} else if(tiempo > tiempos[2]+(1*refreshRate)){
				tiempos[2] = tiempo;
				promedio[2][0] = valores[1];
				promedio[2][1] = valores[2];
			}	
			
			float xTmp = (promedio[0][1]+promedio[1][1]+promedio[2][1])/3;
			float yTmp = (promedio[0][0]+promedio[1][0]+promedio[2][0])/3;
			
			Y = xTmp-factorCorreccion[0];
			X = yTmp-factorCorreccion[1];
			
		}
		
		public void onAccuracyChanged(Sensor sensor, int accuracy) {};
	};
	
	public void calibracion(){ //Vero
		
	}
	
	
	/**
	 * Clase que regresa un arreglo de Y
	 * @return valor
	 */
	public int getY(){
		if(Y > downSlowBottomLimit && Y < downSlowUpperLimit){
			return sVel;
		}
		if(Y >= downMedBottomLimit && Y < downMedUpperLimit){
			return mVel;
		}
		if(Y >= downHiBottomLimit && Y < downHiUpperLimit){
			return hVel;
		}
		
		
		if(Y < upSlowBottomLimit && Y > upSlowUpperLimit){
			return -sVel;
		}
		if(Y <= upMedBottomLimit && Y > upMedUpperLimit){
			return -mVel;
		}
		if(Y <= upHiBottomLimit && Y > upHiUpperLimit){
			return -hVel;
		}
		return 0;
		
	}
	
	/**
	 * Clase que regresa un arrego de X
	 * @return valor
	 */
	public int getX(){
		if(X > rightSlowBottomLimit && X < rightSlowUpperLimit){
			return sVel;
		}
		if(X >= rightMedBottomLimit && X < rightMedUpperLimit){
			return mVel;
		}
		if(X >= rightHiBottomLimit && X < rightHiUpperLimit){
			return hVel;
		}
		
		
		if(X < leftSlowBottomLimit && X > leftSlowUpperLimit){
			return -sVel;
		}
		if(X <= leftMedBottomLimit && X > leftMedUpperLimit){
			return -mVel;
		}
		if(X <= leftHiBottomLimit && X > leftHiUpperLimit){
			return -hVel;
		}
		return 0;
		
	}
	

}
