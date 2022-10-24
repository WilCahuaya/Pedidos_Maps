package com.example.repartidor_wcq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.LocaleData;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Entidades.Pedido;

import static java.lang.Float.parseFloat;

public class RegistrarPedido extends AppCompatActivity {
    EditText edtCliente,edtDscripcion,edtCodPedido;
    Spinner spTipo;
    TextView txtDireccion;
    Button btnGuardarPedido;

    private List<Pedido> listPedido=new ArrayList<Pedido>();
    String sDireccion;

    private DatabaseReference mDatabase;


    float latitud;
    float longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_pedido);

        edtCliente=findViewById(R.id.edtCliente);
        edtDscripcion=findViewById(R.id.edtDescripcion);
        edtCodPedido=findViewById(R.id.edtCodPedido);
        spTipo=findViewById(R.id.spTipo);
        txtDireccion=findViewById(R.id.txtDireccion);
        btnGuardarPedido=findViewById(R.id.btnGuardarPedido);


        FirebaseApp.initializeApp(this);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        mDatabase=database.getReference();
//Recuperamos los valores de latitud y longitud del punto seleccionado en el mapa
        String mLat=getIntent().getStringExtra("mLat");
        String mLong=getIntent().getStringExtra("mlong");
        latitud=parseFloat(mLat);
        longitud=parseFloat(mLong);

        //convertir coordenadas en direcciones
        Geocoder geocoder=new Geocoder(getApplicationContext(), Locale.getDefault());
        try {
            List<Address> direccion=geocoder.getFromLocation(parseFloat(mLat),parseFloat(mLong),1);
            txtDireccion.setText(direccion.get(0).getAddressLine(0));
            sDireccion=direccion.get(0).getAddressLine(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnGuardarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarPedido();
            }
        });
    }

    // debemos enviar un objeto al firebase
    private void registrarPedido() {
        int codigo=Integer.parseInt(edtCodPedido.getText().toString());
        String cliente=edtCliente.getText().toString();
        String direccion=sDireccion;
        String descripcion=edtDscripcion.getText().toString();
        String tipo="";
        tipo=spTipo.getSelectedItem().toString();
        float mLat=latitud;
        float mLong=longitud;
        Date fecha=new Date();
        String fechaRegist=fecha.toString();

        Pedido pedido=new Pedido(codigo,cliente,direccion,descripcion,tipo,mLat,mLong,"n",fechaRegist);
        String id=mDatabase.push().getKey();
        mDatabase.child("Pedidos").child(id).setValue(pedido);

        Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show();

        Intent i=new Intent(RegistrarPedido.this,MainActivity.class);
        startActivity(i);
    
    }
}