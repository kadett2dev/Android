package kadett2dev.formtest;

/**
 * Created by kadett2dev on 04/11/2017.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ActivityB extends AppCompatActivity {

    //Variables que contendrán los TextView de la vista
    private TextView tv_name;
    private TextView tv_lastname;
    private TextView tv_age;
    private TextView tv_smoke;
    private TextView tv_status;

    //Método que se ejecuta cuando arranca la Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Vinculamos el código con la vista
        setContentView(R.layout.activity_b);

        //Vinculamos los TextView de la vista con su variable correspondiente
        tv_name = (TextView)findViewById(R.id.tv_name);
        tv_lastname = (TextView)findViewById(R.id.tv_lastname);
        tv_age = (TextView)findViewById(R.id.tv_age);
        tv_smoke = (TextView)findViewById(R.id.tv_smoke);
        tv_status = (TextView)findViewById(R.id.tv_status);

        //Recuperamos el container que contendrá la información
        //que proviene del Activity anterior
        Bundle container = getIntent().getExtras();

        //Recuperamos la información del container y la mostramos
        //en cada TextView correspondiente
        tv_name.setText(container.getString(Key.NAME));
        tv_lastname.setText(container.getString(Key.LASTNAME));
        tv_age.setText(container.getString(Key.AGE));

        String smoke = "";
        String status = "";
        //Si contiene el clave smoke
        if (container.getBoolean(Key.SMOKE)){
            //Recuperamos el texto correspondiente del archivo de strings
            smoke = getResources().getString(R.string.yes_smoke);
        }
        //Si no la contiene
        else{
            //Recuperamos el texot correspondiente del archivo de strings
            smoke = getResources().getString(R.string.no_smoke);
        }

        //Si contiene la clave married
        if (container.getBoolean(Key.MARRIED)){
            //Recuperamos el texto correspondiente del archivo de strings
            status = getResources().getString(R.string.is_married);
        }
        //Si no la contiene
        else{
            //Recuperamos el texto correspondiente del archivo de strings
            status = getResources().getString(R.string.is_single);
        }

        //Mostramos la información en los TextView correspondientes
        tv_smoke.setText(smoke);
        tv_status.setText(status);
    }
}
