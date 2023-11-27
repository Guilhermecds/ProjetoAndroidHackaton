package com.alfaumuarama.projetoandroidhackaton;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/usuarios/")
    Call<List<User>> getUsers();

    @GET("/rotas/")
    Call<List<Rota>> getRotas();
}