package kadett2dev.twoactivities;

/**
 * Created by kadett2dev
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et_name;
    private Button b_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Unimos el código con los elementos de la vista
        et_name = (EditText)findViewById(R.id.et_name);
        b_submit = (Button)findViewById(R.id.b_submit);

        //Establecemos la funcionalidad del botón
        b_submit.setOnClickListener(this);
    }

    //Vamos a sobreescribir el método onclick
    @Override
    public void onClick(View view) {
        //Según que elemento se haya seleccionado, se ejecutará un case del switch,
        //aunque en este caso, solo tenemos 1 case.
        switch (view.getId()){
            case R.id.b_submit:callSecondActivity();break;
        }
    }

    public void callSecondActivity(){
        //Creamos un Intent que nos llevará del MainActivity al SecondActivity
        Intent i = new Intent(MainActivity.this, SecondActivity.class);
        //Creamos el contenedor
        Bundle container = new Bundle();
        //Ponemos en el contenedor el contenido del campo de texto
        container.putString("name", et_name.getText().toString());
        //Añadimos el contenedor en el Intent
        i.putExtras(container);
        //Llamamos la nueva actividad.
        startActivity(i);
    }
}
