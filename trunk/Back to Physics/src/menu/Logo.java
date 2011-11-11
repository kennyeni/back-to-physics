package menu;

import java.util.Timer;

import java.util.TimerTask;

import mx.itesm.btp.BacktoPhysics;
import mx.itesm.btp.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

public class Logo extends Activity {

@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.pantallalogo); 
}



@Override
protected void onResume() {
super.onResume();
Timer reloj = new Timer();
TimerTask lanzaPrincipal = new TimerTask() {

@Override
public void run() {
Intent intencion = new Intent (Logo.this, menu.PantallaMemes.class); 
startActivity(intencion);

}
};
reloj.schedule(lanzaPrincipal, 4000);

}


}