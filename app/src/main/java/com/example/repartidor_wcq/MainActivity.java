package com.example.repartidor_wcq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
Button btnRegPedido,btnListarPedido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRegPedido=findViewById(R.id.btn_reg_pedido);
        btnListarPedido=findViewById(R.id.btnVerPedidos);
        btnRegPedido.setOnClickListener(this);
        btnListarPedido.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_reg_pedido:{
                Intent i=new Intent(this,UbicacionPedido.class);
                startActivity(i);
            }
            case R.id.btnVerPedidos:{
                Intent i=new Intent(this,ListarPedido.class);
                startActivity(i);
            }
        }

    }
}