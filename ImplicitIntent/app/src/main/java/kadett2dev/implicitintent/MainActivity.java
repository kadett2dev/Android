package kadett2dev.implicitintent;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button BtnWeb, BtnPhone, BtnMaps, BtnPhoto, BtnMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Vinculamos los botones de la vista con el código.
        //Establecemos el onclick del botón
        BtnWeb = (Button) findViewById(R.id.btn_web);
        BtnWeb.setOnClickListener(this);

        BtnPhone = (Button) findViewById(R.id.btn_phone);
        BtnPhone.setOnClickListener(this);

        BtnMaps = (Button) findViewById(R.id.btn_google_maps);
        BtnMaps.setOnClickListener(this);

        BtnPhoto = (Button) findViewById(R.id.btn_photo);
        BtnPhoto.setOnClickListener(this);

        BtnMail = (Button) findViewById(R.id.btn_mail);
        BtnMail.setOnClickListener(this);

    }

    //Función que se ejecturá cuando se haga click en los botones que estan sujetos
    //a onclicklistener
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //Si se ha hecho click en el botón web
            case R.id.btn_web:
                callWeb();
                break;
            //Si se ha hecho click en el botón teléfono
            case R.id.btn_phone:
                callPhone();
                break;
            //Si se ha hecho click en el botón google maps
            case R.id.btn_google_maps:
                callMap();
                break;
            //Si se ha hecho click en el botón foto
            case R.id.btn_photo:
                callPhoto();
                break;
            //Si se ha hecho click en el botón correo
            case R.id.btn_mail:
                callMail();
                break;
        }
    }

    public void callWeb() {
        //Abrirá la página de google.cat
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.cat"));
        startActivity(i);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void callPhone() {
        //Abrirá el gestor de llamada y directamente hará la llamada a ese número
        //Esta función necesitará de permisos
        Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel: 666666666"));
        //Desde la version 6 de Android, es obligatorio poner este IF
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 123);
            return;
        }

        startActivity(i);
    }

    public void callMap() {
        //Abrirá Google Maps situándose a la posición indicada (Barcelona)
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:41.387016,2.170036"));
        startActivity(i);
    }

    public void callPhoto(){
        //Abrirá la cámara
        Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivity(i);
    }

    public void callMail(){
        //Prepara la información para mandar por email.
        //Si no hay ninguna aplicación por defecto, pide la aplicación deseada para realizar la acción
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "This is my subject");
        i.putExtra(Intent.EXTRA_TEXT, "Hello friend!");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"fake@mail.com"});
        startActivity(i);
    }
}

