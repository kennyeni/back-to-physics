package preferencias;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

public class Preferencias extends Activity {
	public static final String PREFS = "Pref";
	
	
	protected void onCreate(Bundle state) {
		super.onCreate(state);
		
		SharedPreferences settings = getSharedPreferences(PREFS, 0);
		boolean silent = settings.getBoolean("Silencio", false);
		setSilent (silent);
	}
	
	private void setSilent(boolean silent) {
		SharedPreferences settings = getSharedPreferences (PREFS, 0);
		SharedPreferences.Editor editor = settings.edit();
		
		editor.commit();
		
	}


}
