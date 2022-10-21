package com.example.repartidor_wcq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import Entidades.Pedido;

import static java.lang.String.valueOf;

public class ListarPedido extends AppCompatActivity {
    ListView lstViewPedidosEntregar;

    //objeto
    List<Pedido> listaPedidos=new ArrayList<Pedido>();
    ArrayAdapter<Pedido> arrayAdapterPedido;

    //BD mDatabase
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pedido);

        lstViewPedidosEntregar=findViewById(R.id.lstPedidosEntregar);

        //Inicializar la BD de firbase
        FirebaseApp.initializeApp(this);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        mDatabase=database.getReference();
         listarPedidosNuevos();
    }

    private void listarPedidosNuevos() {
        DatabaseReference pedidosReference=mDatabase.child("Pedidos");
        //filtro
        Query pedidosNuevos=pedidosReference.orderByChild("estado").equalTo("n");
        listaPedidos.clear();

        pedidosNuevos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                listaPedidos.clear();
                for (DataSnapshot objSnapshot : snapshot.getChildren()){
                    Pedido p= objSnapshot.getValue(Pedido.class);
                    listaPedidos.add(p);
                }
                arrayAdapterPedido=new ArrayAdapter<Pedido>(ListarPedido.this, android.R.layout.simple_list_item_1,listaPedidos);
                lstViewPedidosEntregar.setAdapter(arrayAdapterPedido);

                lstViewPedidosEntregar.setOnItemClickListener(
                        new verPedido()
                );
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    private class verPedido implements android.widget.AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //listaPedidos.get(position).getId();
            Toast.makeText(ListarPedido.this, listaPedidos.get(position).getCliente()+"", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(ListarPedido.this,RutaMapsPedido.class);
            i.putExtra("latitud",""+listaPedidos.get(position).getLatitud());
            i.putExtra("longitud",""+listaPedidos.get(position).getLongitud());
            i.putExtra("cliente",""+listaPedidos.get(position).getCliente());
            i.putExtra("direccion",""+listaPedidos.get(position).getDireccion());
            startActivity(i);
        }
    }
}