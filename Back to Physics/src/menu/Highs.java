package menu;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import logica.HighScore;
import mx.itesm.btp.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Highs extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.highs);
		escribirHs();
	}
	
	private void escribirHs(){
		
		Typeface font = Typeface.createFromAsset(getAssets(), "fonts/spin.otf");
        TextView highs = (TextView)findViewById(mx.itesm.btp.R.id.hs);
        highs.setTypeface(font);
		boolean existe=false;
		String files[] = fileList();
		for(int i=0; i<files.length; i++){
			if(files[i]=="scores"){
				existe=true;
			}
		}
		StringBuffer str = new StringBuffer("Highscores\n");
		Scanner scores = null;
		if(existe){
			try {
				scores = new Scanner(openFileInput("scores"));
			} catch (FileNotFoundException e) {Log.i("ERROR", "No sirve la busqueda :S");}
		}
		int cont = 1;
		while(scores.hasNext()){
			HighScore tmp = new HighScore(scores.next(), scores.nextInt());
			str.append(cont+". "+tmp.toString()+"\n");
		}
		scores.close();
		highs.setText(str);
	}
}
