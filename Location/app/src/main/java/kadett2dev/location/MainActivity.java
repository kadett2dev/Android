package kadett2dev.location;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity implements LocationListener {
    private static final long TIEMPO_MIN = 10 * 1000; // 10 segundos
    private static final long DISTANCIA_MIN = 5; // 5 metros
    private static final String[] A = {"n/d", "preciso", "impreciso"};
    private static final String[] P = {"n/d", "bajo", "medio", "alto"};
    private static final String[] E = {"fuera de servicio",
            "temporalmente no disponible ", "disponible"};
    private LocationManager manejador;
    private String proveedor;
    private TextView salida;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //A partir de Android 6, es obligatorio poner este IF
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION},123);
        }
        salida = (TextView) findViewById(R.id.salida);
        //Le pedimos al sistema que nos de su sistema de localización
        manejador = (LocationManager) getSystemService(LOCATION_SERVICE);
        log("Proveedores de localización: \n ");
        muestraProveedores();
        Criteria criterio = new Criteria();
        //Le establecemos los criterios para la localización
        criterio.setCostAllowed(false);
        criterio.setAltitudeRequired(false);
        criterio.setAccuracy(Criteria.ACCURACY_FINE);
        //Le pedimos el mejor proveedor según los criterios deseados
        proveedor = manejador.getBestProvider(criterio, true);
        log("Mejor proveedor: " + proveedor + "\n");
        log("Comenzamos con la última localización conocida:");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION},123);
        }
        Location localizacion = manejador.getLastKnownLocation(proveedor);
        muestraLocaliz(localizacion);
    }

    //Esta función nos hará de LOG casero
    public void log(String text){
        salida.append(text + "\n");
    }

    //Nos muestra la información de la localización
    public void muestraLocaliz(Location location){
        if (location != null){
            log(location.toString() + "\n");
        }
        else{
            log("Unknown location");
        }
    }

    //Nos muesta la información de todos los proveedores
    private void muestraProveedores(){
        log("LOCATION PROVIDERS: \n");
        List<String> providers = manejador.getAllProviders();
        for(String provider: providers){
            showProviderData(provider);
        }
    }

    //Nos muestra la información del proveedor deseado
    private void showProviderData(String proveedor) {
        LocationProvider info = manejador.getProvider(proveedor);
        log("LocationProvider[ " + "getName=" + info.getName()
                + ", isProviderEnabled="
                + manejador.isProviderEnabled(proveedor) + ", getAccuracy="
                + A[Math.max(0, info.getAccuracy())] + ", getPowerRequirement="
                + P[Math.max(0, info.getPowerRequirement())]
                + ", hasMonetaryCost=" + info.hasMonetaryCost()
                + ", requiresCell=" + info.requiresCell()
                + ", requiresNetwork=" + info.requiresNetwork()
                + ", requiresSatellite=" + info.requiresSatellite()
                + ", supportsAltitude=" + info.supportsAltitude()
                + ", supportsBearing=" + info.supportsBearing()
                + ", supportsSpeed=" + info.supportsSpeed() + " ]\n");
    }


    //Cuando la localización ha cambiado, se ejecuta este método
    @Override
    public void onLocationChanged(Location location) {
        log("NEW LOCATION");
        muestraLocaliz(location);
    }

    //Cuando el estatus del proveedor ha cambiado, se ejecuta este método
    @Override
    public void onStatusChanged(String s, int state, Bundle bundle) {
        log("PROVIDER STATE CHANGED: " + proveedor+", STATE= " + E[Math.max(0, state)] + ", EXTRAS= " + bundle + "\n" );
    }

    //Cuando se habilita un proveedor, se ejecuta este método
    @Override
    public void onProviderEnabled(String s) {
        log("ENALED PROVIDER " + proveedor + "\n");
    }

    //Cuando se deshabilita un proveedor, se ejecuta este método
    @Override
    public void onProviderDisabled(String s) {
        log("DISABLED PROVIDER " + proveedor + "\n");
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onResume() {
        super.onResume();
        //A partir de Android 6, es obligatorio poner este IF
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION},123);
        }

        manejador.requestLocationUpdates(proveedor, TIEMPO_MIN, DISTANCIA_MIN, this);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
