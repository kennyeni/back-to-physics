package logica;

import graficas.Coordenadas;

import java.util.LinkedList;

/**
 * Clase estatica que regresa una matriz de puntos a graficar
 * @author Kenny
 *
 */

public class Fisica {
	
	final static int MAX_PUNTOS = 30;
	
	/**
	 * Clase que regresa una LL de puntos a graficar en la parte aerea
	 * @param velocidad
	 * @param theta
	 * @param phi
	 * @param gravedad
	 * @return puntos
	 */
	public static LinkedList<Coordenadas> puntosAereos(double v, double theta, double phi, double g){
		LinkedList<Coordenadas> puntos = new LinkedList<Coordenadas>();
		double radian = Math.PI/180;
		double vx = v*Math.cos(phi*radian);
		double vy = v*Math.sin(phi*radian);
		double t = 2*vy/g;
		double intervalo = t/MAX_PUNTOS;
		for(double ti = 0, a=0; ti < MAX_PUNTOS+1; a+=intervalo, ti++){
			double hyp = vx*a;
			float x = (float) (hyp*Math.cos(theta*radian));
			float y = (float) (hyp*Math.sin(theta*radian));
			puntos.add(new Coordenadas(x, y));
		}
		
		return puntos;
	}
	
	/**
	 * Clase que regresa una lista de LL de puntos a graficar en la parte lateral
	 * @param velocidad
	 * @param theta
	 * @param phi
	 * @param gravedad
	 * @return puntos
	 */

	public static LinkedList<Coordenadas> puntosLaterales(double v, double theta, double phi, double g){
		LinkedList<Coordenadas> puntos = new LinkedList<Coordenadas>();
		double radian = Math.PI/180;
		double vx = v*Math.cos(phi*radian);
		double vy = v*Math.sin(phi*radian);
		double t = 2*vy/g;
		double intervalo = t/MAX_PUNTOS;
		for(double ti = 0, a=0; ti < MAX_PUNTOS+1; a+=intervalo, ti++){
			float x = (float) (vx*a);
			float y = (float) (x*Math.tan(phi*radian)-(g*x*x/(2*(vx*vx))));
			puntos.add(new Coordenadas(x, y));
		}
		return puntos;
	}
	

}
