package menu;

import mx.itesm.btp.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class Loading extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.loading);
		ProgressBar barra = (ProgressBar) findViewById(R.id.progressBar1);
		
		
	}

}
