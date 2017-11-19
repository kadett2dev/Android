package kadett2dev.lifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Se llama en la creación de la actividad. Se utiliza para realizar todo tipo de
    //inicializaciones, como la creación de la interfaz de usuario o la inicialización de estructuras de
    //datos. Puede recibir información de estado de la actividad (en una instancia de la clase
    //Bundle), por si se reanuda desde una actividad que ha sido destruida y vuelta a crear.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
    }

    //Nos indica que la actividad está a punto de ser mostrada al usuario.
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }

    //Indica que la actividad va a volver a ser representada después de haber pasado
    //por onStop().
    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
    }

    //Se llama cuando la actividad va a comenzar a interactuar con el usuario. Es un
    //buen lugar para lanzar las animaciones y la música.
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }

    //Indica que la actividad está a punto de ser lanzada a segundo plano, normalmente
    //porque otra actividad es lanzada. Es el lugar adecuado para detener animaciones, música o
    //almacenar los datos que estaban en edición.
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
    }

    //La actividad ya no va a ser visible para el usuario. Ojo si hay muy poca memoria, es
    //posible que la actividad se destruya sin llamar a este método.
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }

    //Se llama antes de que la actividad sea totalmente destruida. Por ejemplo, cuando
    //el usuario pulsa el botón de volver o cuando se llama al método finish(). Ojo si hay muy poca
    //memoria, es posible que la actividad se destruya sin llamar a este método.
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }
}
