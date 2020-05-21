package com.example.parcialrepeticion.Interface;

import com.example.parcialrepeticion.Modelo.ListaCanciones;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonListaCancionesApi {

    @GET("2.0/?method=chart.gettoptracks&api_key=b284db959637031077380e7e2c6f2775&format=json")
    Call<List<ListaCanciones>> getListaCanciones();


}
