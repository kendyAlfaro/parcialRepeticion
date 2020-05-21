package com.example.parcialrepeticion;

import android.os.Bundle;
import android.os.StrictMode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcialrepeticion.Modelo.ListaCanciones;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRvListaCanciones;
    private AdaptadorListaCanciones mAdaptador;
    private ArrayList<ListaCanciones> arrayListItems = new ArrayList<>();


    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String USGS_RESQUEST_URL =
            "http://ws.audioscrobbler.com/2.0/?method=chart.gettoptracks&api_key=b284db959637031077380e7e2c6f2775&format=json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRvListaCanciones = findViewById(R.id.rv_lista_canciones);

        mRvListaCanciones.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRvListaCanciones.setLayoutManager(linearLayoutManager);
        mRvListaCanciones.setHasFixedSize(true);
        //datos();

       // getData();
        //getListaCanciones();

    }


    /*    public void datos() {
            arrayListItems = new ArrayList<>();
            ListaCanciones n1 = new ListaCanciones();
            n1.setName("ana");
            n1.setArtista("Artista");
            n1.setDuration("5");
            arrayListItems.add(n1);
            ListaCanciones n2 = new ListaCanciones();
            n2.setName("Pedro");
            n2.setArtista("Artista");
            n2.setDuration("5");
            arrayListItems.add(n2);
        }*/
    public void getData() {
        String sql = "http://ws.audioscrobbler.com/2.0/?method=chart.gettoptracks&api_key=b284db959637031077380e7e2c6f2775&format=json";
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        URL url = null;
        HttpURLConnection conn;

        try {
            url = new URL(sql);
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            Gson gson = new Gson();
            String json = "";
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            json = response.toString();
            JSONArray array = new JSONArray(response.toString());
            for(int i = 0; i<=array.length()-1;i++){
                JSONObject object = array.getJSONObject(i);
                //ListaCanciones l1 = new ListaCanciones(object.getString("track"),object.getString("duration"),object.getString("url"));
                System.out.println("PRUEBA :"+ object.getString("track"));
                // arrayListItems.add(l1);
            }
            mAdaptador = new AdaptadorListaCanciones(this, arrayListItems);
            mRvListaCanciones.setAdapter(mAdaptador);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void llenararreglo(String respuesta){
        try {
            JSONArray array = new JSONArray(respuesta);
            for(int i = 0; i<=array.length()-1;i++){
                JSONObject object = array.getJSONObject(i);
                //ListaCanciones l1 = new ListaCanciones(object.getString("track"),object.getString("duration"),object.getString("url"));
                System.out.println("PRUEBA :"+ object.getString("track"));
               // arrayListItems.add(l1);
            }

            mAdaptador.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

   /* private void getListaCanciones() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ws.audioscrobbler.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonListaCancionesApi jsonListaCancionesApi = retrofit.create(JsonListaCancionesApi.class);
        Call<List<ListaCanciones>> call = jsonListaCancionesApi.getListaCanciones();
        call.enqueue(new Callback<List<ListaCanciones>>() {
            @Override
            public void onResponse(Call<List<ListaCanciones>> call, Response<List<ListaCanciones>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Codigo" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    List<ListaCanciones> listItems = response.body();
                    System.out.print("TODAL LA LISTA"+ listItems);
                    for (ListaCanciones lista : listItems) {
                        ListaCanciones lc= new ListaCanciones();
                        lc.setName(lista.getName());
                        lc.setDuration(lista.getDuration());
                        lc.setPlaycount(lista.getPlaycount());
                        lc.setListeners(lista.getListeners());
                        lc.setMbid(lista.getMbid());
                        lc.setUrl(lista.getUrl());
                        lc.setStreamable(lista.getStreamable());
                        lc.setArtista(lista.getArtista());
                        lc.setImagen(lista.getImagen());
                        arrayListItems.add(lc);

                    }
                    System.out.println("AQUI ESTA EL ARRAY "+arrayListItems);
                }catch (Exception e){
                    System.out.println("no trajo nada");
                }



            }

            @Override
            public void onFailure(Call<List<ListaCanciones>> call, Throwable t) {

            }
        });
    }
*/
}
