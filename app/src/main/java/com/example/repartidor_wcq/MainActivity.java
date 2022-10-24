package com.example.repartidor_wcq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button btnRegPedido,btnListarPedido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRegPedido=findViewById(R.id.btn_reg_pedido);
        btnListarPedido=findViewById(R.id.btnListarPedidos);

        btnRegPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,UbicacionPedido.class);
                startActivity(intent);
            }
        });

        btnListarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,ListarPedido.class);
                startActivity(i);
            }
        });
    }

}