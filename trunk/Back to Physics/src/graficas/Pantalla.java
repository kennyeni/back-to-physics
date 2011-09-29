package graficas;

import exceptions.NoContextProvidedException;
import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

public class Pantalla {
	

	private Context context = null;
	private int width;
	private int height;
	private Display display = null;
	
	public Pantalla(Context context){
		this.context = context;
		display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		width = display.getWidth();
		height = display.getHeight();
	}
	
	public Pantalla(){
		//Se incializo una pantalla sin propiedades... :(
	}
    
    public int getHeight() throws NoContextProvidedException{
    	if(this.context==null)
    		throw new NoContextProvidedException("No hay un contexto definido para Pantalla!");
    	return height;
    }
    
    public int getWidth() throws NoContextProvidedException{
    	if(this.context==null)
    		throw new NoContextProvidedException("No hay un contexto definido para Pantalla!");
    	return width;
    }
    
    public void setContext(Context context){
    	this.context = context;
    	display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		width = display.getWidth();
		height = display.getHeight();
    }

}
