package graficas;

import exceptions.NoContextProvidedException;
import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

/**
 * Esta clase es el contenedor del juego
 * @author vero
 *
 */

public class Pantalla {
	

	private Context context = null;
	private int width;
	private int height;
	private Display display = null;
	
	/**
	 * Obtine los datos de la pantalla
	 * @param context
	 */
	public Pantalla(Context context){
		this.context = context;
		display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		width = display.getWidth();
		height = display.getHeight();
	}
	
	public Pantalla(){
		//Se incializo una pantalla sin propiedades... :(
	}
    
	/**
	 * Obtiene la altura de la pantalla
	 * @return altura de la pantalla
	 * @throws NoContextProvidedException
	 */
    public int getHeight() throws NoContextProvidedException{
    	if(this.context==null)
    		throw new NoContextProvidedException("No hay un contexto definido para Pantalla!");
    	return height;
    }
    
    /**
     * Obtiene el ancho de la pantalla
     * @return ancho de pantalla
     * @throws NoContextProvidedException
     */
    public int getWidth() throws NoContextProvidedException{
    	if(this.context==null)
    		throw new NoContextProvidedException("No hay un contexto definido para Pantalla!");
    	return width;
    }
    
    /**
     * Asigna valores de instancia al contet para mnejar la altura y ancho de la panatalla
     * @param context
     */
    public void setContext(Context context){
    	this.context = context;
    	display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		width = display.getWidth();
		height = display.getHeight();
    }

}
