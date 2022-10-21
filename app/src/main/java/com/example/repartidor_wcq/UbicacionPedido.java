package com.example.repartidor_wcq;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.repartidor_wcq.databinding.ActivityUbicacionPedidoBinding;

import org.jetbrains.annotations.NotNull;

public class UbicacionPedido extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityUbicacionPedidoBinding binding;

    //Varibles de latitud y longitud
    double mLat,mlong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUbicacionPedidoBinding.inflate(getLayoutInflater());
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

        // Add a marker in Sydney and move the camera
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        LatLng hyo = new LatLng(-12.068148340882951, -75.21002132643184);
        mMap.addMarker(new MarkerOptions().position(hyo).title("Huancayo - Perù"));
        CameraPosition cameraPosition=CameraPosition.builder().target(hyo).zoom(15).build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        //Capturar latitud y longitud de un punto en especifico

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(@NonNull @NotNull LatLng latLng) {
                mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.repartidor))
                        .title("Punto")
                        .snippet("Punto de entrega")
                        .position(latLng)
                        .anchor(0.5F,0.5f));
                mLat=latLng.latitude;
                mlong=latLng.longitude;
                //Toast.makeText(UbicacionPedido.this, mLat+" <> "+mlong, Toast.LENGTH_LONG).show();
                Intent i=new Intent(UbicacionPedido.this,RegistrarPedido.class);
                i.putExtra("mLat",""+mLat);
                i.putExtra("mlong",""+mlong);
                startActivity(i);
            }
        });
    }
}