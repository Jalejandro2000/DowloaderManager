package com.example.dowloadermanager.service;

import com.example.dowloadermanager.model.Ficheros;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FicheroService {
    @GET("Ficheros")
    Call<List<Ficheros>> getFicheros();
}
