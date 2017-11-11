package kadett2dev.twoactivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by kadett2dev
 */

public class SecondActivity extends AppCompatActivity {
    private TextView tv_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        //Unimos el TextView con el código
        tv_name = (TextView)findViewById(R.id.tv_name);

        //Recuperamos el contenedor de la actividad anterior
        Bundle container = getIntent().getExtras();

        //Mostramos la información introducida en la actividad anterior en el TextView de esta Activity
        tv_name.setText(container.getString("name"));
    }
}

