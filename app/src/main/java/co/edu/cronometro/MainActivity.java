package co.edu.cronometro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private boolean running;
    private int segundos = 0;

    private String[] guardados = new String[5];
    private int guardarNo = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runTimer();
        for(int i = 0; i<5;i++){
            guardados[i]="";
        }
    }

    public void iniciar(View view){
        running=true;
    }

    public void pausar(View view){
        running=false;
    }

    public void reinicio(View view){
        running=false;
        segundos =0;
    }

    public void guardar(View view){
        TextView vistaTiempo2 = (TextView)findViewById(R.id.time_guardados);
        int hora = segundos/3600;
        int minutes = (segundos %3600)/60;
        int secs = segundos %60;
        String tiempo2 = String.format(Locale.getDefault(),"%d:%02d:%02d",hora,minutes,secs);
        String resultado = "";
        guardados[guardarNo] = tiempo2;
        for(int i = 0; i<5;i++){
            resultado += guardados[i]+"\n";
        }
        guardarNo++;
        if (guardarNo==5)
            guardarNo = 0;
        vistaTiempo2.setText(resultado);
    }

    private void runTimer(){
        TextView vistaTiempo = (TextView)findViewById(R.id.time_text);
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hora = segundos/3600;
                int minutes = (segundos %3600)/60;
                int secs = segundos %60;
                String tiempo = String.format(Locale.getDefault(),"%d:%02d:%02d",hora,minutes,secs);
                vistaTiempo.setText(tiempo);
                if (running)
                    segundos++;
                handler.postDelayed(this,1000);

            }
        });

    }
}