package graficas;

import java.util.LinkedList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Clase que contiene todos los sprites que se utilizaran en el juego
 * @author vero
 *
 */
public class Sprite {
        
        private LinkedList<Bitmap> listaImgs;
        private int indice;
        
        /**
         * Arreglo de administraci—n de los Bitmaps del proyecto
         * @param admRecursos
         * @param ids
         */
        public Sprite (Resources admRecursos, int []ids){
                listaImgs= new LinkedList<Bitmap>();
                for(int id:ids){
                        Bitmap bm= BitmapFactory.decodeResource(admRecursos, id);
                        listaImgs.add(bm);
                }
                indice=0;
        }
        
        /**
         * Dibuja el canvas
         * @param canvas
         * @param p
         */
        public void draw(Canvas canvas, Paint p){
                canvas.drawBitmap(listaImgs.get(indice), 100, 200, p);
        }
        
        /**
         * Cambio a la siguiente imagen
         */
        public void nextFrame(){
                indice=(indice+1)%listaImgs.size();
        }
}