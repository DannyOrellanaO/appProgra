package com.example.rastreadordecasoscovid_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class Detalles extends AppCompatActivity {
private int posicionPais;
TextView tvNombrePais, tvCasos, tvRecuperados, tvCriticos, tvActivos, tvCasosHoy, tvMuertestotales, tvmuerteshoy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detalles);

        Intent intent = getIntent();
        posicionPais = intent.getIntExtra("position",0);

        getSupportActionBar().setTitle("Detalles de " + PaisesAfectados.ListaModeloPais.get(posicionPais).getNommbrePais());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvNombrePais = findViewById(R.id.tvNombrePais);
        tvCasos = findViewById(R.id.tvCasos);
        tvRecuperados = findViewById(R.id.tvRecuperados);
        tvCriticos = findViewById(R.id.tvCritios);
        tvActivos = findViewById(R.id.tvActivos);
        tvCasosHoy = findViewById(R.id.tvCasosdehoy);
        tvMuertestotales = findViewById(R.id.tvTotalmuertes);
        tvmuerteshoy = findViewById(R.id.tvMuerteshoy);

        tvNombrePais.setText(PaisesAfectados.ListaModeloPais.get(posicionPais).getNommbrePais());
        tvCasos.setText(PaisesAfectados.ListaModeloPais.get(posicionPais).getCasosTotales());
        tvRecuperados.setText(PaisesAfectados.ListaModeloPais.get(posicionPais).getRecuperados());
        tvCriticos.setText(PaisesAfectados.ListaModeloPais.get(posicionPais).getCasosCriticos());
        tvActivos.setText(PaisesAfectados.ListaModeloPais.get(posicionPais).getCasosActivos());
        tvCasosHoy.setText(PaisesAfectados.ListaModeloPais.get(posicionPais).getCasosHoy());
        tvMuertestotales.setText(PaisesAfectados.ListaModeloPais.get(posicionPais).getMuertesTotales());
        tvmuerteshoy.setText(PaisesAfectados.ListaModeloPais.get(posicionPais).getMuertesHoy());


    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

}