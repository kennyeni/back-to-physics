package graficas;

import java.util.LinkedList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;


public class Sprite {
        
        private LinkedList<Bitmap> listaImgs;
        private int indice;
        
        public Sprite (Resources admRecursos, int []ids){
                listaImgs= new LinkedList<Bitmap>();
                for(int id:ids){
                        Bitmap bm= BitmapFactory.decodeResource(admRecursos, id);
                        listaImgs.add(bm);
                }
                indice=0;
        }
        
        public void draw(Canvas canvas, Paint p){
                canvas.drawBitmap(listaImgs.get(indice), 100, 200, p);
        }
        
        public void nextFrame(){
                indice=(indice+1)%listaImgs.size();
        }
}