package kadett2dev.formtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

//Esta clase extiende de AppCompatActivity y además implementa OnClickListener para gestionar
//el onclick de la aplicación
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Creamos las variables con los elementos que vamos a interactuar
    private EditText et_name;
    private EditText et_lastname;
    private EditText et_age;
    private CheckBox cb_smoke;
    private RadioButton rb_married;
    private RadioButton rb_single;
    private Button b_send;

    //Método que se ejecuta cuando arranca la Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Vinculamos el código con la vista
        setContentView(R.layout.activity_main);

        //Vinculamos los TextView de la vista con su variable correspondiente
        et_name = (EditText)findViewById(R.id.et_name);
        et_lastname = (EditText)findViewById(R.id.et_lastname);
        et_age = (EditText)findViewById(R.id.et_age);
        cb_smoke = (CheckBox)findViewById(R.id.cb_smoke);
        rb_married = (RadioButton)findViewById(R.id.rb_married);
        rb_single = (RadioButton)findViewById(R.id.rb_single);

        b_send = (Button)findViewById(R.id.b_send);

        //Establecemos el onclick de este botón.
        //Al implementar OnClickListener, pasamos com parámetro el propio objeto
        b_send.setOnClickListener(this);

        /*
        //Otra manera de hacer el onclick seria asi.
        //Si hubiera más botones, cada botón se tendria que sobreescribir el onclick
        b_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              ...
            }
        });*/
    }

    //Sobreecribimos la función onclick al implementar OnClickListener
    @Override
    public void onClick(View view) {
        //Al ser un onClick común, necesitamos saber quien ha generado el click
        //Para eso usamos el switch cuya clave sera el id de la view que ha iniciado el evento
        switch (view.getId()){
            //En caso que haya sido el botón enviar, llamaremos a la función correspondiente
            case R.id.b_send: showData(); break;
        }
    }

    //Esta función preparará la información a enviar y llamará el siguiente Activity
    public void showData(){
        //Creamos el Intent y le decimos de donde venimos y donde queremos ir
        Intent i = new Intent(MainActivity.this, ActivityB.class);
        //Creamos el contenedor donde guardaremos la información a enviar
        Bundle container = new Bundle();

        //Añadimos la información de los campos de texto y la guardamos al contenedor
        container.putString(Key.NAME, et_name.getText().toString());
        container.putString(Key.LASTNAME, et_lastname.getText().toString());
        container.putString(Key.AGE, et_age.getText().toString());
        container.putBoolean(Key.SMOKE, cb_smoke.isChecked());
        container.putBoolean(Key.MARRIED, rb_married.isChecked());
        container.putBoolean(Key.SINGLE, rb_single.isChecked());

        //Añadimos el contenedor al intent
        i.putExtras(container);

        //Ejecutamos el siguiente Activity
        startActivity(i);
    }


}
