package com.example.repartidor_wcq;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.repartidor_wcq.databinding.ActivityRutaMapsPedidoBinding;

import static java.lang.Float.parseFloat;

public class RutaMapsPedido extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityRutaMapsPedidoBinding binding;

    float latitud;
    float longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityRutaMapsPedidoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //Recuperamos los valores de latitud y longitud del punto seleccionado en el mapa
        String mLat=getIntent().getStringExtra("latitud");
        String mLong=getIntent().getStringExtra("longitud");
        String cliente=getIntent().getStringExtra("cliente");
        String direccion=getIntent().getStringExtra("direccion");
        latitud=parseFloat(mLat);
        longitud=parseFloat(mLong);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        // Add a marker in Sydney and move the camera
        LatLng destPedido = new LatLng(latitud, longitud);
        mMap.addMarker(new MarkerOptions().position(destPedido).title("Dirección: "+direccion).title("Cliente: "+cliente));
        CameraPosition cameraPosition=CameraPosition.builder().target(destPedido).zoom(15).build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }
}