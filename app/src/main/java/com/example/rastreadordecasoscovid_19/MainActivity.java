package com.example.rastreadordecasoscovid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView tvCasos,tvRecuperados,tvCritios,tvActivos,tvCasosdehoy,tvTotalmuertes,tvMuerteshoy,tvPaisesAfectados;//TextViews
    SimpleArcLoader simpleArcLoader;// animacion de carga
    ScrollView scrollView;//Scroll/bajar/deslizar
    PieChart pieChart;//grafica de pie
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvCasos = findViewById(R.id.tvCasos);
        tvRecuperados = findViewById(R.id.tvRecuperados);
        tvCritios = findViewById(R.id.tvCritios);
        tvActivos = findViewById(R.id.tvActivos);
        tvCasosdehoy = findViewById(R.id.tvCasosdehoy);
        tvTotalmuertes = findViewById(R.id.tvTotalmuertes);
        tvMuerteshoy = findViewById(R.id.tvMuerteshoy);
        tvPaisesAfectados = findViewById(R.id.tvPaisesAfectados);

        simpleArcLoader = findViewById(R.id.loader);
        scrollView = findViewById(R.id.scrollStats);
        pieChart = findViewById(R.id.piechart);


        ConsumirAPIData();
    }

    private void ConsumirAPIData() {

        String url  = "https://corona.lmao.ninja/v2/all/";

        simpleArcLoader.start();//animacion de carga

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());

                            tvCasos.setText(jsonObject.getString("cases"));
                            tvRecuperados.setText(jsonObject.getString("recovered"));
                            tvCritios.setText(jsonObject.getString("critical"));
                            tvActivos.setText(jsonObject.getString("active"));
                            tvCasosdehoy.setText(jsonObject.getString("todayCases"));
                            tvTotalmuertes.setText(jsonObject.getString("deaths"));
                            tvMuerteshoy.setText(jsonObject.getString("todayDeaths"));
                            tvPaisesAfectados.setText(jsonObject.getString("affectedCountries"));


                            pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(tvCasos.getText().toString()), Color.parseColor("#FFA726")));
                            pieChart.addPieSlice(new PieModel("Recoverd",Integer.parseInt(tvRecuperados.getText().toString()), Color.parseColor("#66BB6A")));
                            pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(tvTotalmuertes.getText().toString()), Color.parseColor("#EF5350")));
                            pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(tvActivos.getText().toString()), Color.parseColor("#29B6F6")));
                            pieChart.startAnimation();

                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);




                        } catch (JSONException e) {
                            e.printStackTrace();
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }

    public void RastrearCasosPorPais(View view) {
        startActivity(new Intent(getApplicationContext(),PaisesAfectados.class));
    }
}