package com.example.rastreadordecasoscovid_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PaisesAfectados extends AppCompatActivity {
    EditText edtBuscar;//texto/nombre de pais a buscar
    ListView listView;
    SimpleArcLoader simpleArcLoader;//animacion

    public static List<ModeloPais> ListaModeloPais = new ArrayList<>();
    ModeloPais modeloPais;
    AdaptadorEspecial adaptadorEspecial;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paises_afectados);

        edtBuscar = findViewById(R.id.edtBuscar);
        listView = findViewById(R.id.listView);
        simpleArcLoader = findViewById(R.id.loader);
        getSupportActionBar().setTitle("Países afectados");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ConsumirApiPais();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(),Detalles.class).putExtra("position",position));
            }
        });


        edtBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            adaptadorEspecial.getFilter().filter(s);
            adaptadorEspecial.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    private void ConsumirApiPais() {

        String url  = "https://corona.lmao.ninja/v2/countries/";

        simpleArcLoader.start();

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for(int i=0;i<jsonArray.length();i++){

                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                String NombrePais = jsonObject.getString("country");
                                String CasosTotales = jsonObject.getString("cases");
                                String CasosHoy = jsonObject.getString("todayCases");
                                String MuertesTotales = jsonObject.getString("deaths");
                                String MuertesHoy= jsonObject.getString("todayDeaths");
                                String Recuperados = jsonObject.getString("recovered");
                                String activos = jsonObject.getString("active");
                                String criticos = jsonObject.getString("critical");

                                JSONObject object = jsonObject.getJSONObject("countryInfo");
                                String flagUrl = object.getString("flag");

                                modeloPais = new ModeloPais(flagUrl,NombrePais,CasosTotales,CasosHoy,MuertesTotales,MuertesHoy,Recuperados,activos,criticos);
                                ListaModeloPais.add(modeloPais);


                            }

                            adaptadorEspecial = new AdaptadorEspecial(PaisesAfectados.this,ListaModeloPais);
                            listView.setAdapter(adaptadorEspecial);
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);






                        } catch (JSONException e) {
                            e.printStackTrace();
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                Toast.makeText(PaisesAfectados.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }
}